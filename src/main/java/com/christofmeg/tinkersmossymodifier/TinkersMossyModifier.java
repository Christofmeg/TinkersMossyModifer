package com.christofmeg.tinkersmossymodifier;

import com.christofmeg.tinkersmossymodifier.common.ModRegistries;
import com.christofmeg.tinkersmossymodifier.common.config.ModConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("removal")
@Mod(TinkersMossyModifier.MOD_ID)
public class TinkersMossyModifier {

    public static final String MOD_ID = "tinkersmossymodifier";
    public static final String MOD_NAME = "Tinkers Mossy Modifer";

    public static ResourceLocation rl(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public TinkersMossyModifier(FMLJavaModLoadingContext context) {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistries.init(bus);

        context.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_CONFIG);
    }

    //TODO continue texture cleanup

}
