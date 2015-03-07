/******************************************************************************
 * Copyright (c) 2015 Fabian Lupa                                             *
 * This software is licensed under the MIT License (MIT).                     *
 ******************************************************************************/

package com.flaiker.geometri;

import com.flaiker.geometri.blocks.ModBlocks;
import com.flaiker.geometri.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Flaiker on 15.10.2014.
 */
@Mod(modid = Geometri.MODID, name = Geometri.MODNAME, version = Geometri.VERSION)
public class Geometri {
    public static final String MODID              = "geometri";
    public static final String MODNAME            = "Geometri";
    public static final String VERSION            = "1.0";
    public static final String CLIENT_PROXY_CLASS = "com.flaiker.geometri.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.flaiker.geometri.proxy.CommonProxy";

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerTileEntities();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
