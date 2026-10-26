package dev.anvilcraft.addon.applied_anvilstics.compat.ae2;

import appeng.recipes.handlers.ChargerRecipe;
import dev.dubhe.anvilcraft.recipe.ChargerChargingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Optional;

final class AE2ChargerAdapter {
    private static final int CHARGER_POWER_KW = -8;
    private static final int AE_PER_KW_TICK = 50;
    private static final int AE_CHARGER_ENERGY_PER_OPERATION = 1600;
    private static final int CHARGE_TIME =
        Math.ceilDiv(AE_CHARGER_ENERGY_PER_OPERATION, -CHARGER_POWER_KW * AE_PER_KW_TICK);

    private AE2ChargerAdapter() {
    }

    static Optional<RecipeHolder<?>> adapt(
        ResourceLocation sourceId,
        ChargerRecipe recipe
    ) {
        if (recipe.getIngredient().isEmpty()) return Optional.empty();

        ItemStack result = recipe.getResultItem().copy();
        if (result.isEmpty()) return Optional.empty();

        ChargerChargingRecipe charging = new ChargerChargingRecipe(
            recipe.getIngredient(),
            result,
            CHARGER_POWER_KW,
            CHARGE_TIME
        );
        return Optional.of(new RecipeHolder<>(
            AE2RecipeRuntimeAdapter.generatedId("charger", sourceId),
            charging
        ));
    }
}
