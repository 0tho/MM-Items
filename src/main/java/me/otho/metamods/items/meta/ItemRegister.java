package me.otho.metamods.items.meta;

import com.google.gson.JsonObject;

import me.otho.metamods.core.registry.IRegister;
import me.otho.metamods.items.mod.MmItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegister implements IRegister {

	@Override
	public void register(JsonObject obj) {
		System.out.println( "Registering: " + obj.get("id").toString());
		MmItem newItem = new MmItem( obj.get("id").toString() );
		
		GameRegistry.register(newItem);
		newItem.registerItemModel();
	}

}
