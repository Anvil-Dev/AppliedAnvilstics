package dev.anvilcraft.addon.applied_anvilstics;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

/**
 * 客户端专用入口。
 * <p>
 * 配置界面（{@code ConfigurationScreen}）已由 AnvilLib 的 Config 模块在客户端自动注册，
 * 此处不再重复注册，保留空入口以便后续添加客户端专用初始化逻辑。
 */
@Mod(value = AppliedAnvilstics.MODID, dist = Dist.CLIENT)
public class AppliedAnvilsticsClient {
}
