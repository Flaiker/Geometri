package com.flaiker.geometri.blocks;

import com.flaiker.geometri.Geometri;
import com.flaiker.geometri.tileentities.TileEntityTestEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
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
}
