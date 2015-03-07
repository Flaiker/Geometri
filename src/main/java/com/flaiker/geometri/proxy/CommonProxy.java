/******************************************************************************
 * Copyright (c) 2015 Fabian Lupa                                             *
 * This software is licensed under the MIT License (MIT).                     *
 ******************************************************************************/

package com.flaiker.geometri.proxy;

import com.flaiker.geometri.tileentities.TileEntityMath;
import com.flaiker.geometri.tileentities.TileEntityTestEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityTestEntity.class, TileEntityTestEntity.NAME);
        GameRegistry.registerTileEntity(TileEntityMath.class, TileEntityMath.NAME);
    }
}
