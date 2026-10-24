# KnightOnline v4.0.3 — decompiled sources

This repo retains `KnightOnline_v403.jar` at its root.

Generated directories:
- [`extracted/`](extracted/) contains the **original uncompressed archive contents** (images, metadata, binary assets and `.class` bytecode).
- [`decompiled-src/`](decompiled-src/) contains **approximate Java source** reconstructed from bytecode with CFR.
- [`DECOMPILE_REPORT.md`](DECOMPILE_REPORT.md) records actual output counts and caveats.

The decompilation script is [`tools/decompile.sh`](tools/decompile.sh); run it with Java, curl and unzip installed. The [GitHub Actions workflow](.github/workflows/decompile.yml) runs on main when the JAR, script, or workflow changes and commits generated files back to main.

**Important:** Java decompilation does not restore the exact original source, comments or variable names. The project targets Java ME (CLDC 1.0 / MIDP 2.0), and may require extra APIs to rebuild.
