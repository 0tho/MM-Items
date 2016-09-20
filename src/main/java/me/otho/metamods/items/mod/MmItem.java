package me.otho.metamods.items.mod;

import me.otho.metamods.items.MmItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class MmItem extends Item {

	protected String name;

	public MmItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	public void registerItemModel() {
		MmItems.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public MmItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}