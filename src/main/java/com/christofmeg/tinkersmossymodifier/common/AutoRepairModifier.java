package com.christofmeg.tinkersmossymodifier.common;

import com.christofmeg.tinkersmossymodifier.common.config.ModConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class AutoRepairModifier extends Modifier implements InventoryTickModifierHook {

    public AutoRepairModifier() {}

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(@NotNull IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, @NotNull Level level,
                                @NotNull LivingEntity livingEntity, int itemSlot, boolean isSelected, boolean isCorrectSlot, @NotNull ItemStack stack) {
        if (!level.isClientSide()) {
            if (level.getGameTime() % ModConfig.REPAIR_EVERY_X_GAMETICK.get() == 0) {
                if (!iToolStackView.isUnbreakable() && stack.isDamageableItem() && iToolStackView.getDamage() > 0 && !iToolStackView.isBroken()) {
                    if (ModConfig.ENABLE_EXPERIENCE_DRAIN.get()) {
                        if (livingEntity instanceof Player player && player.getExperienceReward() > 0) {
                            repair(iToolStackView, level, player);
                            player.giveExperiencePoints(-ModConfig.BASE_EXPERIENCE_DRAIN_VALUE.get());
                        }
                    } else {
                        repair(iToolStackView, level, livingEntity);
                    }
                }
            }
        }
    }

    private void repair(IToolStackView iToolStackView, Level level, LivingEntity livingEntity) {
        int mossyLevel = iToolStackView.getModifierLevel(ModRegistries.AUTO_REPAIR_MODIFIER.get());
        int durabilityUsed = iToolStackView.getDamage();
        int durabilityRepaired = ModConfig.BASE_REPAIR_VALUE.get() + mossyLevel;
        boolean canSeeSky = level.canSeeSky(livingEntity.blockPosition().above());
        if (ModConfig.DOUBLE_REPAIR_RATE_IN_SUN.get()) {
            boolean sunny = canSeeSky && level.isDay() && !level.isRaining() && !level.isThundering();
            if (sunny) durabilityRepaired = (ModConfig.BASE_REPAIR_VALUE.get() + mossyLevel) * 2;
        }
        if (ModConfig.DOUBLE_REPAIR_RATE_IN_RAIN.get()) {
            boolean raining = canSeeSky && level.getLevelData().isRaining();
            if (raining) durabilityRepaired = (ModConfig.BASE_REPAIR_VALUE.get() + mossyLevel) * 2;
        }
        if (durabilityRepaired > durabilityUsed) {
            iToolStackView.setDamage(0);
        } else {
            iToolStackView.setDamage(durabilityUsed - durabilityRepaired);
        }
    }

}