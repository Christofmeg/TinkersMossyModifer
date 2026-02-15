package com.christofmeg.tinkersmossymodifier.common.data;

import com.christofmeg.tinkersmossymodifier.TinkersMossyModifier;
import com.christofmeg.tinkersmossymodifier.client.data.ModItemModelProvider;
import com.christofmeg.tinkersmossymodifier.client.data.ModLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

@Mod.EventBusSubscriber(modid = TinkersMossyModifier.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        MaterialDataProvider materials = new MaterialDataProvider(output);

        boolean server = event.includeServer();
        gen.addProvider(server, new ModRecipeProvider(output));

        boolean client = event.includeClient();
        gen.addProvider(client, new ModItemModelProvider(output, existingFileHelper));
        gen.addProvider(client, new ModLanguageProvider(output, "en_us"));
    }

}
