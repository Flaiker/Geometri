package com.flaiker.geometri.proxy;

import com.flaiker.geometri.tileentities.TileEntityTestEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityTestEntity.class, TileEntityTestEntity.NAME);
    }
}
