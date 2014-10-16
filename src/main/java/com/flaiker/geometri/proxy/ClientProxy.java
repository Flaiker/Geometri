package com.flaiker.geometri.proxy;

import com.flaiker.geometri.tileentities.TileEntityMath;
import com.flaiker.geometri.tileentities.TileEntityMathRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void registerTileEntities() {
        super.registerTileEntities();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMath.class, new TileEntityMathRenderer());
    }
}
