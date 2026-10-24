# HSO — KnightOnline v4.0.3

Repository for analyzing the Java ME KnightOnline client.

## Browse contents

- [Original KnightOnline_v403.jar](KnightOnline_v403.jar) — preserved unchanged.
- [Recovered Java source (`decompiled-src/`)](decompiled-src/) — **195** Java files produced by CFR 0.152 from **202** class files; some nested classes are merged into parent Java files.
- [Extracted archive contents (`extracted/`)](extracted/) — **202** original class files and **728** other files (assets, manifests, configuration, etc.).
- [Decompilation report](DECOMPILE_REPORT.md).
- [How to rerun / caveats](DECOMPILATION.md).
- [Reproducible shell script](tools/decompile.sh).
- [GitHub Actions workflow](.github/workflows/decompile.yml).

Useful entry points:
- [Network session](decompiled-src/CLib/Session_ME.java)
- [Message structure](decompiled-src/net/Message.java)
- [Client outgoing commands](decompiled-src/netcommand/global/GlobalService.java)
- [Incoming command handler](decompiled-src/netcommand/global/GlobalMessageHandler.java)

> **Note:** This is automatically decompiled code, not the original author-written source; Java ME (CLDC/MIDP) dependencies are required for rebuilding.
