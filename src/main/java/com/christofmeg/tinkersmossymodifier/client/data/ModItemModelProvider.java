package com.christofmeg.tinkersmossymodifier.client.data;

import com.christofmeg.tinkersmossymodifier.TinkersMossyModifier;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersMossyModifier.MOD_ID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return TinkersMossyModifier.MOD_NAME + " - ItemModel";
    }

    @Override
    protected void registerModels() {
        basicItem(TinkersMossyModifier.rl("ball_of_moss"));
    }

}