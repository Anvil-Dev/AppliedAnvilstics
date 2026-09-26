package dev.anvilcraft.addon.applied_anvilstics.event;

import appeng.api.AECapabilities;
import appeng.api.implementations.blockentities.ICraftingMachine;
import dev.dubhe.anvilcraft.init.block.ModBlockEntities;
import dev.anvilcraft.addon.applied_anvilstics.AppliedAnvilstics;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = AppliedAnvilstics.MODID)
public final class CapabilitiesEventListener {
    private CapabilitiesEventListener() {
    }

    @SubscribeEvent
    public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
        // AE2 只通过能力查找合成机器（ICraftingMachine.of -> getCapability），
        // AE2 自身也只为分子装配室注册。不注册这个能力，样板供应器就找不到批量合成器，
        // 合成样板无法推送，自动合成也就无法完成。
        event.registerBlockEntity(
            AECapabilities.CRAFTING_MACHINE,
            ModBlockEntities.BATCH_CRAFTER.get(),
            (blockEntity, side) -> blockEntity instanceof ICraftingMachine machine ? machine : null
        );
        // 批量切石机同样通过该能力接收 AE2 切石配方样板。
        event.registerBlockEntity(
            AECapabilities.CRAFTING_MACHINE,
            ModBlockEntities.BATCH_CUTTER.get(),
            (blockEntity, side) -> blockEntity instanceof ICraftingMachine machine ? machine : null
        );
    }
}
