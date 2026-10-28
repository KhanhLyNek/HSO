#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."

GAME="KnightOnline_v403.jar"
WORK="build/autofarm"
DIST="dist"
mkdir -p "$WORK/lib" "$WORK/classes" "$WORK/patcher" "$DIST"
rm -f "$WORK/classes"/Tool/*.class "$DIST/HSO_AutoFarm_v403.jar" 2>/dev/null || true

fetch() {
  local url="$1" path="$2"
  if [ ! -s "$path" ]; then
    curl -fL --retry 3 --connect-timeout 15 -o "$path" "$url"
  fi
}
BASE="https://repo.maven.apache.org/maven2"
fetch "$BASE/org/eclipse/jdt/core/compiler/ecj/4.6.1/ecj-4.6.1.jar" "$WORK/lib/ecj.jar"
fetch "$BASE/org/microemu/cldcapi11/2.0.4/cldcapi11-2.0.4.jar" "$WORK/lib/cldcapi11.jar"
fetch "$BASE/org/microemu/midpapi20/2.0.4/midpapi20-2.0.4.jar" "$WORK/lib/midpapi20.jar"

# The original MIDlet uses Java class-file version 45.3. ECJ supports the
# CLDC StackMap attribute directly (unlike javac 21/8).
java -jar "$WORK/lib/ecj.jar" \
  -source 1.3 -target cldc1.1 \
  -bootclasspath "$WORK/lib/cldcapi11.jar:$WORK/lib/midpapi20.jar" \
  -classpath "$GAME" -encoding UTF-8 -nowarn \
  -d "$WORK/classes" decompiled-src/Tool/*.java

ASM_EXPORT="--add-exports=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED"
javac $ASM_EXPORT -d "$WORK/patcher" tools/BuildFarmJar.java
java $ASM_EXPORT -cp "$WORK/patcher" BuildFarmJar \
  "$GAME" "$WORK/classes" "$WORK/KnightOnline_farm_prepatch.jar"

# Preverify all game classes after the two original classes were patched.
# ProGuard emits Java ME StackMap attributes when -microedition is enabled.
curl -fL --retry 3 --connect-timeout 15 -o "$WORK/lib/proguard.zip" \
  https://github.com/Guardsquare/proguard/releases/download/v7.7/proguard-7.7.0.zip
unzip -oq "$WORK/lib/proguard.zip" -d "$WORK/lib/proguard"
PROGUARD=$(find "$WORK/lib/proguard" -name 'proguard.jar' | head -n 1)
test -s "$PROGUARD"
java -jar "$PROGUARD" \
  -injars "$WORK/KnightOnline_farm_prepatch.jar" \
  -outjars "$DIST/HSO_AutoFarm_v403.jar" \
  -libraryjars "$WORK/lib/cldcapi11.jar" \
  -libraryjars "$WORK/lib/midpapi20.jar" \
  -dontshrink -dontoptimize -dontobfuscate \
  -dontwarn -dontnote -microedition \
  -keep 'class * { *; }'

python3 - <<'PY'
import zipfile, os, struct, re
original='KnightOnline_v403.jar'
output='dist/HSO_AutoFarm_v403.jar'
required=[
  'Tool/AutoFarmController.class', 'Tool/FarmWorldData.class',
  'Tool/FarmRoutePlanner.class', 'Tool/FarmTeleportData.class',
  'Tool/FarmMemory.class', 'Tool/FarmUiBridge.class',
  'GameScreen/GameScreen.class', 'GameObjects/Player.class',
]
with zipfile.ZipFile(original) as a, zipfile.ZipFile(output) as b:
    errors=b.testzip()
    if errors: raise SystemExit('Corrupt ZIP entry: '+errors)
    a_names=set(x for x in a.namelist() if not x.endswith('/'))
    b_names=set(x for x in b.namelist() if not x.endswith('/'))
    missing=sorted(a_names-b_names)
    if missing: raise SystemExit('Missing original assets: '+repr(missing[:20]))
    for name in required:
        if name not in b_names: raise SystemExit('Missing class: '+name)
        x=b.read(name)
        if len(x)<8 or x[:4]!=b'\xca\xfe\xba\xbe':
            raise SystemExit('Invalid class: '+name)
        minor,major=struct.unpack('>HH',x[4:8])
        if (minor,major)!=(3,45):
            raise SystemExit('Java ME 45.3 required: %s has %d.%d'%(name,major,minor))
    if b'StackMap' not in b.read('Tool/AutoFarmController.class'):
        raise SystemExit('CLDC preverification missing in AutoFarmController')
    for name in a_names:
        if name.startswith('META-INF/') or name.endswith('.class'): continue
        if a.read(name)!=b.read(name):
            raise SystemExit('Original asset changed: '+name)
    manifest=b.read('META-INF/MANIFEST.MF').decode('latin-1')
    attrs={}
    for line in manifest.replace('\r','').split('\n'):
        if ': ' in line:
            key,value=line.split(': ',1)
            attrs[key]=value
    jarname=os.path.basename(output)
    size=os.path.getsize(output)
    jad=[
        'MIDlet-Name: '+attrs.get('MIDlet-Name','HSO Auto Farm'),
        'MIDlet-Version: '+attrs.get('MIDlet-Version','4.0.3'),
        'MIDlet-Vendor: '+attrs.get('MIDlet-Vendor','HSO'),
        'MIDlet-1: '+attrs.get('MIDlet-1','KnightOnline,,Main.GameMidlet'),
        'MicroEdition-Configuration: '+attrs.get('MicroEdition-Configuration','CLDC-1.1'),
        'MicroEdition-Profile: '+attrs.get('MicroEdition-Profile','MIDP-2.0'),
        'MIDlet-Jar-URL: '+jarname,
        'MIDlet-Jar-Size: '+str(size),
    ]
    with open('dist/HSO_AutoFarm_v403.jad','w',encoding='utf-8',newline='') as w:
        w.write('\r\n'.join(jad)+'\r\n')
print('Validated:',output,'size:',size,'classfiles: 45.3; preverified StackMap; all original non-class assets retained')
PY

ls -lh "$DIST/HSO_AutoFarm_v403.jar" "$DIST/HSO_AutoFarm_v403.jad"
