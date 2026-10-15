# LyMeowz HSO — current implementation and validation

## Game-side features

LyMeowz keeps the original HSO/J2ME UI; it does not install an outside hack menu.
Open the LyMeowz menu via the native main function menu.

| Module | Menu / behavior | Status |
| --- | --- | --- |
| AI training | Enable "Level up your intelligence" | Source integrated |
| Quest NPC | Find NPC, auto navigate after a quest finishes | Source integrated |
| Area transfer | Uses the game's `Change_Area(byte)` | Source integrated; server validates |
| Map transitions | Learns observed physical gates; routes through map **and** area | Source integrated |
| RMS cache | Versioned local per-character NPC/map/area/portal graph | Source integrated |
| AI Director | One-shot quest handoff; no repeated opening of NPC menus | Source integrated |
| Respawn | Optional one-shot native `gohome` and return to last live checkpoint | Source integrated |
| Hunting | Selects quest monsters / safe nearby mobs, avoids low HP/MP | Source integrated |
| Training areas | Observes actual spawns and crowding, suggests/moves to a known area | Source integrated |
| Telemetry | Level-progress-per-hour estimate, kills, deaths, map hops and event journal | Source integrated |

### Important safety rules

- **Never synthesize teleport packets.** The NPC/map navigator only uses
  previously confirmed gates and the server's existing area-switch command.
- **Teleport stones must not teach portal edges.** A recently approached
  gate and a server-verified non-teleport map transition are both necessary.
- **No invented NPC or monster spawn maps.** Locations are learned only
  when the client sees actual objects.
- Recovery and training-area travel must yield to NPC quest navigation.
- If a route is unknown or the character gets stuck, the automation stops
  rather than repeatedly retrying packets.
- Returning a quest is still confirmed in the **game's native quest menu**;
  there is no blind server-side quest completion.
- The user must explicitly turn on autonomous actions via the LyMeowz menu.

## Build verification — these are not equivalent

1. **Java smoke build:** compilation against existing game class resources
   and in-repository unit tests. This is a JVM correctness gate only.
2. **J2ME source diagnostics:** a separate lightweight source check, *not* a
   runnable JAR.
3. **Real J2ME CLDC JAR build:** `scripts/build-j2me.sh` downloads pinned
   Eclipse ECJ / CLDC 1.0 / MIDP 2.0 API stubs (compile-time only), compiles
   changed client sources to Java 1.1 bytecode with CLDC `StackMap`,
   overlays original client resources, validates class versions, and creates
   `LyMeowz-HSO.jar` and `LyMeowz-HSO.jad`. The workflow uploads the
   artifact **only when all checks pass**.

Use GitHub Actions:
- `LyMeowz main Java smoke build`
- `HSO real CLDC JAR build (release candidate)`
- `HSO J2ME source diagnostics (NOT release)`

## Runtime crash fix: missing mount animation arrays

Observed Android/J2ME stack:
`GameObjects.MainObject.updateMount → MainObject.update → Other_Players.update`.

**Cause:** a remote character with `typeMount` may be updated before
`ReadMessenge.catalogyMonster()` has finished filling the static mount
animation arrays. Direct access to `MountTemplate.dx[typeMount][...]`,
`dy`, `DY_CHAR_*` and `FRAME_MOVE_*` can throw an NPE or array bounds error.

**Fixes committed:**
- `MountTemplate.hasAnimationData(type)` checks all required outer/inner
  rows, sizes and the optional type ID before animation.
- `MainObject.updateMount()` skips incomplete animation without clearing
  `typeMount`, and uses captured row pointers when they exist.
- `getVmount()`, `isMountFly()`, painting/front-frame checks are safe while
  the catalogue is absent. Missing animation resumes when it arrives.
- The actual `MainObject.class` and `MountTemplate.class` are now
  compiled into the CLDC JAR rather than relying on the stale resource classes.
- `tests/LyMeowzMountCrashTest.java` exercises missing arrays, invalid
  indices, incomplete rows and later animation recovery.

The J2ME build **does not prove** that every runtime path works on Android:
reinstall the newest artifact and re-enter a crowded map to test this exact
crash, ideally with several mounted players onscreen.

## Manual J2ME runtime tests still required

Building and preverification are not a substitute for running the MIDlet in
an emulator or compatible J2ME environment. Test at least:

1. Start/login and verify the native LyMeowz menu and character skin/UI.
2. Walk through an ordinary gate and check that the **correct map and area**
   are learned, then attempt the same using a teleport stone and confirm it
   does not create a false gate entry.
3. Visit an NPC *before* finishing a quest; complete the quest and verify
   that the correct map/area and NPC become the navigation destination.
4. Complete a quest; follow the learned route across multiple map hops and
   confirm the game opens its own NPC quest list exactly once.
5. Switch between areas on one map and verify the server's response.
6. Test low HP/MP, crowded and low-level mobs, empty spawns, and missing routes.
7. Enable recovery, die, revive, and verify the last checkpoint is restored
   without repeated resurrection packets.
8. Restart the client and verify the local RMS map cache loads properly;
   verify corrupt/unexpected data cannot crash login.
9. Inspect log and progression counters after at least 15 minutes of play.

**EXP/hour limitation:** this client exposes level and server-reported
`phantramLv` (level progress), not verified absolute earned EXP.
The displayed rate is therefore labeled as estimated level-progress/hour;
it must not be misrepresented as exact EXP/hour.

**Known limitation:** the monster sighting catalogue is session-only for
now; the persistent RMS cache covers verified NPC locations and gate routes.
