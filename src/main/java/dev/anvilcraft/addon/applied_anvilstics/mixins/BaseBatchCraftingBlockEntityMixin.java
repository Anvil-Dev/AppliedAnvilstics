package dev.anvilcraft.addon.applied_anvilstics.mixins;

import dev.dubhe.anvilcraft.block.entity.batch.BaseBatchCraftingBlockEntity;
import dev.anvilcraft.addon.applied_anvilstics.api.DeferredTaskQueue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseBatchCraftingBlockEntity.class)
abstract class BaseBatchCraftingBlockEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    void appliedanvilstics$runDeferredTasks(Level level, BlockPos pos, CallbackInfo ci) {
        if (this instanceof DeferredTaskQueue<?> queue) {
            queue.appliedanvilstics$runDeferredTasks();
        }
    }
}