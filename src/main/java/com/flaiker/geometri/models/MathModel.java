package com.flaiker.geometri.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class MathModel extends ModelBase {
    public static final int PIXEL_COUNT = 32;
    private ModelRenderer shape1;
    private static Map<Integer, ModelRenderer> recycledCoordinates = new HashMap<Integer, ModelRenderer>();


    public MathModel() {
    }


    public void render() {
        shape1.render(1f / PIXEL_COUNT);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void prepareForRender(List<CoordinatePair> coordinatePairs) {
        shape1 = null;
        //System.gc();

        ModelRenderer recycledShape = recycledCoordinates.get(coordinatePairs.hashCode());

        if (recycledShape != null) {
            shape1 = recycledShape;
        } else {
            shape1 = new ModelRenderer(this, 0, 0);
            for (CoordinatePair pair : coordinatePairs) {
                shape1.addBox(pair.x, 0F, pair.y, 1, PIXEL_COUNT / 2, 1);
            }
            recycledCoordinates.put(coordinatePairs.hashCode(), shape1);
        }
    }

    public static class CoordinatePair {
        public final int x;
        public final int y;

        public CoordinatePair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
