package com.flaiker.geometri.tileentities;

import com.flaiker.geometri.Geometri;
import com.flaiker.geometri.models.MathModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class TileEntityMathRenderer extends TileEntitySpecialRenderer {
    private MathModel model;

    public TileEntityMathRenderer() {
        this.model = new MathModel();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        TileEntityMath myte = ((TileEntityMath) te);

        model.prepareForRender(myte.getCoordinatePairs());

        TextureManager tm = Minecraft.getMinecraft().getTextureManager();
        tm.bindTexture(new ResourceLocation(Geometri.MODID, "textures/blocks/testBlock.png"));

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x, (float) y + 1, (float) z + 1);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

        model.render();

        GL11.glPopMatrix();
    }
}

