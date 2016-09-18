package me.otho.metamods.items;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import me.otho.metamods.core.registry.RegisterHandler;

@Mod(modid = MmItems.MOD_ID, version = MmItems.VERSION)
public class MmItems
{
    public static final String MOD_ID = "mm-items";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        // some example code
        RegisterHandler.addRegisterType(MOD_ID + "." + "item", new ItemRegister() );
    }
}
