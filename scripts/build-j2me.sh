#!/usr/bin/env bash
# Build a REAL CLDC-preverified MIDlet only if every changed class compiles.
# Never substitute JVM smoke classes or publish a resource-only placeholder.
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p build/j2me/toolchain build/j2me/src build/j2me/classes build/j2me/staging dist
TOOL=build/j2me/toolchain

download() {
  local url="$1" file="$2"
  if [[ ! -s "$file" ]]; then curl --retry 3 -fsSL "$url" -o "$file"; fi
}
download 'https://repo.maven.apache.org/maven2/org/eclipse/jdt/core/compiler/ecj/4.6.1/ecj-4.6.1.jar' "$TOOL/ecj.jar"
download 'https://repo.maven.apache.org/maven2/org/microemu/cldcapi10/2.0.4/cldcapi10-2.0.4.jar' "$TOOL/cldcapi10.jar"
download 'https://repo.maven.apache.org/maven2/org/microemu/midpapi20/2.0.4/midpapi20-2.0.4.jar' "$TOOL/midpapi20.jar"

# Compile only the source files actively changed by LyMeowz. The remaining
# already-built client classes come from resources/. The original source tree
# is left untouched.
python3 - <<'PY'
from pathlib import Path
import re
roots = [
  'sources/GameScreen/GameScreen.java',
  'sources/GameScreen/LyMeowzMapReader.java',
  'sources/GameScreen/LyMeowzMapTransitions.java',
  'sources/GameScreen/LyMeowzNpcMapRegistry.java',
  'sources/GameScreen/LyMeowzWorldStore.java',
  'sources/GameScreen/LyMeowzAIDirector.java',
  'sources/GameScreen/LyMeowzDiagnostics.java',
  'sources/GameScreen/LyMeowzTrainingPlanner.java',
  'sources/GameScreen/LyMeowzTrainingSpotRegistry.java',
  'sources/GameScreen/LyMeowzRecovery.java',
  'sources/GameObjects/Player.java',
  'sources/GameObjects/MainQuest.java',
  'sources/GameObjects/NPC.java',
  'sources/netcommand/global/ReadMessenge.java',
  'sources/Main/ChildLocationDiagnostics.java',
  'sources/Main/TestMenu.java',
]
for item in roots:
    src=Path(item)
    if not src.is_file(): raise SystemExit('Missing source '+item)
    text=src.read_text(encoding='utf-8')
    # Decompiler adds Java 5 annotations and 3 enhanced-for loops; strip
    # these syntax-only additions for CLDC's Java 1.3 source compatibility.
    text=text.replace('@Override', '')
    pattern=r'for \(MainInfoItem (\w+) : mainInfoItemArr\) \{'
    counter=[0]
    def lower(match):
        var=match.group(1)
        idx='lyIndex'+str(counter[0])
        counter[0]+=1
        return ('for (int '+idx+'=0; '+idx+'<mainInfoItemArr.length; '+idx+'++) {\n'
                '                            MainInfoItem '+var+' = mainInfoItemArr['+idx+'];')
    text=re.sub(pattern, lower, text)
    if re.search(r'for\s*\([^;]*\s+:\s+[^)]+\)', text):
        raise SystemExit('Unsupported enhanced-for: '+item)
    out=Path('build/j2me/src')/src.relative_to('sources')
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(text, encoding='utf-8')
Path('build/j2me/sources.txt').write_text(
    '\n'.join(str(Path('build/j2me/src')/Path(item).relative_to('sources')) for item in roots))
PY

# ECJ's cldc1.1 target emits 45.3 class files with CLDC StackMap preverification.
# Boot classpath uses CLDC 1.0 + MIDP 2.0, matching the original manifest.
mapfile -t SOURCE_FILES < build/j2me/sources.txt
java -jar "$TOOL/ecj.jar" -1.3 -target cldc1.1 -encoding UTF-8 \
  -bootclasspath "$TOOL/cldcapi10.jar:$TOOL/midpapi20.jar" \
  -classpath resources -d build/j2me/classes "${SOURCE_FILES[@]}"

test -s build/j2me/classes/GameScreen/GameScreen.class
test -s build/j2me/classes/netcommand/global/ReadMessenge.class
test -s build/j2me/classes/GameScreen/LyMeowzAIDirector.class

cp -a resources/. build/j2me/staging/
cp -a build/j2me/classes/. build/j2me/staging/
rm -f build/j2me/staging/META-INF/MANIFEST.MF
mkdir -p build/j2me/staging/META-INF

cat > build/j2me/MANIFEST.MF <<'EOF'
Manifest-Version: 1.0
MIDlet-Name: LyMeowz HSO
MIDlet-Vendor: LyMeowz
MIDlet-Version: 4.0.3
MIDlet-1: LyMeowz HSO,/icon.png,com.silverknight.TemMidlet
MicroEdition-Configuration: CLDC-1.0
MicroEdition-Profile: MIDP-2.0
EOF

# Check each packaged class file is Java 1.1 bytecode (major <=45). Check
# newly compiled classes have the CLDC StackMap attribute, not JVM StackMapTable.
python3 - <<'PY'
from pathlib import Path
import struct
staging=Path('build/j2me/staging')
classes=list(staging.rglob('*.class'))
if not classes: raise SystemExit('No class files, cannot package J2ME')
for p in classes:
    b=p.read_bytes()
    if len(b)<10 or b[:4]!=b'\xca\xfe\xba\xbe':
        raise SystemExit('Malformed class: '+str(p))
    minor,major=struct.unpack('>HH',b[4:8])
    if major>45: raise SystemExit('Non-J2ME class (major '+str(major)+'): '+str(p))
for p in Path('build/j2me/classes').rglob('*.class'):
    b=p.read_bytes()
    if b'StackMap' not in b: raise SystemExit('CLDC StackMap missing: '+str(p))
print('J2ME class version and preverification inspection: PASS, '+str(len(classes))+' classes')
PY

jar cfm dist/LyMeowz-HSO.jar build/j2me/MANIFEST.MF -C build/j2me/staging .
python3 - <<'PY'
from pathlib import Path
size=Path('dist/LyMeowz-HSO.jar').stat().st_size
if size < 1024: raise SystemExit('Invalid JAR')
jad = ('MIDlet-Name: LyMeowz HSO\n'
       'MIDlet-Vendor: LyMeowz\n'
       'MIDlet-Version: 4.0.3\n'
       'MIDlet-1: LyMeowz HSO,/icon.png,com.silverknight.TemMidlet\n'
       'MIDlet-Jar-URL: LyMeowz-HSO.jar\n'
       'MIDlet-Jar-Size: '+str(size)+'\n'
       'MicroEdition-Configuration: CLDC-1.0\n'
       'MicroEdition-Profile: MIDP-2.0\n')
Path('dist/LyMeowz-HSO.jad').write_text(jad)
print('J2ME JAR and JAD packaged:', size, 'bytes')
PY
unzip -t dist/LyMeowz-HSO.jar | tail -1
