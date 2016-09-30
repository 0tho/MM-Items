package me.otho.metamods.items.meta;

import me.otho.metamods.core.api.IMetaTypeRegister;
import me.otho.metamods.core.jsonreader.common.ConfigItemDrop;
import me.otho.metamods.core.meta.CreativeTabHandler;
import me.otho.metamods.items.MmItems;
import me.otho.metamods.items.mod.items.MmAxe;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MetaTypeAxeRegister implements IMetaTypeRegister {
	
	class AxeReader {
		public String id;
		public String creativeTab;
		public int harvestLevel;
		public int durability;
		public float efficiencyOnProperMaterial;
		public float damageVsEntity;
		public int enchantability;
		
		public ConfigPotionEffect[] onHitTargetEffects;
		public ConfigPotionEffect[] onHitUserEffects;
		public ConfigPotionEffect[] wearEffects;
		public boolean breaks;
		public ConfigItemDrop[] dropsOnBreak;
	}

	@Override
	public void register(Object obj) {
		AxeReader data = (AxeReader) obj;
		
		Item.ToolMaterial material = EnumHelper.addToolMaterial(data.id, data.harvestLevel, data.durability,
		        data.efficiencyOnProperMaterial, data.damageVsEntity, data.enchantability);
		
		MmAxe axe = new MmAxe(material, data.damageVsEntity, data.efficiencyOnProperMaterial);
		axe.setBreaks(data.breaks);
		axe.setOnHitUserEffects(data.onHitUserEffects);
		axe.setOnHitTargetEffects(data.onHitTargetEffects);
		axe.setWearEffects(data.wearEffects);
		
		axe.setCreativeTab(CreativeTabHandler.getTab(data.creativeTab));
		axe.setUnlocalizedName(data.id);
	    MmItems.proxy.registerItemRenderer(axe, 0, data.id);
		GameRegistry.register(axe, new ResourceLocation( MmItems.MOD_ID , data.id ) );
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public Class<?> getReaderClass() {
		return AxeReader.class;
	}

}
