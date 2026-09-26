package dev.anvilcraft.addon.applied_anvilstics.config;

import dev.anvilcraft.lib.v2.config.Config;

/**
 * 应用铁砧学的配置定义。
 * <p>
 * 字段会被 AnvilLib 的 Config 模块自动扫描并生成 ModConfigSpec，
 * 可通过 {@code @Comment} 补充注释、{@code @BoundedDiscrete} 限定数值范围、
 * {@code @CollapsibleObject} 嵌套分组。
 * <p>
 * 当前暂无配置项，新增字段时直接写成 public 非 final 字段并给默认值即可。
 */
@Config(name = "applied_anvilstics")
public class AAConfig {
}
