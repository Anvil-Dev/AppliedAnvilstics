<div align="center">

<img src="src/main/resources/assets/applied_anvilstics/textures/icon.png" width="256" height="256" alt="Applied Anvilstics icon">

# Applied Anvilstics

**A NeoForge add-on that connects AnvilCraft processing methods to Applied Energistics 2 autocrafting.**

English | [简体中文](README.md)

</div>

Applied Anvilstics is a NeoForge add-on for [AnvilCraft](https://github.com/Anvil-Dev/AnvilCraft) that connects
AnvilCraft with [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2) for automation.

## Features

### Batch Crafters as AE2 Crafting Machines

- AnvilCraft's **Batch Crafter** and **Batch Cutter** now participate in AE2 autocrafting as molecular assemblers (crafting machines). Pattern providers can push crafting patterns and stonecutting patterns directly into them.

### Runtime AE2 Recipe Adaptation

After the datapack finishes loading and the final recipe manager is built, this mod scans recipes from AE2 and its add-ons and converts them into AnvilCraft recipes. Compatibility recipes no longer need to be written by hand for every add-on.

| AE2 recipe type   | Converted AnvilCraft recipe       | Notes                                                       |
|-------------------|-----------------------------------|-------------------------------------------------------------|
| `ae2:inscriber`   | `anvilcraft:item_crush`           | Used when there is no top/bottom press                       |
| `ae2:inscriber`   | `anvilcraft:stamping`             | Used with presses; `inscribe` keeps them, `press` consumes them |
| `ae2:charger`     | `anvilcraft:charger_charging`     | Charging recipes are generated automatically                 |
| `ae2:transform`   | `anvilcraft:solid_liquid`         | Fluid circumstances only; explosion recipes are excluded     |

- Recipes are scanned from the final `RecipeManager`. Any add-on that reuses these AE2 recipe types, including Extended AE and Advanced AE, is handled automatically regardless of datapack or mod load order.
- Generated recipe IDs follow `applied_anvilstics:generated/ae2/<type>/<namespace>/<path>` and remain stable for a given load.
- New recipes from other mods are scanned again whenever the datapack is reloaded.

The following are not converted automatically:

- `ae2:transform` recipes that require an explosion.
- Results that rely on dynamic components, NBT, or dynamically assigned frequencies, such as quantum entangled singularities.
- Add-on-specific recipe types such as the Crystal Assembler, Reaction Chamber, or Circuit Cutter. These still require targeted compatibility where needed.

## Requirements

- [AnvilCraft](https://modrinth.com/mod/anvilcraft)
- [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2)

Optional compatibility: add-ons such as Extended AE and Advanced AE that reuse AE2 recipe types.

## Installation

1. Install matching versions of Minecraft and NeoForge.
2. Put AnvilCraft, Applied Energistics 2, and their dependencies in the `mods` directory.
3. Put this mod's jar in the `mods` directory.

## License

This project is licensed under the [GNU LGPL-3.0](LICENSE).
