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
- 产物由机器弹出，输入按本次消耗扣除，行为与分子装配室保持一致。
- 两种方块均通过 AE2 的 `CRAFTING_MACHINE` 能力注册，样板供应器可以正常识别。

### 铁砧工艺加工 AE2 系列材料

铁砧工艺的加工方式现在可以处理 AE2、Extended AE 与 Advanced AE 的材料：

| 加工方式              | 内容                                                                                                                     |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------|
| 压印 Stamping         | 硅的压印与印刷，逻辑／计算／工程处理器的压印、印刷与成型，以及 Advanced AE 量子处理器与 Extended AE 并发处理器的对应配方 |
| 粉碎 Item Crush       | 福鲁伊克斯粉、赛特斯石英粉、陨石粉、末影粉，以及 Extended AE / Advanced AE 的粉尘                                        |
| 充能 Charger Charging | 充能赛特斯石英水晶、陨石罗盘，以及用书本充能获得 AE2 指南                                                                |
| 固液反应 Solid–Liquid | 在装水的炼药锅中回收赛特斯石英粉与福鲁伊克斯粉、合成福鲁伊克斯水晶，以及逐级修复赛特斯石英母岩                           |

Extended AE 与 Advanced AE 的配方均带有 `neoforge:mod_loaded` 条件，未安装对应模组时不会加载。

## 环境要求

| 项目      | 版本            |
|-----------|-----------------|
| Minecraft | 1.21.1          |
| NeoForge  | 21.1.241 或更高 |
| Java      | 21              |

运行时还需要：

- [铁砧工艺 AnvilCraft](https://modrinth.com/mod/anvilcraft)
- [应用能源2 Applied Energistics 2](https://github.com/AppliedEnergistics/Applied-Energistics-2)
- AnvilLib（铁砧工艺的前置库）

可选兼容：Extended AE、Advanced AE。

## 安装

1. 安装 Minecraft 1.21.1 与对应版本的 NeoForge。
2. 将铁砧工艺、应用能源2 及其前置模组放入 `mods` 目录。
3. 将本模组的 jar 放入 `mods` 目录。

## 构建

项目使用 Gradle Wrapper，无需另行安装 Gradle：

```bash
./gradlew build         # 构建模组
./gradlew runClient     # 启动开发客户端
./gradlew runServer     # 启动开发服务端
./gradlew runData       # 运行数据生成，输出到 src/generated/resources
```

Windows 下请改用 `gradlew.bat`。构建产物位于 `build/libs/`。

## 项目结构

```
src/main/java/dev/anvilcraft/addon/applied_anvilstics/
├── AppliedAnvilstics.java   模组主入口
├── all/                     注册（物品、创造模式标签页）
├── api/                     延迟任务队列
├── config/                  配置定义
├── data/                    数据生成（配方、标签、语言）
├── event/                   能力注册
├── item/                    物品实现
└── mixins/                  Mixin 注入
src/main/resources/
├── applied_anvilstics.mixins.json
├── assets/applied_anvilstics/   资源：图标、语言、游戏内指南
└── data/applied_anvilstics/     可选兼容配方
src/generated/resources/     数据生成产物
gradle/scripts/              构建脚本
```

## 相关链接

- [GitHub 仓库](https://github.com/Anvil-Dev/AppliedAnvilstics)
- [问题反馈](https://github.com/Anvil-Dev/AppliedAnvilstics/issues)
- [铁砧工艺 AnvilCraft](https://github.com/Anvil-Dev/AnvilCraft)
- [应用能源2 源码](https://github.com/AppliedEnergistics/Applied-Energistics-2)
- [AnvilCraft 文档](https://www.anvilcraft.dev/)
- [AnvilLib](https://lib.anvilcraft.dev)
- [NeoForge 文档](https://docs.neoforged.net/)
- [NeoForged Discord](https://discord.neoforged.net/)

## 许可证

本项目基于 [GNU LGPL-3.0](LICENSE) 授权。
