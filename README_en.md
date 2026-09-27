<div align="center">

<img src="src/main/resources/assets/applied_anvilstics/textures/icon.png" width="256" height="256" alt="Applied Anvilstics icon">

# Applied Anvilstics

**An AnvilCraft add-on that brings AnvilCraft processing into Applied Energistics 2 autocrafting.**

English | [简体中文](README.md)

</div>

Applied Anvilstics is a NeoForge add-on for [AnvilCraft](https://github.com/Anvil-Dev/AnvilCraft) that connects
AnvilCraft with [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2) for automation.

## Features

### Batch crafters as AE2 crafting machines

- AnvilCraft's **Batch Crafter** and **Batch Cutter** now act as molecular assemblers (crafting machines) for AE2
  autocrafting, so pattern providers can push crafting and stonecutting patterns into them.
- Results are ejected by the machine and inputs are consumed per operation, matching molecular assembler behavior.
- Both blocks register the AE2 `CRAFTING_MACHINE` capability so pattern providers can discover them.

### AnvilCraft processing for AE2-family materials

AnvilCraft processing methods can now handle materials from AE2, Extended AE and Advanced AE:

| Processing       | Coverage                                                                                                                                                                                                       |
|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stamping         | Silicon pressing and printing; logic, calculation and engineering processor pressing, printing and forming; plus the matching recipes for Advanced AE quantum processors and Extended AE concurrent processors |
| Item Crush       | Fluix dust, certus quartz dust, sky stone dust, ender dust, and the dusts of Extended AE / Advanced AE                                                                                                         |
| Charger Charging | Charged certus quartz crystal, meteorite compass, and charging a book into the AE2 guide                                                                                                                       |
| Solid–Liquid     | In a water cauldron: recycling certus quartz dust and fluix dust, crafting fluix crystals, and repairing budding quartz block by block                                                                         |

Extended AE and Advanced AE recipes carry a `neoforge:mod_loaded` condition, so they are skipped when the corresponding
mod is absent.

## Requirements

| Component | Version           |
|-----------|-------------------|
| Minecraft | 1.21.1            |
| NeoForge  | 21.1.241 or newer |
| Java      | 21                |

At runtime you also need:

- [AnvilCraft](https://modrinth.com/mod/anvilcraft)
- [Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2)
- AnvilLib (AnvilCraft's library dependency)

Optional compatibility: Extended AE, Advanced AE.

## Installation

1. Install Minecraft 1.21.1 with a matching NeoForge version.
2. Put AnvilCraft, Applied Energistics 2 and their dependencies into the `mods` directory.
3. Put this mod's jar into the `mods` directory.

## Building

The project ships a Gradle Wrapper, so no separate Gradle install is needed:

```bash
./gradlew build         # build the mod
./gradlew runClient     # launch the development client
./gradlew runServer     # launch the development server
./gradlew runData       # run data generation into src/generated/resources
```

On Windows use `gradlew.bat` instead. Build artifacts land in `build/libs/`.

## Project layout

```
src/main/java/dev/anvilcraft/addon/applied_anvilstics/
├── AppliedAnvilstics.java   Mod entry point
├── all/                     Registration (items, creative tab)
├── api/                     Deferred task queue
├── config/                  Configuration definition
├── data/                    Data generation (recipes, tags, lang)
├── event/                   Capability registration
├── item/                    Item implementations
└── mixins/                  Mixin injections
src/main/resources/
├── applied_anvilstics.mixins.json
├── assets/applied_anvilstics/   Assets: icon, lang, in-game guide
└── data/applied_anvilstics/     Optional-compat recipes
src/generated/resources/     Data generation output
gradle/scripts/              Build scripts
```

## Links

- [GitHub repository](https://github.com/Anvil-Dev/AppliedAnvilstics)
- [Issue tracker](https://github.com/Anvil-Dev/AppliedAnvilstics/issues)
- [AnvilCraft](https://github.com/Anvil-Dev/AnvilCraft)
- [Applied Energistics 2 source](https://github.com/AppliedEnergistics/Applied-Energistics-2)
- [AnvilCraft documentation](https://www.anvilcraft.dev/)
- [AnvilLib](https://lib.anvilcraft.dev)
- [NeoForge documentation](https://docs.neoforged.net/)
- [NeoForged Discord](https://discord.neoforged.net/)

## License

This project is licensed under the [GNU LGPL-3.0](LICENSE).
