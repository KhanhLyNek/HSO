# Auto Di Buon (experimental)

This module lives on `feature/training-assistant-20261013`. It does not modify `main`.

## What is confirmed in the HSO client

- `GameObjects.Monstervantieu` models trade pack animals (white catalog IDs 127/129/131, black catalog IDs 128/130/132; guild animals 177/176).
- `ReadMessenge.monsterInfo` updates pack animal ownership (`nameowner`), HP and speed from server messages.
- `ReadMessenge.npcInfo` handles a `SHOP_VANTIEU` shop category.
- Native pathfinding uses `GameScreen.updateFindRoad`; native map-change packets carry map ID and `LoadMap.Area`.
- Buying and selling remain **manual** until exact merchant NPC/shop operations have been verified with the real server.
- The tool does **not** fake packet commands or automate CAPTCHA.

## Game menu

`Menu chinh -> Auto Di Buon`:

1. **Ghi tuyen mua-den-ban**: stand at a starting merchant (for example Dylan) and start recording.
2. Manually buy cargo, lead your pack animal through your desired route, and manually cross map gates.
3. **Ket thuc ghi tuyen**: press when near the destination selling merchant (for example Graham).
4. Return to the original departure point with a live pack animal.
5. **Bat dau dan hang**: the client follows the captured waypoints, keeping the owned pack animal within a configurable distance.
6. At each inter-map gate, the tool **pauses** and asks you to cross normally; it resumes after the server confirms the *expected* map and area.
7. On arrival, the tool stops and prompts you to sell through the ordinary NPC shop.

Other options: **Dung di buon**, **Trang thai di buon**, **Giu khoang cach thu (96/144/192)**, **Luu NPC mua hang**, **Luu NPC ban hang**.

NPC saving currently records the selected NPC's identifier for future use; it does not initiate commerce automatically.

## Safeguards

- Rejects players below level 40.
- Only follows the white trader convoy owned by the currently logged-in character.
- Stops on death or wrong map/area, invalid pathfinding, or missing pack animals for over 10 seconds.
- Waits for monster info packet before trusting the animal's HP; warns at less than 25% health.
- Pauses whenever the animal is too far away or the UI has an open game menu/dialog.
- Warns after 23 minutes into the automated leg. The game's reported 30-minute animal lifetime is **not** directly verified by client state.
- Only stores 400 waypoints **in memory** (the route is lost when the client restarts).
- Does not control gateway transitions autonomously or buy/sell goods yet.

## Tests

The pure Java route recorder has an isolated executable test:

```sh
javac -d /tmp/trade-build sources/tools/trading/TradeRoute.java tests/tools/trading/TradeRouteTest.java
java -cp /tmp/trade-build tools.trading.TradeRouteTest
```

The route test checks waypoint deduplication, exit markers, map/area changes and capacity. It is *not* an in-game integration test.

## Manual in-game test checklist

- [ ] Enable menu on mobile / Java ME emulator.
- [ ] Record a short same-map path and replay it with a live, owned pack animal.
- [ ] Keep distance over the configured limit; movement should pause until the animal catches up.
- [ ] Verify that killing/losing the pack animal or player stops the tool.
- [ ] Record a multi-map route, cross gate manually, and confirm map/area matching resumes navigation.
- [ ] Test incomplete route, blocked path, uninitialized pack info, timed out map transition, and different character.
- [ ] Verify merchant NPC identifiers and final buy/sell steps using real server messages.
- [ ] Build the full Java ME project (the decompiled source may need baseline fixes).

## Next implementation stages

1. Record and replay confirmed map transitions, with edge/collision semantics validated per server.
2. Identify the actual Dylan and Graham server menus and support explicitly confirmed purchase/sale choices.
3. Add merchant pack-skill UI (protection/heal/speed) using proven commands only.
4. Persist named routes and add a non-invasive trade profit/time log.
5. Test a complete journey and package a working client after compile fixes.
