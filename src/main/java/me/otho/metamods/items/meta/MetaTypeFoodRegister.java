package me.otho.metamods.items.meta;

import me.otho.metamods.core.api.IMetaTypeRegister;
import me.otho.metamods.core.meta.CreativeTabHandler;
import me.otho.metamods.items.MmItems;
import me.otho.metamods.items.mod.items.MmFood;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetaTypeFoodRegister implements IMetaTypeRegister {
	
	class FoodReader {
		public String id;
		public int healAmount = 1;
		public float saturationModifier = 1;
		public boolean alwaysEdible = false;
		public boolean isWolfFood = false;
		public String useAction = "EAT";
		private String creativeTab = "customTab";

		public String dropItemName;

		public ConfigPotionEffect[] potionEffects = {};
		
		public FoodReader () {}
	}

	@Override
	public void register(Object obj) {
		
		FoodReader data = (FoodReader) obj;
		
	    MmFood food = new MmFood(data.healAmount, data.saturationModifier, data.isWolfFood, data.id);

	    if (data.alwaysEdible) {
	      food.setAlwaysEdible();
	    }

	    
    	if (data.potionEffects.length > 0) {
    		food.setFoodEffectsArray(data.potionEffects);
    	}	
	    

	    if (data.dropItemName != null) {
	      String[] parser = data.dropItemName.split(":");
	      String modId = parser[0];
	      String name = parser[1];
	      int damage = 0;
	      if (parser.length > 2) {
	        damage = Integer.parseInt(parser[2]);
	      }

	      Item item = Item.REGISTRY.getObject(new ResourceLocation(modId, name));
	      food.setDropStack(new ItemStack(item, 1, damage));
	    }
	    
	    if (data.useAction == null ) {
	    	data.useAction = "EAT";
	    }
	    food.setUseAction(data.useAction);	  
	    
	    if (data.creativeTab == null) {
	    	data.creativeTab = "customTab";
	    }
	    food.setCreativeTab(CreativeTabHandler.getTab(data.creativeTab));
	    
	    GameRegistry.register( food, new ResourceLocation( MmItems.MOD_ID , data.id ) );
	    
	    food.registerItemModel();
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public Class<?> getReaderClass() {
		return FoodReader.class;
	}

}
