package me.otho.metamods.items.meta;

import com.google.gson.JsonObject;

import me.otho.metamods.core.api.IMetaTypeRegister;
import me.otho.metamods.core.meta.CreativeTabHandler;
import me.otho.metamods.items.mod.items.MmItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetaTypePickaxeRegister implements IMetaTypeRegister {

	@Override
	public void register(JsonObject obj) {
		String id = obj.get("id").getAsString();
		int maxStackSize = obj.has("maxStackSize") ? obj.get("maxStackSize").getAsInt() : 64;
		String creativeTab = obj.has("creativeTab") ? obj.get("creativeTab").getAsString() : "misc";
		
		MmItem newItem = new MmItem( id );
		newItem.setMaxStackSize(maxStackSize);
		newItem.setCreativeTab(CreativeTabHandler.getTab(creativeTab));
		
		GameRegistry.register(newItem);
		newItem.registerItemModel();
	}

	@Override
	public int getPriority() {
		return 0;
	}

}