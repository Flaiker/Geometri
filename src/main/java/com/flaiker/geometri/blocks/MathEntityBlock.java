package com.flaiker.geometri.blocks;

import com.flaiker.geometri.Geometri;
import com.flaiker.geometri.tileentities.TileEntityMath;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class MathEntityBlock extends BlockContainer {
    public static final String NAME = "mathBlock";

    public MathEntityBlock() {
        super(Material.iron);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setBlockName(Geometri.MODID + "_" + NAME);
        //setBlockTextureName(Geometri.MODID + ":testBlock");
        GameRegistry.registerBlock(this, NAME);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMath();
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
        TileEntity te = world.getTileEntity(tileX, tileY, tileZ);
        if (te instanceof TileEntityMath) {
            ((TileEntityMath) world.getTileEntity(x, y, z)).onNeighborChange();
        }
    }
}

