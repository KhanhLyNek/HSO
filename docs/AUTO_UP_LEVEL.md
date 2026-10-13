# Auto UP Level (experimental)

This feature is developed only on branch `feature/training-assistant-20261013`.

## Menu

In game: **Menu -> Chuc nang -> Luyen cap / Auto UP Level**.

- **Bat/Tat luyen cap**: records the map, area, and starting coordinates, starts the native Auto Fire controller.
- **Tu ve bai: BAT/TAT**: allows return to the recorded training area after death (default ON).
- **Luu NPC dich chuyen**: select/focus the teleport-stone NPC in the village first; use this setting to record its ID if its name does not contain "dich chuyen".
- **Pham vi**: cycles the maximum monster selection distance (120/180/280).
- **Xem trang thai**: displays recovery state and last saved coordinates.

## Recovery state machine

TRAIN -> HOME -> STONE -> STONE_MENU -> MAP_WAIT -> SPOT -> TRAIN

1. On the first death, remember the **actual position at death**, map id and area. Stop auto-attacking.
2. Send the game's native `gohome(0)` command once. Wait for server-confirmed alive/village state.
3. Locate a suitable teleport NPC (by remembered ID or name), navigate using the built-in pathfinder and request the NPC's original server menu.
4. Select a teleport destination **only when exactly one option matches the recorded map name**. An unambiguous generic teleport action may be used to open one further server menu.
5. Wait until the server changes to the recorded map. Change area using the original area-change command if needed.
6. Find a path back to the recorded death coordinates. Resume native Auto Fire only after reaching the spot.

## Fail-safe behavior

No invented protocol IDs, captcha automation, rapid packet retries, or blind index selection. Auto mode stops on unknown/ambiguous teleport menus, path failures, unsupported map modes, or server timeouts. It pauses attacking when dialogs or another menu are open. A maximum of two repeated deaths during a return is allowed.

## Test checklist (must be tested in a running client)

- [ ] Auto training toggles correctly and respects monster distance.
- [ ] Death captures map/area/death coordinates and sends one village-return packet.
- [ ] Village NPC is discovered by name; explicit selected NPC fallback works.
- [ ] Server displays a dynamic destination menu, matching the intended destination.
- [ ] Teleport produces a server-confirmed map change and returns to the correct area.
- [ ] Character navigates to the recorded coordinates and resumes attack.
- [ ] Missing NPC, missing route, wrong destination, timed-out menu and repeated deaths safely stop.
- [ ] Captcha or authentication challenges are not bypassed.
- [ ] Existing Java ME project compiles and runs on a representative emulator/device.

**Status:** source integrated, but full build and in-game validation have not yet been completed. Teleport stone specifics are server-dependent; unknown menu labels are intentionally not auto-selected.
