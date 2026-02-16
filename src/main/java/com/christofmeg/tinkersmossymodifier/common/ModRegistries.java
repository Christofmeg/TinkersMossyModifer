package com.christofmeg.tinkersmossymodifier.common;

import com.christofmeg.tinkersmossymodifier.TinkersMossyModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.world.TinkerWorld;

import javax.annotation.Nonnull;

public class ModRegistries {

    protected static ModifierDeferredRegister MODIFIERS_REGISTRY = ModifierDeferredRegister.create(TinkersMossyModifier.MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersMossyModifier.MOD_ID);
    protected static SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinkersMossyModifier.MOD_ID);

    public static RegistryObject<Item> BALL_OF_MOSS = ITEMS.register("ball_of_moss", () -> new Item(new Item.Properties()));
    public static StaticModifier<AutoRepairModifier> AUTO_REPAIR_MODIFIER = MODIFIERS_REGISTRY.register("auto_repair", AutoRepairModifier::new);
    public static RegistryObject<CreativeModeTab> ITEM_TAB_GROUP = CREATIVE_TABS.register(
            TinkersMossyModifier.MOD_ID + ".items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + TinkersMossyModifier.MOD_ID + ".items"))
                    .icon(() -> BALL_OF_MOSS.get().getDefaultInstance())
                    .displayItems((itemDisplayParameters, output) -> output.accept(BALL_OF_MOSS.get()))
                    .withTabsBefore(TinkerWorld.tabWorld.getId())
                    .build()
    );

    public static void init(@Nonnull IEventBus modEventBus) {
        MODIFIERS_REGISTRY.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
    }
}
