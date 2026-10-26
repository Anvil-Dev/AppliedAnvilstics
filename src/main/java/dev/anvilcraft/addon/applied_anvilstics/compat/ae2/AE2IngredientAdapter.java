package dev.anvilcraft.addon.applied_anvilstics.compat.ae2;

import dev.anvilcraft.lib.v2.util.predicate.ItemIngredientPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.Optional;

final class AE2IngredientAdapter {
    private AE2IngredientAdapter() {
    }

    static Optional<ItemIngredientPredicate> toPredicate(Ingredient ingredient) {
        if (ingredient.isEmpty()) return Optional.empty();
        ItemStack[] stacks = ingredient.getItems();
        if (stacks.length == 0) return Optional.empty();

        if (stacks.length == 1) {
            return Optional.of(ItemIngredientPredicate.Builder.item()
                .of(stacks[0])
                .withCount(1)
                .build());
        }

        // 多项候选无法用单个 ItemStack 表达组件条件，带组件的配方交给专用适配器处理。
        if (Arrays.stream(stacks).anyMatch(stack -> !stack.getComponentsPatch().isEmpty())) {
            return Optional.empty();
        }

        Item[] items = Arrays.stream(stacks)
            .map(ItemStack::getItem)
            .distinct()
            .toArray(Item[]::new);
        if (items.length == 0) return Optional.empty();

        return Optional.of(ItemIngredientPredicate.Builder.item()
            .of(items)
            .withCount(1)
            .build());
    }

    static Optional<ItemStack> toPreservedStack(Ingredient ingredient) {
        if (ingredient.isEmpty()) return Optional.empty();
        ItemStack[] stacks = ingredient.getItems();
        if (stacks.length != 1) return Optional.empty();
        return Optional.of(stacks[0].copyWithCount(1));
    }
}
