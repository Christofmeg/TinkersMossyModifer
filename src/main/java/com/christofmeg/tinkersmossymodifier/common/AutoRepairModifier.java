package com.christofmeg.tinkersmossymodifier.common;

import net.minecraft.world.entity.LivingEntity;
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
            if (level.getGameTime() % 150 == 0) {
                if (!iToolStackView.isUnbreakable() && stack.isDamageableItem() && iToolStackView.getDamage() > 0) {
                    int mossyLevel = iToolStackView.getModifierLevel(ModRegistries.AUTO_REPAIR_MODIFIER.get());
                    int durabilityUsed = iToolStackView.getDamage();
                    boolean sunny = level.canSeeSky(livingEntity.blockPosition().above()) && !level.getLevelData().isRaining();
                    int durabilityRepaired = sunny ? (2 + mossyLevel) * 2 : (2 + mossyLevel);
                    if (durabilityRepaired > durabilityUsed) {
                        iToolStackView.setDamage(0);
                    } else {
                        iToolStackView.setDamage(durabilityUsed - durabilityRepaired);
                    }
                }
            }
        }
    }

}