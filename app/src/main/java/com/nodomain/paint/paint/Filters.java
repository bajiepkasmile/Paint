package com.nodomain.paint.paint;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public class Filters {

    public static final ColorMatrixColorFilter GRAY_SCALE_FILTER;
    public static final ColorMatrixColorFilter SEPIA_FILTER;
    public static final ColorMatrixColorFilter BINARY_FILTER;
    public static final ColorMatrixColorFilter INVERT_FILTER;
    public static final ColorMatrixColorFilter ALPHA_FILTER;

    static {
        ColorMatrix grayScaleColorMatrix = new ColorMatrix();
        grayScaleColorMatrix.setSaturation(0);
        GRAY_SCALE_FILTER = new ColorMatrixColorFilter(grayScaleColorMatrix);

        ColorMatrix sepiaColorMatrix = new ColorMatrix();
        sepiaColorMatrix.setSaturation(0);
        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1, 1, 0.8f, 1);
        sepiaColorMatrix.postConcat(colorScale);
        SEPIA_FILTER = new ColorMatrixColorFilter(sepiaColorMatrix);

        ColorMatrix binaryColorMatrix = new ColorMatrix();
        binaryColorMatrix.setSaturation(0);
        float m = 255f;
        float t = -255*128f;
        ColorMatrix threshold = new ColorMatrix(new float[] {
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        });
        binaryColorMatrix.postConcat(threshold);
        BINARY_FILTER = new ColorMatrixColorFilter(binaryColorMatrix);

        ColorMatrix invertColorMatrix = new ColorMatrix();
        invertColorMatrix.postConcat(new ColorMatrix(new float[] {
                -1,  0,  0,  0, 255,
                0, -1,  0,  0, 255,
                0,  0, -1,  0, 255,
                0,  0,  0,  1,   0
        }));
        INVERT_FILTER = new ColorMatrixColorFilter(invertColorMatrix);

        ColorMatrix alphaBlueColorMatrix = new ColorMatrix();
        alphaBlueColorMatrix.postConcat(new ColorMatrix(new float[] {
                0,    0,    0, 0,   0,
                0.3f,    0,    0, 0,  50,
                0,    0,    0, 0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        }));
        ALPHA_FILTER = new ColorMatrixColorFilter(alphaBlueColorMatrix);
    }

}
