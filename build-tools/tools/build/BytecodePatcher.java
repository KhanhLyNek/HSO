package tools.build;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.commons.ClassRemapper;
import jdk.internal.org.objectweb.asm.commons.Remapper;

/**
 * Bytecode-only integration of the decompiled, precompiled HSO client.
 * Avoids recompiling malformed decompiler output and patches only known methods.
 * New classes are downgraded to CLDC classfile version and preverified separately.
 */
public final class BytecodePatcher implements Opcodes {
    private static final String BRIDGE = "tools/ui/ToolMenus";
    private static final String VECTOR = "LCLib/mVector;ILjava/lang/String;ZLCLib/mVector;";
    private static final String START_DESC = "(" + VECTOR + ")V";
    private static int menus, functions, autos, shows, actions, quick, quickDispatch, ticks, targets, hudPaints, before, after, dynamic;

    private static void require(boolean ok, String message) {
        if (!ok) throw new IllegalStateException("Patch validation failed: " + message);
    }

    private static byte[] patchOriginal(final String className, byte[] source) {
        ClassReader reader = new ClassReader(source);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, final String name, String descriptor,
                                             String signature, String[] exceptions) {
                MethodVisitor original = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (original == null) return null;
                return new MethodVisitor(ASM9, original) {
                    @Override
                    public void visitCode() {
                        super.visitCode();
                        if (className.equals("GameScreen/GameScreen") && name.equals("commandPointer") &&
                            descriptor.equals("(II)V")) {
                            super.visitVarInsn(ILOAD, 1);
                            super.visitMethodInsn(INVOKESTATIC, BRIDGE, "dispatch", "(I)Z", false);
                            Label next = new Label();
                            super.visitJumpInsn(IFEQ, next);
                            super.visitInsn(RETURN);
                            super.visitLabel(next);
                            actions++;
                        } else if (className.equals("Model/Menu2") && name.equals("commandPointer") &&
                                   descriptor.equals("(II)V")) {
                            super.visitVarInsn(ILOAD, 1);
                            super.visitMethodInsn(INVOKESTATIC, BRIDGE, "quickAction", "(I)Z", false);
                            Label next = new Label();
                            super.visitJumpInsn(IFEQ, next);
                            super.visitInsn(RETURN);
                            super.visitLabel(next);
                            quickDispatch++;
                        } else if (className.equals("GameObjects/Player") &&
                                   name.equals("nextMonster") && descriptor.equals("()V")) {
                            super.visitVarInsn(ALOAD, 0);
                            super.visitMethodInsn(INVOKESTATIC, BRIDGE, "onNextMonster",
                                "(LGameObjects/Player;)Z", false);
                            Label proceed = new Label();
                            super.visitJumpInsn(IFEQ, proceed);
                            super.visitInsn(RETURN);
                            super.visitLabel(proceed);
                            targets++;
                        } else if (className.equals("netcommand/global/ReadMessenge") && name.equals("changeMap")) {
                            super.visitMethodInsn(INVOKESTATIC, "tools/navigation/WorldTraveler",
                                "onMapLeaving", "()V", false);
                            super.visitMethodInsn(INVOKESTATIC, "tools/trading/TradeController",
                                "beforeMapChange", "()V", false);
                            before++;
                        }
                    }

                    @Override
                    public void visitFieldInsn(int opcode, String owner, String field, String desc) {
                        super.visitFieldInsn(opcode, owner, field, desc);
                        if (className.equals("Model/Menu2") && name.equals("setAt_Quick") &&
                            opcode == PUTFIELD && owner.equals("Model/Menu2") &&
                            field.equals("menuItems") && desc.equals("LCLib/mVector;")) {
                            insertQuickCommand("LyMeowz - Công cụ", 15);
                            quick++;
                        }
                    }

                    private void insertQuickCommand(String title, int id) {
                        super.visitVarInsn(ALOAD, 0);
                        super.visitFieldInsn(GETFIELD, "Model/Menu2", "menuItems", "LCLib/mVector;");
                        super.visitTypeInsn(NEW, "InterfaceComponents/iCommand");
                        super.visitInsn(DUP);
                        super.visitLdcInsn(title);
                        super.visitIntInsn(BIPUSH, id);
                        super.visitInsn(ICONST_2);
                        super.visitVarInsn(ALOAD, 0);
                        super.visitMethodInsn(INVOKESPECIAL, "InterfaceComponents/iCommand", "<init>",
                            "(Ljava/lang/String;IILModel/AvMain;)V", false);
                        super.visitMethodInsn(INVOKEVIRTUAL, "CLib/mVector", "addElement",
                            "(Ljava/lang/Object;)V", false);
                    }

                    @Override
                    public void visitMethodInsn(int opcode, String owner, String method,
                                                String desc, boolean isInterface) {
                        if (className.equals("GameScreen/GameScreen") &&
                            owner.equals("Model/Menu2") && method.equals("startAt") &&
                            desc.equals(START_DESC) && opcode == INVOKEVIRTUAL) {
                            String helper;
                            if (name.equals("doMenu")) {
                                helper = "openMain";
                                menus++;
                            } else if (name.equals("MenuChucNang")) {
                                helper = "openFunctions";
                                functions++;
                            } else if (name.equals("doMenuAuto")) {
                                helper = "openAuto";
                                autos++;
                            } else {
                                helper = null;
                            }
                            if (helper != null) {
                                super.visitMethodInsn(INVOKESTATIC, BRIDGE, helper,
                                    "(LModel/Menu2;" + VECTOR + ")V", false);
                                return;
                            }
                        }
                        if (className.equals("netcommand/global/ReadMessenge") &&
                            name.equals("Dynamic_Menu") && owner.equals("Model/Menu2") &&
                            method.equals("setinfoDynamic") &&
                            desc.equals("(LCLib/mVector;IIILjava/lang/String;)V")) {
                            super.visitMethodInsn(INVOKESTATIC, BRIDGE, "showDynamic",
                                "(LModel/Menu2;LCLib/mVector;IIILjava/lang/String;)V", false);
                            dynamic++;
                            return;
                        }
                        super.visitMethodInsn(opcode, owner, method, desc, isInterface);
                    }

                    @Override
                    public void visitInsn(int opcode) {
                        if (opcode == RETURN) {
                            if (className.equals("GameScreen/GameScreen") &&
                                    name.equals("paint") &&
                                    descriptor.equals("(LCLib/mGraphics;)V")) {
                                super.visitVarInsn(ALOAD, 1);
                                super.visitMethodInsn(INVOKESTATIC, "tools/ui/ToolHud",
                                    "paint", "(LCLib/mGraphics;)V", false);
                                hudPaints++;
                            } else if (className.equals("GameScreen/GameScreen") && name.equals("Show")) {
                                super.visitVarInsn(ALOAD, 0);
                                super.visitMethodInsn(INVOKESTATIC, BRIDGE, "onShow",
                                    "(LGameScreen/GameScreen;)V", false);
                                shows++;
                            } else if (className.equals("GameObjects/Player") && name.equals("update")) {
                                super.visitVarInsn(ALOAD, 0);
                                super.visitMethodInsn(INVOKESTATIC, BRIDGE, "playerTick",
                                    "(LGameObjects/Player;)V", false);
                                ticks++;
                            } else if (className.equals("netcommand/global/ReadMessenge") && name.equals("changeMap")) {
                                super.visitMethodInsn(INVOKESTATIC, "tools/trading/TradeController",
                                    "afterMapChange", "()V", false);
                                super.visitMethodInsn(INVOKESTATIC, "tools/navigation/WorldTraveler",
                                    "onMapChanged", "()V", false);
                                super.visitMethodInsn(INVOKESTATIC, "tools/training/AutoFarmExplorer",
                                    "onMapChanged", "()V", false);
                                after++;
                            }
                        }
                        super.visitInsn(opcode);
                    }
                };
            }
        };
        reader.accept(classVisitor, ClassReader.SKIP_FRAMES);
        return writer.toByteArray();
    }

    /** Remap Java 8 javac StringBuilder bytecode into CLDC-compatible StringBuffer. */
    private static byte[] downgradeNewClass(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(ASM9, writer) {
            @Override
            public void visit(int version, int access, String name, String signature,
                              String superName, String[] interfaces) {
                super.visit(V1_1, access, name, signature, superName, interfaces);
            }
        };
        Remapper remapper = new Remapper() {
            @Override
            public String map(String internalName) {
                return "java/lang/StringBuilder".equals(internalName)
                       ? "java/lang/StringBuffer" : internalName;
            }
        };
        reader.accept(new ClassRemapper(visitor, remapper), ClassReader.SKIP_FRAMES);
        byte[] out = writer.toByteArray();
        require((out[6] & 255) * 256 + (out[7] & 255) == 45, "bad classfile version");
        return out;
    }

    private static void process(Path input, Path output, boolean patch) throws Exception {
        if (!Files.isDirectory(input)) throw new IllegalArgumentException(input.toString());
        try (java.util.stream.Stream<Path> files = Files.walk(input)) {
            for (Path file : (Iterable<Path>) files.filter(Files::isRegularFile)::iterator) {
                Path relative = input.relativize(file);
                if (!file.toString().endsWith(".class")) continue;
                Path out = output.resolve(relative);
                Files.createDirectories(out.getParent());
                byte[] data = Files.readAllBytes(file);
                String name = relative.toString().replace('\\', '/').replaceAll("\\.class$", "");
                if (patch && (name.equals("GameScreen/GameScreen") ||
                              name.equals("Model/Menu2") ||
                              name.equals("GameObjects/Player") ||
                              name.equals("netcommand/global/ReadMessenge"))) {
                    data = patchOriginal(name, data);
                } else if (!patch) {
                    data = downgradeNewClass(data);
                } else {
                    continue;
                }
                Files.write(out, data);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        require(args.length == 3, "usage: BytecodePatcher resources/ modules/ output/");
        Path original = Paths.get(args[0]);
        Path modules = Paths.get(args[1]);
        Path out = Paths.get(args[2]);
        process(original, out, true);
        process(modules, out, false);
        require(menus == 1, "GameScreen.doMenu: " + menus);
        require(functions == 1, "GameScreen.MenuChucNang: " + functions);
        require(autos == 1, "GameScreen.doMenuAuto: " + autos);
        require(shows >= 1, "GameScreen.Show: " + shows);
        require(actions == 1, "GameScreen.commandPointer: " + actions);
        require(quick == 1, "Menu2.setAt_Quick: " + quick);
        require(quickDispatch == 1, "Menu2.commandPointer: " + quickDispatch);
        require(ticks >= 1, "Player.update: " + ticks);
        require(hudPaints >= 1, "GameScreen.paint HUD: " + hudPaints);
        require(targets == 1, "Player.nextMonster override: " + targets);
        require(before == 1 && after >= 1, "ReadMessenge.changeMap");
        require(dynamic == 1, "ReadMessenge.Dynamic_Menu");
        System.out.println("Bytecode integration PASS: main="+menus+" functions="+functions+
                " auto="+autos+" show="+shows+" command="+actions+" quick="+quick+
                " quickCommand="+quickDispatch+" playerTick="+ticks+" hudPaint="+hudPaints+" targetOverride="+targets+" map="+before+
                "/"+after+" npcMenu="+dynamic);
    }
}
