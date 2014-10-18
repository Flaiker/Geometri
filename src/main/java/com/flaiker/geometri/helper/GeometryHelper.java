package com.flaiker.geometri.helper;

import com.flaiker.geometri.models.MathModel;
import com.flaiker.geometri.models.MathModel.CoordinatePair;
import com.flaiker.geometri.tileentities.TileEntityMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Flaiker on 16.10.2014.
 */
public class GeometryHelper {
    public static void alignAllTheThingsCircleLike(TileEntityMath tileEntityMath) {
        List<TileEntityMath> entityList = new ArrayList<TileEntityMath>();
        getChilds(tileEntityMath, entityList);

        TileEntityMath[][] coordinateArray = getCoordinateArray(entityList);

        int p = MathModel.PIXEL_COUNT;
        float centerX, centerY, radius;
        radius = coordinateArray.length / 2.0f * p - 1f;
        centerX = coordinateArray.length / 2.0f * p - 0.5f;
        centerY = coordinateArray[0].length / 2.0f * p - 0.5f;

        for (int y = 0; y < coordinateArray[0].length; y++) {
            for (int x = 0; x < coordinateArray.length; x++) {
                TileEntityMath te = coordinateArray[x][y];
                if (te != null) {
                    double distance1, distance2, distance3, distance4;
                    distance1 = getDistanceBetweenCoordinates(x * p, y * p, centerX, centerY);
                    distance2 = getDistanceBetweenCoordinates(x * p + p, y * p + p, centerX, centerY);
                    distance3 = getDistanceBetweenCoordinates(x * p + p, y * p, centerX, centerY);
                    distance4 = getDistanceBetweenCoordinates(x * p, y * p + p, centerX, centerY);

                    if (((radius > distance1 && radius < distance2) || (radius < distance1 && radius > distance2)) ||
                        ((radius > distance3 && radius < distance4) || (radius < distance3 && radius > distance4))) {
                        List<CoordinatePair> newCoordinatesList = new ArrayList<CoordinatePair>();

                        for (int px = 0; px < p; px++) {
                            for (int py = 0; py < p; py++) {
                                double dstBtwPixelAndCenter = (getDistanceBetweenCoordinates(x * p + px, y * p + py, centerX, centerY));
                                if (dstBtwPixelAndCenter >= radius && dstBtwPixelAndCenter <= radius + 1) {
                                    newCoordinatesList.add(new CoordinatePair(px, py));
                                }
                            }
                        }
                        te.setCoordinatePairs(newCoordinatesList);
                    } else te.setInitialCoordinates();
                }
            }
        }
    }

    private static TileEntityMath[][] getCoordinateArray(List<TileEntityMath> entityMathList) {
        int bottom, top, left, right;

        Collections.sort(entityMathList, new Comparator<TileEntityMath>() {
            public int compare(TileEntityMath s1, TileEntityMath s2) {
                return s1.xCoord - s2.xCoord;
            }
        });
        left = entityMathList.get(0).xCoord;
        right = entityMathList.get(entityMathList.size() - 1).xCoord;

        Collections.sort(entityMathList, new Comparator<TileEntityMath>() {
            public int compare(TileEntityMath s1, TileEntityMath s2) {
                return s1.zCoord - s2.zCoord;
            }
        });
        bottom = entityMathList.get(0).zCoord;
        top = entityMathList.get(entityMathList.size() - 1).zCoord;


        TileEntityMath[][] testArr = new TileEntityMath[right - left + 1][top - bottom + 1];
        for (TileEntityMath te : entityMathList) {
            testArr[te.xCoord - left][te.zCoord - bottom] = te;
        }

        return testArr;
    }


    private static void getChilds(TileEntityMath entityMath, List<TileEntityMath> outList) {
        if (outList.contains(entityMath)) return;

        entityMath.onNeighborChange(false);
        List<TileEntityMath> neighbors = new ArrayList<TileEntityMath>(entityMath.getNeighbors());

        outList.add(entityMath);

        if (neighbors.size() > 0) {
            for (TileEntityMath neighborEntity : neighbors) {
                getChilds(neighborEntity, outList);
            }
        }
    }

    private static double getDistanceBetweenCoordinates(float xPos, float yPos, float xPos2, float yPos2) {
        return Math.sqrt(Math.pow((xPos2 - xPos), 2) + Math.pow((yPos2 - yPos), 2));
    }
}
