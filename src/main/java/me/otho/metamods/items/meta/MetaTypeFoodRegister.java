package me.otho.metamods.items.meta;

import me.otho.metamods.core.api.IMetaTypeRegister;
import me.otho.metamods.core.jsonreader.common.ConfigItemDrop;
import me.otho.metamods.core.meta.CreativeTabHandler;
import me.otho.metamods.items.MmItems;
import me.otho.metamods.items.mod.items.MmFood;
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

		public ConfigItemDrop[] dropItems;

		public ConfigPotionEffect[] potionEffects = {};
	}

	@Override
	public void register(Object obj) {
		
		FoodReader data = (FoodReader) obj;
		
	    MmFood food = new MmFood(data.healAmount, data.saturationModifier, data.isWolfFood);

	    if (data.alwaysEdible) {
	      food.setAlwaysEdible();
	    }

	    
    	if (data.potionEffects.length > 0) {
    		food.setFoodEffectsArray(data.potionEffects);
    	}	
	    

	    if (data.dropItems != null) {	      
	      food.setDropItems(data.dropItems);
	    }
	    
	    if (data.useAction == null ) {
	    	data.useAction = "EAT";
	    }
	    food.setUseAction(data.useAction);	  
	    
	    if (data.creativeTab == null) {
	    	data.creativeTab = "customTab";
	    }
	    food.setCreativeTab(CreativeTabHandler.getTab(data.creativeTab));
	    
	    food.setUnlocalizedName(data.id);
	    MmItems.proxy.registerItemRenderer(food, 0, data.id);
	    GameRegistry.register( food, new ResourceLocation( MmItems.MOD_ID , data.id ) );
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
