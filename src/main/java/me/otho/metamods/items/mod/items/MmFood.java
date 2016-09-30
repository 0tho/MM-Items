package me.otho.metamods.items.mod.items;

import javax.annotation.Nullable;

import me.otho.metamods.core.jsonReader.common.ConfigItemDrop;
import me.otho.metamods.items.meta.ConfigPotionEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MmFood extends ItemFood {

	  private ConfigItemDrop[] dropItems;
	  private EnumAction useAction = EnumAction.EAT;
	  private ConfigPotionEffect[] effectsArray;

	  public MmFood(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
	    super(healAmount, saturationModifier, isWolfsFavoriteMeat);
	  }	  

	  @Override
	  protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
	    if (!world.isRemote) {
	      if (effectsArray != null) {
	        for (int i = 0; i < effectsArray.length; i++) {
	          ConfigPotionEffect effect = effectsArray[i];
	          if (world.rand.nextFloat() < effect.potionEffectProbability) {
	        	  Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(effect.effectId));
	        	 
	        	  player.addPotionEffect( new PotionEffect( potion, effect.potionDuration * 20, effect.potionAmplifier));	            
	          }
	        }
	      }
	    }
	  }
	  
	  @Nullable
	  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	  {
		  if (dropItems != null) {
			  EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			  for( ConfigItemDrop itemDrop: dropItems ) {
				  int dropAmount = itemDrop.min;
				  String[] parser = itemDrop.itemId.split(":");
			      String modId = parser[0];
			      String name = parser[1];
			      int damage = 0;
			      if (parser.length > 2) {
			        damage = Integer.parseInt(parser[2]);
			      }

			      Item item = Item.REGISTRY.getObject(new ResourceLocation(modId, name));
			      
				  for(int i = 0; i< itemDrop.max - itemDrop.min; i++) {
					  if( worldIn.rand.nextFloat() <= itemDrop.chance ) {
						  dropAmount++;
					  }
				  }
				  entityplayer.inventory.addItemStackToInventory(new ItemStack(item, dropAmount, damage));
			  }
			  
		  }
		  return super.onItemUseFinish(stack, worldIn, entityLiving);
	  }

	  public void setFoodEffectsArray(ConfigPotionEffect[] effectsArray) {
	    this.effectsArray = effectsArray;
	  }

	  public void setUseAction(String useAction) {
	      this.useAction = EnumAction.valueOf(useAction.toUpperCase());
	  }

	  public void setDropItems(ConfigItemDrop[] dropItems) {
	    this.dropItems = dropItems;
	  }

	  @Override
	  public EnumAction getItemUseAction(ItemStack p_77661_1_) {
	    return useAction;
	  }

	}