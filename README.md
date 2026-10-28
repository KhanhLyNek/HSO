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

## Level-aware Auto Farm (source-only prototype)

Auto Farm is now a general mode rather than a hard-coded dog-field route.

- The game menu and the Auto menu expose **Bat Auto Farm / Tat Auto Farm**.
- On activation, `AutoFarmController` reads `GameScreen.player.Lv` and the current map.
- It selects a non-decorative monster-spawn entry of **exactly the player's level**, if reachable; if none exists, it falls back to a monster at most **2 levels below**.
- `FarmWorldData` contains 120 map/monster spawn entries and 170 directed map portals obtained from the public HSO server SQL snapshot. Multiple disconnected spawn packs are stored as separate centers.
- A graph search chooses connected maps and actual portal coordinates; a tile search uses the current collision map to walk to the selected spawn area, with normal client motion and packet command 4 through `GlobalService`/`Session_ME`.
- Once at the spawn, it enables the existing `Player.dofire_()` auto-combat logic and restricts target selection to the chosen monster template. On level-up it re-evaluates the target and route.
- It stops on disconnection, character death, an unresponsive portal, or persistent stuck movement.

**Scope and limits:** This is a modification to the **decompiled Java sources**, not to the preserved JAR. It has **not** been compiled/rebuilt into a usable MIDP JAR or verified against a live game server. The source server's spawn and portal definitions may differ from other HSO servers; some high-level regions are disconnected from starting villages or require NPC teleporters, quests, permissions, or special transitions. The tool does not bypass those requirements, send instant teleport packets, or automate NPC dialog flows. Skill availability and combat success are controlled by the existing client/server logic.

Files: [AutoFarmController.java](decompiled-src/Tool/AutoFarmController.java), [FarmWorldData.java](decompiled-src/Tool/FarmWorldData.java), [Player.java](decompiled-src/GameObjects/Player.java), [GameScreen.java](decompiled-src/GameScreen/GameScreen.java).
