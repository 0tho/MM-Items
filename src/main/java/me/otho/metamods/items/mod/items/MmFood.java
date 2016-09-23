package me.otho.metamods.items.mod.items;

import javax.annotation.Nullable;

import me.otho.metamods.items.MmItems;
import me.otho.metamods.items.meta.ConfigPotionEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MmFood extends ItemFood {

	  private ItemStack dropStack;
	  private EnumAction useAction = EnumAction.EAT;
	  private ConfigPotionEffect[] effectsArray;
	  private String name;

	  public MmFood(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat, String name) {
	    super(healAmount, saturationModifier, isWolfsFavoriteMeat);
	    this.name = name;
	    setUnlocalizedName(name);
	  }

	  @Override
	  public String getUnlocalizedName() {
	    return super.getUnlocalizedName();
	  }

	  @Override
	  protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
	    if (!p_77849_2_.isRemote) {
	      if (effectsArray != null) {
	        for (int i = 0; i < effectsArray.length; i++) {
	          ConfigPotionEffect effect = effectsArray[i];
	          if (p_77849_2_.rand.nextFloat() < effect.potionEffectProbability) {
	        	  Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effect.effectId));
	        	 
	        	  p_77849_3_.addPotionEffect( new PotionEffect( potion, effect.potionDuration * 20, effect.potionAmplifier));	            
	          }
	        }
	      }
	    }
	  }
	  
	  @Nullable
	  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	  {
		  super.onItemUseFinish(stack, worldIn, entityLiving);
		  return dropStack;
	  }

	  public void setFoodEffectsArray(ConfigPotionEffect[] effectsArray) {
	    this.effectsArray = effectsArray;
	  }

	  public void setUseAction(String useAction) {
	      this.useAction = EnumAction.valueOf(useAction.toUpperCase());
	  }

	  public void setDropStack(ItemStack dropStack) {
	    this.dropStack = dropStack;
	  }

	  @Override
	  public EnumAction getItemUseAction(ItemStack p_77661_1_) {
	    return useAction;
	  }

		public void registerItemModel() {
			MmItems.proxy.registerItemRenderer(this, 0, name);
		}

	}