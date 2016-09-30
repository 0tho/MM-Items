package me.otho.metamods.items.mod.items;
	
import java.util.Iterator;

import me.otho.metamods.items.meta.ConfigPotionEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MmAxe extends ItemAxe {
	
	private boolean breaks = true;
	private ConfigPotionEffect[] onHitTargetEffects;
	private ConfigPotionEffect[] onHitUserEffects;
	private ConfigPotionEffect[] wearEffects; 

	public MmAxe(Item.ToolMaterial material, float damage, float speed) {
        super(material, damage, speed);
    }

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		if (!breaks && this.getMaxDamage() - this.getDamage(stack) <= 0 ) {
			return 0;
		}
		return super.getStrVsBlock(stack, state);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (onHitTargetEffects != null) {
			for (ConfigPotionEffect effect : onHitTargetEffects) {
				World world = attacker.getEntityWorld();
				if (world.rand.nextFloat() < effect.potionEffectProbability) {
					Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effect.effectId));

					target.addPotionEffect(
							new PotionEffect(potion, effect.potionDuration * 20, effect.potionAmplifier));
				}
			}
		}
		
		if (onHitUserEffects != null) {
			for (ConfigPotionEffect effect : onHitUserEffects) {
				World world = attacker.getEntityWorld();
				if (world.rand.nextFloat() < effect.potionEffectProbability) {
					Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effect.effectId));

					attacker.addPotionEffect(
							new PotionEffect(potion, effect.potionDuration * 20, effect.potionAmplifier));
				}
			}
		}
		
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!world.isRemote)
        {
        	if( isSelected && wearEffects != null) {
        		for (ConfigPotionEffect effect : wearEffects) {
    				EntityLivingBase living = (EntityLivingBase) entityIn;
    				if (world.rand.nextFloat() < effect.potionEffectProbability) {
    					Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effect.effectId));

    					living.addPotionEffect(
    							new PotionEffect(potion, effect.potionDuration * 20, effect.potionAmplifier));
    				}
    			}
        	}
        }
    }
	
	public void setBreaks(boolean breaks) {
		this.breaks = breaks;
	}

	public void setOnHitTargetEffects(ConfigPotionEffect[] onHitTargetEffects) {
		this.onHitTargetEffects = onHitTargetEffects;
	}

	public void setOnHitUserEffects(ConfigPotionEffect[] onHitUserEffects) {
		this.onHitUserEffects = onHitUserEffects;
	}

	public void setWearEffects(ConfigPotionEffect[] wearEffects) {
		this.wearEffects = wearEffects;
	}
}
