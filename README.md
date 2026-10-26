<div align="center">

<img src="src/main/resources/assets/applied_anvilstics/textures/icon.png" width="256" height="256" alt="应用铁砧学图标">

# 应用铁砧学

**让《铁砧工艺》的加工方式接入《应用能源2》自动合成的铁砧工艺扩展。**

[English](README_en.md) | 简体中文

</div>

《应用铁砧学》（Applied Anvilstics）是 [铁砧工艺（AnvilCraft）](https://github.com/Anvil-Dev/AnvilCraft) 的 NeoForge
附属模组，用于打通铁砧工艺与 [应用能源2（Applied Energistics 2）](https://github.com/AppliedEnergistics/Applied-Energistics-2)
之间的自动化链路。

## 功能特性

### 批量合成器接入 AE2 自动合成

- 铁砧工艺的 **批量合成器**与 **批量切石机**现在会作为 AE2 的分子装配室（合成机器）参与自动合成，样板供应器可直接把合成样板与切石样板推送进来。

### 运行时自动适配 AE2 配方

本模组会在数据包加载完成、最终配方表形成后扫描 AE2 及其附属模组的配方，并自动转换成对应的铁砧工艺配方，不需要为每个模组逐条手写兼容配方。

| AE2 配方类型          | 转换后的铁砧工艺配方                        | 说明                              |
|-------------------|-----------------------------------|---------------------------------|
| `ae2:inscriber`   | `anvilcraft:item_crush`           | 没有上/下模具时，按粉碎处理                  |
| `ae2:inscriber`   | `anvilcraft:stamping`             | 有模具时按冲压处理；`inscribe` 保留模具，`press` 消耗模具 |
| `ae2:charger`     | `anvilcraft:charger_charging`     | 自动生成充能配方                        |
| `ae2:transform`   | `anvilcraft:solid_liquid`         | 仅转换流体环境，不处理爆炸环境                 |

- 扫描对象是最终 `RecipeManager`，因此 Extended AE、Advanced AE 等附属模组只要复用上述 AE2 配方类型，就会自动获得兼容，不存在配方加载先后问题。
- 自动生成的配方 ID 形如 `applied_anvilstics:generated/ae2/<type>/<namespace>/<path>`，同一次加载中保持稳定。
- 不同模组添加的新配方会在每次数据包重载时重新扫描并生成。

以下情况不会被自动转换：

- 爆炸环境中的 `ae2:transform` 配方。
- 带有动态组件、NBT 或动态频率的产物，例如量子纠缠奇点。
- 附属模组自己的专用配方类型，例如晶体装配机、反应仓、电路切割机等。这类配方仍按需单独适配。

## 环境要求

- [铁砧工艺 AnvilCraft](https://modrinth.com/mod/anvilcraft)
- [应用能源2 Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2)

可选兼容：Extended AE、Advanced AE 等复用 AE2 配方类型的附属模组。

## 安装

1. 安装 对应版本的Minecraft 与对应版本的 NeoForge。
2. 将铁砧工艺、应用能源2 及其前置模组放入 `mods` 目录。
3. 将本模组的 jar 放入 `mods` 目录。

## 许可证

本项目基于 [GNU LGPL-3.0](LICENSE) 授权。
