package com.flaiker.geometri;

import com.flaiker.geometri.blocks.ModBlocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Flaiker on 15.10.2014.
 */
@Mod(modid = Geometri.MODID, name = Geometri.MODNAME, version = Geometri.VERSION)
public class Geometri {
    public static final String MODID   = "geometri";
    public static final String MODNAME = "Geometri";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
