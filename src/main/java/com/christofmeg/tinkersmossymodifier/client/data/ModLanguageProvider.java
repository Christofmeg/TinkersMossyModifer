package com.christofmeg.tinkersmossymodifier.client.data;

import com.christofmeg.tinkersmossymodifier.common.ModRegistries;
import com.christofmeg.tinkersmossymodifier.TinkersMossyModifier;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, TinkersMossyModifier.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("modifier.tinkersmossymodifier.auto_repair", "Auto Repair");
        add("modifier.tinkersmossymodifier.auto_repair.flavor", "Slowly repairs itself");

        ModRegistries.ITEMS.getEntries().stream().map(RegistryObject::get)
            .forEach(item -> addItem(() -> item, StringUtils.capitaliseAllWords(item.getDescriptionId()
                    .replace("item." + TinkersMossyModifier.MOD_ID + ".", "")
                    .replace("block." + TinkersMossyModifier.MOD_ID + ".", "")
                    .replace("_", " ")
            )));
    }

}