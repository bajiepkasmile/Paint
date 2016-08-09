package com.nodomain.paint.paint;

import android.graphics.Bitmap;
import android.graphics.ColorMatrixColorFilter;

public interface ToolsParamsChangeListener {

    void onSizeChange(int size);

    void onColorChange(int color);

    void onRadiusChange(int radius);

    void onIntensityChange(int intensity);

    void onTextChange(String text);

    void onPrintChange(Bitmap print);

    void onApplyFilter(ColorMatrixColorFilter filter);

}
