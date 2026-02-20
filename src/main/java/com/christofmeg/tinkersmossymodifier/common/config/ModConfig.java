package com.christofmeg.tinkersmossymodifier.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final String CATEGORY_SETTINGS = "Tinkers Mossy Modifier Mod";

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue DOUBLE_REPAIR_RATE_IN_SUN;
    public static ForgeConfigSpec.BooleanValue DOUBLE_REPAIR_RATE_IN_RAIN;
    public static ForgeConfigSpec.ConfigValue<Integer> REPAIR_EVERY_X_GAMETICK;
    public static ForgeConfigSpec.ConfigValue<Integer> BASE_REPAIR_VALUE;
    public static ForgeConfigSpec.BooleanValue ENABLE_EXPERIENCE_DRAIN;
    public static ForgeConfigSpec.ConfigValue<Integer> BASE_EXPERIENCE_DRAIN_VALUE;

    static {
        setupFirstBlockConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void setupFirstBlockConfig() {
        COMMON_BUILDER.comment("Mod settings").push(CATEGORY_SETTINGS);

        DOUBLE_REPAIR_RATE_IN_SUN = COMMON_BUILDER.comment("Enable double repair value in sun")
                .define("DoubleRepairInSun", true);

        DOUBLE_REPAIR_RATE_IN_RAIN = COMMON_BUILDER.comment("Enable double repair value in rain")
                .define("DoubleRepairInRain", false);

        REPAIR_EVERY_X_GAMETICK = COMMON_BUILDER.comment("Repair once x game ticks has passed")
                .define("RepairEvery", 150);

        BASE_REPAIR_VALUE = COMMON_BUILDER.comment("Default value is 2, formula is (2 + mossyLevel) * (2 if sun/rain is enabled)")
                .define("BaseRepairValue", 2);

        ENABLE_EXPERIENCE_DRAIN = COMMON_BUILDER.comment("Enable draining of experience to repair, like Mending Moss")
                .define("EXPDrain", false);

        BASE_EXPERIENCE_DRAIN_VALUE = COMMON_BUILDER.comment("If EXPDrain is enabled. How much experience should be drained each repair")
                .define("BaseEXPDrainValue", 1);

        COMMON_BUILDER.pop();
    }

}
