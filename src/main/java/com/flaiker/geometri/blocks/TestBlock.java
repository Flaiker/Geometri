package com.flaiker.geometri.blocks;

import com.flaiker.geometri.Geometri;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class TestBlock extends Block {
    public static final String NAME = "testBlock";

    public TestBlock() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockName(Geometri.MODID + "_" + NAME);
        GameRegistry.registerBlock(this, NAME);
    }
}
