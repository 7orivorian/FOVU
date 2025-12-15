# FOVU - _FoV Unbound!_

<!-- formatter:off -->
![a crudely drawn image of the words "fov unbound"](./assets/icon_256.png)

[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/fovu)
[![fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/fabric_vector.svg)](https://modrinth.com/mod/fovu)
[![kofi-singular](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/kofi-singular_vector.svg)](https://7ori.dev/ko-fi)
<!-- formatter:on -->

## Features

Increases the minimum and maximum values of the vanilla Field of View slider,
allowing any FoV from **1** all the way to **170**!

FOVU also sets limits on the minimum and maximum Field of View after any game
modifiers—like bows, speed potions, or sprinting—are applied. This prevents
the FoV from becoming too low (which makes the screen turn white) or too high
(which could flip the camera upside down).

## FAQ

**Q:** Where are the settings?<br>
**A:** FOVU only modifies the vanilla FoV slider and does not add any
additional settings of its own.

**Q:** Why the 170 FoV cap? Isn't 179 the maximum FoV before the camera
flips?<br>
**A:** It _is_ correct that 179 is the absolute maximum before the camera flips
upside down. However, an FoV in the range [171, 189] can allow players to easily
see through walls, floors, and ceilings, which may be considered **cheating** on
some servers, thus, the 170 FoV cap.

**Q:** Where do I install this mod?<br>
**A:** FOVU is a client-side-only mod and will do nothing if installed on the
server.

**Q:** May I use this mod in my modpack?<br>
**A:** Absolutely! You may freely use an unmodified version of this mod in any
modpacks/videos/etc without contacting me for approval. I would greatly
appreciate a link back to this Modrinth page if you do use my mod!

## Where's the Code?

The source code is split by Minecarft version via Git branches for easier
maintenance.