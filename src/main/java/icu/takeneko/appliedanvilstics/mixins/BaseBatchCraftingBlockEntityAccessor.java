package icu.takeneko.appliedanvilstics.mixins;

import dev.dubhe.anvilcraft.api.itemhandler.PollableFilteredItemStackHandler;
import dev.dubhe.anvilcraft.block.entity.batch.BaseBatchCraftingBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(BaseBatchCraftingBlockEntity.class)
public interface BaseBatchCraftingBlockEntityAccessor {
    @Accessor("handler")
    PollableFilteredItemStackHandler appliedanvilstics$getHandler();

    @Invoker("ejectItems")
    boolean appliedanvilstics$invokeEjectItems(
        ItemStack result,
        List<ItemStack> craftRemaining,
        Direction direction
    );
}
