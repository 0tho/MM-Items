package me.otho.metamods.items.meta;

import me.otho.metamods.core.api.IMetaTypeRegister;
import me.otho.metamods.core.meta.CreativeTabHandler;
import me.otho.metamods.items.mod.items.MmItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetaTypeItemRegister implements IMetaTypeRegister {

	class ItemReader {
		String id;
		int maxStackSize;
		String creativeTab;
	}
	
	@Override
	public void register(Object obj) {
		ItemReader data = (ItemReader) obj;
		
		MmItem newItem = new MmItem( data.id );
		newItem.setMaxStackSize(data.maxStackSize);
		newItem.setCreativeTab(CreativeTabHandler.getTab(data.creativeTab));
		
		GameRegistry.register(newItem);
		newItem.registerItemModel();
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public Class<?> getReaderClass() {
		return ItemReader.class;
	}

}
