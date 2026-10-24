#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."

JAR="KnightOnline_v403.jar"
CFR_VERSION="0.152"
CFR_URL="https://repo.maven.apache.org/maven2/org/benf/cfr/${CFR_VERSION}/cfr-${CFR_VERSION}.jar"

if [[ ! -f "$JAR" ]]; then
  echo "Missing $JAR" >&2
  exit 1
fi
for cmd in java curl unzip find; do
  command -v "$cmd" >/dev/null || { echo "Missing dependency: $cmd" >&2; exit 1; }
done

# Regenerate only machine-generated directories.
rm -rf extracted decompiled-src
mkdir -p extracted decompiled-src

# Extract every original archive member, including class files and game assets.
unzip -oq "$JAR" -d extracted

# CFR converts bytecode to approximate, readable Java source.
temp_jar="$(mktemp --suffix=.jar)"
trap 'rm -f "$temp_jar"' EXIT
curl --fail --location --retry 3 --silent --show-error "$CFR_URL" -o "$temp_jar"
java -jar "$temp_jar" "$JAR" --outputdir decompiled-src --silent true --clobber true

classes=$(find extracted -type f -name '*.class' | wc -l | tr -d ' ')
java_files=$(find decompiled-src -type f -name '*.java' | wc -l | tr -d ' ')
resources=$(find extracted -type f ! -name '*.class' | wc -l | tr -d ' ')
if [[ "$java_files" -eq 0 ]]; then
  echo "Decompiler returned no Java source files" >&2
  exit 1
fi

cat > DECOMPILE_REPORT.md <<EOF
# KnightOnline v4.0.3 decompilation

Generated from \`KnightOnline_v403.jar\` using CFR ${CFR_VERSION}.

| Item | Count |
|---|---:|
| Original .class files | ${classes} |
| Decompiler-produced .java files | ${java_files} |
| Other extracted files | ${resources} |

- \`extracted/\`: **all** JAR contents, including bytecode and assets.
- \`decompiled-src/\`: Java reconstructed from the .class files.
- \`KnightOnline_v403.jar\`: unchanged original archive.
- \`tools/decompile.sh\`: reproducible extraction/decompilation script.

## Limitations

Decompiler output is approximate, not the original author-written source.
Names of local variables, comments, and exact control-flow layout may be lost.
Java ME CLDC/MIDP dependencies will be needed for any attempt to rebuild.
Do not assume the sources compile as-is.
EOF

echo "Unpacked: ${classes} class files and ${resources} non-class files"
echo "Recovered: ${java_files} Java source files"
