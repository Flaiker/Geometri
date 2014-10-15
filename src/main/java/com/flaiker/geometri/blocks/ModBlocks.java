package com.flaiker.geometri.blocks;

import net.minecraft.block.Block;

/**
 * Created by Flaiker on 15.10.2014.
 */
public final class ModBlocks {
    public static Block testBlock;

    public static void init() {
        testBlock = new TestBlock();
    }
}