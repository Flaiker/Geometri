package com.flaiker.geometri.tileentities;

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
        System.out.println("New instance");
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

    private void setInitialCoordinates() {
        coordinatePairs.clear();
        for (int i = -8; i <= 7; i++) {
            coordinatePairs.add(new MathModel.CoordinatePair(i, 0));
        }
    }

    public void onNeighborChange() {
        neighbors.clear();

        System.out.println("I'm at " + xCoord + "|" + zCoord);
        TileEntity te;

        for (MathModel.CoordinatePair coordinatePair : COORDS_LIST) {
            te = worldObj.getTileEntity(xCoord + coordinatePair.x, yCoord, zCoord + coordinatePair.y);
            if (te instanceof TileEntityMath) {
                System.out.println(coordinatePair.x + "|" + coordinatePair.y + ": yes");
                neighbors.add((TileEntityMath) te);
            } else System.out.println(coordinatePair.x + "|" + coordinatePair.y + ": no");
        }
    }

    public List<MathModel.CoordinatePair> getCoordinatePairs() {
        return coordinatePairs;
    }
}
