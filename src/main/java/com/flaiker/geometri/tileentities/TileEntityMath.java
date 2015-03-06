package com.flaiker.geometri.tileentities;

import com.flaiker.geometri.helper.GeometryHelper;
import com.flaiker.geometri.models.MathModel;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flaiker on 15.10.2014.
 */
public class TileEntityMath extends TileEntity {
    public static final String NAME = "tileEntityMath";

    private List<MathModel.CoordinatePair> coordinatePairs;
    private List<TileEntityMath>           neighbors;
    private static List<MathModel.CoordinatePair> COORDS_LIST = new ArrayList<MathModel.CoordinatePair>();

    public TileEntityMath() {
        coordinatePairs = new ArrayList<MathModel.CoordinatePair>();
        neighbors = new ArrayList<TileEntityMath>();
        if (COORDS_LIST.size() == 0) {
            COORDS_LIST.add(new MathModel.CoordinatePair(0, 1));
            COORDS_LIST.add(new MathModel.CoordinatePair(0, -1));
            COORDS_LIST.add(new MathModel.CoordinatePair(1, 0));
            COORDS_LIST.add(new MathModel.CoordinatePair(-1, 0));
        }

        setInitialCoordinates();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    /**
     * Sets the coordinatePairs-list so that a cube is rendered, meaning no special alignment
     */
    public void setInitialCoordinates() {
        coordinatePairs.clear();
        int centerLeft = MathModel.PIXEL_COUNT / 2 - Math.round(MathModel.PIXEL_COUNT / 4f);
        int centerRight = MathModel.PIXEL_COUNT / 2 + Math.round(MathModel.PIXEL_COUNT / 4f);
        for (int i = centerLeft; i <= centerRight; i++) {
            for (int ii = centerLeft; ii <= centerRight; ii++) {
                coordinatePairs.add(new MathModel.CoordinatePair(i, ii));
            }
        }
    }

    /**
     * Searches for Mathblocks/entities around and fills the neighbors-list accordingly
     *
     * @param updateAlignment force to recalculate alignment
     */
    public void onNeighborChange(boolean updateAlignment) {
        neighbors.clear();

        TileEntity te;
        for (MathModel.CoordinatePair coordinatePair : COORDS_LIST) {
            te = worldObj.getTileEntity(xCoord + coordinatePair.x, yCoord, zCoord + coordinatePair.y);
            if (te instanceof TileEntityMath) {
                //System.out.println(coordinatePair.x + "|" + coordinatePair.y + ": yes");
                neighbors.add((TileEntityMath) te);
            } //else System.out.println(coordinatePair.x + "|" + coordinatePair.y + ": no");
        }

        if (updateAlignment) GeometryHelper.alignStuff(this, GeometryHelper.AlignmentMethod.CIRCLE);
    }

    public List<TileEntityMath> getNeighbors() {
        return neighbors;
    }

    public List<MathModel.CoordinatePair> getCoordinatePairs() {
        return coordinatePairs;
    }

    public void setCoordinatePairs(List<MathModel.CoordinatePair> coordinatePairs) {
        this.coordinatePairs = coordinatePairs;
    }

    public void clearCoordinates() {
        coordinatePairs.clear();
    }

    public void addCoordinate(int x, int y) {
        coordinatePairs.add(new MathModel.CoordinatePair(x, y));
    }
}
