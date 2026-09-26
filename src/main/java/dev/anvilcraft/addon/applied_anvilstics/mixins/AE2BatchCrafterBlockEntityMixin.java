package dev.anvilcraft.addon.applied_anvilstics.mixins;

import appeng.api.crafting.IPatternDetails;
import appeng.api.implementations.blockentities.ICraftingMachine;
import appeng.api.implementations.blockentities.PatternContainerGroup;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.KeyCounter;
import appeng.blockentity.crafting.IMolecularAssemblerSupportedPattern;
import appeng.crafting.pattern.AECraftingPattern;
import dev.dubhe.anvilcraft.api.DeferTaskSubmittable;
import dev.anvilcraft.addon.applied_anvilstics.api.DeferredTaskQueue;
import dev.dubhe.anvilcraft.block.entity.batch.BatchCrafterBlockEntity;
import dev.dubhe.anvilcraft.init.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

@Mixin(BatchCrafterBlockEntity.class)
public abstract class AE2BatchCrafterBlockEntityMixin
    extends BlockEntity
    implements ICraftingMachine, IMolecularAssemblerSupportedPattern.CraftingGridAccessor,
    DeferTaskSubmittable<BatchCrafterBlockEntity>, DeferredTaskQueue<BatchCrafterBlockEntity> {

    @Shadow
    @Final
    private CraftingContainer craftingContainer;

    @Unique
    private final Deque<Consumer<BatchCrafterBlockEntity>> appliedanvilstics$deferredTasks = new ArrayDeque<>();

    @Unique
    private BaseBatchCraftingBlockEntityAccessor appliedanvilstics$baseAccessor() {
        return (BaseBatchCraftingBlockEntityAccessor) (Object) this;
    }

    public AE2BatchCrafterBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public PatternContainerGroup getCraftingMachineInfo() {
        Component name = ModBlocks.BATCH_CRAFTER.asItem().getDescription();
        AEItemKey icon = AEItemKey.of(ModBlocks.BATCH_CRAFTER);
        return new PatternContainerGroup(icon, name, List.of());
    }

    @Override
    public boolean pushPattern(
        IPatternDetails patternDetails,
        KeyCounter[] inputs,
        Direction ejectionDirection
    ) {
        var handler = this.appliedanvilstics$baseAccessor().appliedanvilstics$getHandler();
        if (patternDetails instanceof AECraftingPattern pattern && handler.isEmpty()) {
            pattern.fillCraftingGrid(inputs, this);
            if (this.level == null) return true;
            if (this.level.isClientSide) {
                this.setChanged();
            } else {
                this.level.blockEntityChanged(worldPosition);
                this.anvilcraft$submitTask(it -> {
                    ItemStack result = pattern.assemble(this.craftingContainer.asCraftInput(), level);
                    if (result.isEmpty()) return;
                    this.appliedanvilstics$baseAccessor()
                        .appliedanvilstics$invokeEjectItems(result, List.of(), ejectionDirection);
                    int amount = Math.toIntExact(result.getCount() / pattern.getOutputs().getFirst().amount());
                    for (int i = 0; i < handler.getSlots(); i++) {
                        handler.extractItem(i, amount, false);
                    }
                });
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean acceptsPlans() {
        return this.appliedanvilstics$baseAccessor().appliedanvilstics$getHandler().isEmpty();
    }

    @Override
    public void set(int slot, ItemStack stack) {
        this.appliedanvilstics$baseAccessor().appliedanvilstics$getHandler().setStackInSlot(slot, stack);
    }

    @Override
    public void anvilcraft$submitTask(Consumer<BatchCrafterBlockEntity> fn) {
        this.appliedanvilstics$deferredTasks.add(fn);
    }

    @Override
    public void appliedanvilstics$runDeferredTasks() {
        BatchCrafterBlockEntity self = (BatchCrafterBlockEntity) (Object) this;
        while (!this.appliedanvilstics$deferredTasks.isEmpty()) {
            this.appliedanvilstics$deferredTasks.poll().accept(self);
        }
    }
}