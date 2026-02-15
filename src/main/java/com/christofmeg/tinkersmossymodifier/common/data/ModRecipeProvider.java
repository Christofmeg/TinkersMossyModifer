package com.christofmeg.tinkersmossymodifier.common.data;

import com.christofmeg.tinkersmossymodifier.TinkersMossyModifier;
import com.christofmeg.tinkersmossymodifier.common.ModRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IRecipeHelper {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public @NotNull String getModId() {
        return TinkersMossyModifier.MOD_ID;
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String upgradeFolder = "tools/modifiers/upgrade/";

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModRegistries.BALL_OF_MOSS.get())
                .define('m', Tags.Items.COBBLESTONE_MOSSY)
                .pattern("mmm")
                .pattern("mmm")
                .pattern("mmm").unlockedBy("has_moss", has(Tags.Items.COBBLESTONE_MOSSY))
                .save(consumer, TinkersMossyModifier.rl("ball_of_moss"));

        ModifierRecipeBuilder.modifier(ModRegistries.AUTO_REPAIR_MODIFIER)
                .setTools(TinkerTags.Items.DURABILITY)
                .addInput(ModRegistries.BALL_OF_MOSS.get())
                .setSlots(SlotType.UPGRADE, 1)
                .setMaxLevel(3)
                .saveSalvage(consumer, prefix(ModRegistries.AUTO_REPAIR_MODIFIER, upgradeSalvage))
                .save(consumer, prefix(ModRegistries.AUTO_REPAIR_MODIFIER, upgradeFolder));
    }

}
