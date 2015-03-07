/******************************************************************************
 * Copyright (c) 2015 Fabian Lupa                                             *
 * This software is licensed under the MIT License (MIT).                     *
 ******************************************************************************/

package com.flaiker.geometri.blocks;

import com.flaiker.geometri.Geometri;
import com.flaiker.geometri.tileentities.TileEntityTestEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class TestEntityBlock extends Block implements ITileEntityProvider{
    public static final String NAME = "testEntityBlock";

    public TestEntityBlock() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(Geometri.MODID + "_" + NAME);
        GameRegistry.registerBlock(this, NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityTestEntity();
    }


    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        /*this.setBlockBounds(0.33F, 0.2F, 0.33F, 0.67F, 0.53F, 0.67F);
        float minx = (float)this.minX;
        float maxx = (float)this.maxX;
        float miny = (float)this.minY;
        float maxy = (float)this.maxY;
        float minz = (float)this.minZ;
        float maxz = (float)this.maxZ;

        if (par1IBlockAccess.getBlock(x-1, y, z) == this)
            minx = 0;

        if (par1IBlockAccess.getBlock(x+1, y, z) == this)
            maxx = 1;

        if (par1IBlockAccess.getBlock(x, y-1, z) == this)
            miny = 0;


        if (par1IBlockAccess.getBlock(x, y+1, z) == this)
            maxy = 1;


        if (par1IBlockAccess.getBlock(x, y, z-1) == this)
            minz = 0;

        if (par1IBlockAccess.getBlock(x, y, z+1) == this)
            maxz = 1;


        this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);*/
    }
}
