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
    public static void alignStuff(TileEntityMath tileEntityMath, AlignmentMethod method) {
        switch (method) {
            case CIRCLE:
                alignAllTheThingsCircleLike(tileEntityMath);
                break;
            case OVAL:
                //alignAllTheThingsOvalLike(tileEntityMath);
                break;
        }
    }

    private static void alignAllTheThingsCircleLike(TileEntityMath tileEntityMath) {
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

                    /*if (((radius > distance1 && radius < distance2) || (radius < distance1 && radius > distance2)) ||
                        ((radius > distance3 && radius < distance4) || (radius < distance3 && radius > distance4))) {*/
                    List<CoordinatePair> newCoordinatesList = new ArrayList<CoordinatePair>();

                    for (int px = 0; px < p; px++) {
                        for (int py = 0; py < p; py++) {
                            double angle;
                            try{
                             angle = Math.atan((double) (y * p + py) / (double) (x * p + px)) * (180 / Math.PI);
                            } catch (Exception e) {
                                angle = 0;
                            }


                            /*double pixelRadius = (centerX * centerY) / (Math.sqrt(Math.pow(centerX, 2) * Math.pow(Math.sin(angle), 2) +
                                                                                  Math.pow(centerY, 2) * Math.pow(Math.cos(angle), 2)));*/

                            //double dstBtwPixelAndCenter = (getDistanceBetweenCoordinates(x * p + px, y * p + py, centerX, centerY));
                            //if (dstBtwPixelAndCenter >= pixelRadius - 1 && dstBtwPixelAndCenter <= pixelRadius + 1) {
                            double condition = (Math.pow((x*p+px-centerX)/centerX,2)+Math.pow((y*p+py-centerY)/centerY,2));
                            if (condition>0.8f && condition <= 1) {
                                newCoordinatesList.add(new CoordinatePair(px, py));
                            }

                            //}
                        }
                    }
                    if (newCoordinatesList.size() > 0) {
                        te.setCoordinatePairs(newCoordinatesList);
                    } else te.setInitialCoordinates();

                    //} else te.setInitialCoordinates();
                }
            }
        }
    }

    private static void alignAllTheThingsOvalLike(TileEntityMath tileEntityMath) {
        List<TileEntityMath> entityList = new ArrayList<TileEntityMath>();
        getChilds(tileEntityMath, entityList);
        clearCoordinates(entityList);

        TileEntityMath[][] coordinateArray = getCoordinateArray(entityList);

        int p = MathModel.PIXEL_COUNT;

        float angle = 0;
        float centerX = coordinateArray.length / 2.0f * p - 0.5f;
        float centerY = coordinateArray[0].length / 2.0f * p - 0.5f;
        float step = 0.5f;

        while (angle < 360) {
            float x = (float) (centerX + (centerX) * Math.cos(angle));
            float y = (float) (centerY - (centerY) * Math.sin(angle));

            TileEntityMath entity = coordinateArray[(int) (x / p)][(int) (y / p)];
            if (entity != null) {
                entity.addCoordinate(Math.round(x) - ((int) (x / p) * p), Math.round(y) - ((int) (y / p) * p));
            }
            angle += step;

            //System.out.println(";" + (Math.round(x)-((int)(x/p)* p)) + "|" + (Math.round(x)-((int)(y/p)* p)));
        }
    }

    private static void clearCoordinates(List<TileEntityMath> entityList) {
        for (TileEntityMath entity : entityList) {
            entity.clearCoordinates();
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

    public static enum AlignmentMethod {
        CIRCLE, OVAL
    }
}
