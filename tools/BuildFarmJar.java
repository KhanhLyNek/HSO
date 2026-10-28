import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import jdk.internal.org.objectweb.asm.*;

/** Patch original 45.3 MIDlet classes without recompiling CFR-decompiled sources. */
public final class BuildFarmJar implements Opcodes {
    private static int updateHooks, paintHooks, commandHooks, menuHooks, mobHooks, npcResponseHooks;

    private static byte[] patchScreen(byte[] original) {
        ClassReader reader = new ClassReader(original);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(ASM8, writer) {
            @Override public MethodVisitor visitMethod(int access, String name, String descriptor,
                    String signature, String[] exceptions) {
                MethodVisitor target = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (target == null) return null;
                final boolean update = name.equals("update") && descriptor.equals("()V");
                final boolean paint = name.equals("paint") && descriptor.equals("(LCLib/mGraphics;)V");
                final boolean command = name.equals("commandPointer") && descriptor.equals("(II)V");
                final boolean menu = name.equals("MenuChucNang") || name.equals("doMenuAuto");
                return new MethodVisitor(ASM8, target) {
                    @Override public void visitCode() {
                        super.visitCode();
                        if (update) {
                            updateHooks++;
                            super.visitMethodInsn(INVOKESTATIC, "Tool/AutoFarmController",
                                    "tick", "()V", false);
                        }
                        if (command) {
                            commandHooks++;
                            Label proceed = new Label();
                            super.visitVarInsn(ILOAD, 1);
                            super.visitMethodInsn(INVOKESTATIC, "Tool/FarmUiBridge",
                                    "handleCommand", "(I)Z", false);
                            super.visitJumpInsn(IFEQ, proceed);
                            super.visitInsn(RETURN);
                            super.visitLabel(proceed);
                        }
                    }
                    @Override public void visitMethodInsn(int opcode, String owner, String mName,
                            String desc, boolean itf) {
                        if (menu && owner.equals("Model/Menu2") && mName.equals("startAt")
                                && desc.startsWith("(LCLib/mVector;")) {
                            // The original local 1 holds the menu list.
                            super.visitVarInsn(ALOAD, 1);
                            super.visitMethodInsn(INVOKESTATIC, "Tool/FarmUiBridge",
                                    "appendMenu", "(LCLib/mVector;)V", false);
                            menuHooks++;
                        }
                        super.visitMethodInsn(opcode, owner, mName, desc, itf);
                        if (paint && owner.equals("Thread_More/LoadMap")
                                && mName.equals("paint")
                                && desc.equals("(LCLib/mGraphics;)V")) {
                            // The world-space camera translation is still active here.
                            super.visitVarInsn(ALOAD, 1);
                            super.visitMethodInsn(INVOKESTATIC, "Tool/AutoFarmController",
                                    "paintFarmRing", "(LCLib/mGraphics;)V", false);
                            paintHooks++;
                        }
                    }
                };
            }
        };
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

    private static byte[] patchPlayer(byte[] original) {
        ClassReader reader = new ClassReader(original);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(ASM8, writer) {
            @Override public MethodVisitor visitMethod(int access, String name, String descriptor,
                    String signature, String[] exceptions) {
                MethodVisitor target = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (target == null || !name.equals("nextMonster") || !descriptor.equals("()V"))
                    return target;
                return new MethodVisitor(ASM8, target) {
                    @Override public void visitCode() {
                        super.visitCode();
                        mobHooks++;
                        Label nativeSelection = new Label();
                        super.visitVarInsn(ALOAD, 0);
                        super.visitMethodInsn(INVOKESTATIC, "Tool/FarmUiBridge", "selectNextMonster",
                                "(LGameObjects/Player;)Z", false);
                        super.visitJumpInsn(IFEQ, nativeSelection);
                        super.visitInsn(RETURN);
                        super.visitLabel(nativeSelection);
                    }
                };
            }
        };
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

    /**
     * Observe a real server-supplied dynamic NPC menu.  The npcId is parameter
     * slot 4 of Menu2.setinfoDynamic(mVector, int, int, int, String).
     * Do not send a selection before this method has actually rendered the menu.
     */
    private static byte[] patchMenu(byte[] original) {
        ClassReader reader = new ClassReader(original);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(ASM8, writer) {
            @Override public MethodVisitor visitMethod(int access, String name, String desc,
                    String signature, String[] exceptions) {
                MethodVisitor target = super.visitMethod(access, name, desc, signature, exceptions);
                if (target == null || !name.equals("setinfoDynamic") ||
                        !desc.equals("(LCLib/mVector;IIILjava/lang/String;)V"))
                    return target;
                return new MethodVisitor(ASM8, target) {
                    @Override public void visitInsn(int opcode) {
                        if (opcode == RETURN) {
                            npcResponseHooks++;
                            Label skip = new Label();
                            super.visitVarInsn(ALOAD, 1);
                            super.visitJumpInsn(IFNULL, skip);
                            super.visitVarInsn(ILOAD, 4);
                            super.visitVarInsn(ALOAD, 1);
                            super.visitMethodInsn(INVOKEVIRTUAL, "CLib/mVector", "size", "()I", false);
                            super.visitMethodInsn(INVOKESTATIC, "Tool/AutoFarmController",
                                    "onTeleportMenu", "(II)V", false);
                            super.visitLabel(skip);
                        }
                        super.visitInsn(opcode);
                    }
                };
            }
        };
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

    private static byte[] bytes(JarFile jar, JarEntry entry) throws IOException {
        try (InputStream input = jar.getInputStream(entry);
                ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[16384];
            int n;
            while ((n = input.read(buffer)) != -1) result.write(buffer, 0, n);
            return result.toByteArray();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3)
            throw new IllegalArgumentException("original.jar compiled-classes output.jar");
        Path compiled = Paths.get(args[1]);
        Path output = Paths.get(args[2]);
        Set<String> names = new HashSet<String>();
        int[] patched = new int[3];
        int added = 0;
        Files.createDirectories(output.getParent());
        try (JarFile source = new JarFile(args[0]);
                JarOutputStream archive = new JarOutputStream(Files.newOutputStream(output))) {
            Enumeration<JarEntry> iterator = source.entries();
            while (iterator.hasMoreElements()) {
                JarEntry e = iterator.nextElement();
                String entryName = e.getName();
                if (e.isDirectory()) continue;
                if (entryName.startsWith("META-INF/") &&
                        (entryName.endsWith(".RSA") || entryName.endsWith(".SF")
                                || entryName.endsWith(".DSA"))) continue;
                byte[] data = bytes(source, e);
                if (entryName.equals("GameScreen/GameScreen.class")) {
                    data = patchScreen(data);
                    patched[0]++;
                } else if (entryName.equals("GameObjects/Player.class")) {
                    data = patchPlayer(data);
                    patched[1]++;
                } else if (entryName.equals("Model/Menu2.class")) {
                    data = patchMenu(data);
                    patched[2]++;
                }
                JarEntry newEntry = new JarEntry(entryName);
                archive.putNextEntry(newEntry);
                archive.write(data);
                archive.closeEntry();
                names.add(entryName);
            }
            try (java.util.stream.Stream<Path> stream = Files.walk(compiled)) {
                for (Path file : (Iterable<Path>)stream.filter(p ->
                        p.toString().endsWith(".class"))::iterator) {
                    String name = compiled.relativize(file).toString()
                            .replace(File.separatorChar, '/');
                    if (!names.add(name)) throw new IOException("Duplicate entry: " + name);
                    byte[] data = Files.readAllBytes(file);
                    if (data.length < 8 || data[0] != (byte)0xCA ||
                            data[1] != (byte)0xFE || data[2] != (byte)0xBA ||
                            data[3] != (byte)0xBE || data[6] != 0 || data[7] != 45)
                        throw new IOException("Not a Java ME 45.3 class: " + name);
                    JarEntry e = new JarEntry(name);
                    archive.putNextEntry(e);
                    archive.write(data);
                    archive.closeEntry();
                    added++;
                }
            }
        }
        if (patched[0] != 1 || patched[1] != 1 || patched[2] != 1 || updateHooks != 1 ||
                commandHooks != 1 || paintHooks != 1 || menuHooks < 2 || mobHooks != 1 || npcResponseHooks < 1 ||
                added < 6)
            throw new IOException("Incomplete patch: originals=" + Arrays.toString(patched) +
                    ", update=" + updateHooks + ", command=" + commandHooks +
                    ", paint=" + paintHooks + ", menu=" + menuHooks +
                    ", monster=" + mobHooks + ", npcMenu=" + npcResponseHooks + ", added=" + added);
        System.out.println("Patched GameScreen, Player, Menu2; hooks update=" + updateHooks +
                ", menu=" + menuHooks + ", paint=" + paintHooks +
                ", command=" + commandHooks + ", mob=" + mobHooks +
                ", npcMenu=" + npcResponseHooks +
                "; added CLDC classes=" + added);
        System.out.println("Output: " + output);
    }
}
