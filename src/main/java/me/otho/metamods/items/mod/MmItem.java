package me.otho.metamods.items.mod;

import me.otho.metamods.items.MmItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class MmItem extends Item {

	protected String name;
	protected Boolean glows = false;
	
	public MmItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public void registerItemModel() {
		MmItems.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public MmItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public boolean hasEffect(ItemStack p_77636_1_) {
		return glows;
	 }

	 public Boolean getGlows() {
		return glows;
	 }
	 
	 public void setGlows(Boolean glows) {
		this.glows = glows;
	 }

}