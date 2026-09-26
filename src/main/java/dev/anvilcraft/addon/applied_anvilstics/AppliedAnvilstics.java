package dev.anvilcraft.addon.applied_anvilstics;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.v2.config.ConfigManager;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import guideme.Guide;
import dev.anvilcraft.addon.applied_anvilstics.all.AACreativeTabs;
import dev.anvilcraft.addon.applied_anvilstics.all.AAItems;
import dev.anvilcraft.addon.applied_anvilstics.config.AAConfig;
import dev.anvilcraft.addon.applied_anvilstics.data.AADataGen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AppliedAnvilstics.MODID)
public class AppliedAnvilstics {
    public static final String MODID = "applied_anvilstics";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrum REGISTRUM = Registrum.create(MODID);
    /**
     * 配置实例。由 AnvilLib 的 Config 模块负责生成配置定义、注册到模组容器，
     * 并在客户端自动挂载配置界面。
     */
    public static final AAConfig CONFIG = ConfigManager.register(MODID, AAConfig::new);

    public AppliedAnvilstics(IEventBus modEventBus, ModContainer modContainer) {
        setupRegistration(modEventBus);
        AADataGen.setupDataGeneration(REGISTRUM);
    }

    public static void setupRegistration(IEventBus modBus) {
        AACreativeTabs.DR.register(modBus);
        AAItems.setupRegistration();
        Guide.builder(location("guide"))
            .folder("aaguide")
            .register(true)
            .build();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}