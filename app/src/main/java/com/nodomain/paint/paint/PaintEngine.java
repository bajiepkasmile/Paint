package com.nodomain.paint.paint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public class PaintEngine {

    private Paint paint = new Paint();
    private Paint paintEaser = new Paint();
    private Paint paintPen = new Paint();
    private Paint paintFilter = new Paint();

    private int color = 0xff000000;
    private int size = 10;
    private int radius = 10;
    private int intensity = 10;
    private String text = "";
    private ColorMatrixColorFilter filter = Filters.GRAY_SCALE_FILTER;
    private Bitmap print = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);


    public PaintEngine() {
        paint.setColor(color);
        paint.setStrokeWidth(size);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);

        paintEaser.setColor(0xFFFFFFFF);
        paintEaser.setStrokeCap(Paint.Cap.ROUND);
        paintEaser.setStrokeWidth(size);

        paintPen.setStrokeWidth(1);

        paintFilter.setColorFilter(filter);
    }


    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        paintPen.setColor(color);
    }

    public void setSize(int size) {
        this.size = size;
        paint.setStrokeWidth(size);
        paintEaser.setStrokeWidth(size);
        paint.setTextSize(size);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFilter(ColorMatrixColorFilter filter) {
        this.filter = filter;
        paintFilter.setColorFilter(filter);
    }

    public void setPrint(Bitmap print) {
        this.print = print;
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getRadius() {
        return radius;
    }

    public int getIntensity() {
        return  intensity;
    }

    public String getText() {
        return text;
    }

    public ColorMatrixColorFilter getFilter() {
        return filter;
    }

    public Bitmap getPrint() {
        return print;
    }


    public void toolEaser(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawLine(x1, y1, x2, y2, paintEaser);
    }

    public void toolPipette(Bitmap bitmap, int x, int y) {
        color = bitmap.getPixel(x, y);
    }

    public void toolPen(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawLine(x1, y1, x2, y2, paintPen);
    }

    public void toolBrush(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    public void toolAerosol(Canvas canvas, float x, float y) {
        int pointCount = intensity*radius/10;
        for (int i = 0; i < pointCount; i++) {
            double t = 2*Math.PI*Math.random();
            double u = Math.random()+Math.random();
            double r = u>1 ? 2-u : u;
            float pointX = (float) (r*Math.cos(t))*radius*2 + x;
            float pointY = (float) (r*Math.sin(t))*radius*2 + y;
            canvas.drawPoint(pointX, pointY, paintPen);
        }
    }

    public void toolText(Canvas canvas, float x, float y) {
        canvas.drawText(text, x, y, paint);
    }

    public void toolLine(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    public void toolRectangle(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    public void toolOval(Canvas canvas, float x1, float y1, float x2, float y2) {
        canvas.drawOval(new RectF(x1, y1, x2, y2), paint);
    }

    public void toolCircle(Canvas canvas, float centerX, float centerY, float radius) {
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    public void toolPrint(Canvas canvas, float x, float y) {
        int width = print.getWidth();
        int height = print.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(size/10 + 1, size/10 + 1);
        for (int i = 0; i < print.getWidth(); i++)
            for (int j = 0; j < print.getHeight(); j++) {
                if (print.getPixel(i, j) != 0) {
                    print.setPixel(i, j, color);
                }
            }
        Bitmap resizedPrint = Bitmap.createBitmap(print, 0, 0, width, height, matrix, false);
        canvas.drawBitmap(resizedPrint, x - resizedPrint.getWidth()/2, y - resizedPrint.getHeight()/2, paint);
    }

    public void toolFilter(Canvas canvas, Bitmap bitmap) {
        paintFilter.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paintFilter);
    }

}
