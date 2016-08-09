package com.nodomain.paint.paint;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {

    private ToolsParamsChangeListener listener;

    private Bitmap picture;
    private Canvas pictureCanvas;

    private Bitmap changes;
    private Canvas changesCanvas;

    private PaintEngine paintEngine = new PaintEngine();
    private PaintTool paintTool = PaintTool.BRUSH;
    private float prevX;
    private float prevY;
    private float downX;
    private float downY;

    public PaintView(Context context) {
        super(context);
        init();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(picture, 0, 0, null);
        canvas.drawBitmap(changes, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            pictureCanvas.drawBitmap(changes, 0, 0, null);
            changes.eraseColor(Color.TRANSPARENT);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                switch (paintTool) {
                    case EASER:
                        paintEngine.toolEaser(changesCanvas, prevX, prevY, event.getX(), event.getY());
                        break;
                    case PEN:
                        paintEngine.toolPen(changesCanvas, prevX, prevY, event.getX(), event.getY());
                        break;
                    case BRUSH:
                        paintEngine.toolBrush(changesCanvas, prevX, prevY, event.getX(), event.getY());
                        break;
                    case AEROSOL:
                        paintEngine.toolAerosol(changesCanvas, event.getX(), event.getY());
                        break;
                    case LINE:
                        changes.eraseColor(Color.TRANSPARENT);
                        paintEngine.toolLine(changesCanvas, downX, downY, event.getX(), event.getY());
                        break;
                    case RECTANGLE:
                        changes.eraseColor(Color.TRANSPARENT);
                        paintEngine.toolRectangle(changesCanvas, downX, downY, event.getX(), event.getY());
                        break;
                    case OVAL:
                        changes.eraseColor(Color.TRANSPARENT);
                        paintEngine.toolOval(changesCanvas, downX, downY, event.getX(), event.getY());
                        break;
                    case CIRCLE:
                        changes.eraseColor(Color.TRANSPARENT);
                        float deltaX = Math.abs(event.getX()-downX);
                        float deltaY = Math.abs(event.getY()-downY);
                        paintEngine.toolCircle(
                                changesCanvas,
                                downX,
                                downY,
                                deltaX > deltaY ? deltaX : deltaY);
                        break;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                switch (paintTool) {
                    case PIPETTE:
                        Bitmap pipetteBitmap = Bitmap.createBitmap(picture);
                        new Canvas(pipetteBitmap).drawBitmap(changes, 0, 0, new Paint());
                        paintEngine.toolPipette(pipetteBitmap, (int) event.getX(), (int) event.getY());
                        if (listener != null) listener.onColorChange(paintEngine.getColor());
                        break;
                    case TEXT:
                        paintEngine.toolText(changesCanvas, event.getX(), event.getY());
                        break;
                    case PRINT:
                        paintEngine.toolPrint(changesCanvas, event.getX(), event.getY());
                        break;
                }
                downX = event.getX();
                downY = event.getY();
                break;
        }

        invalidate();

        prevX = event.getX();
        prevY = event.getY();

        return super.onTouchEvent(event);
    }

    public void applyFilter() {
        paintEngine.toolFilter(pictureCanvas, picture);
        paintEngine.toolFilter(changesCanvas, changes);
        invalidate();
    }

    public void setListener(ToolsParamsChangeListener listener) {
        this.listener = listener;
    }

    public void setPaintTool(PaintTool paintTool) {
        this.paintTool = paintTool;
    }

    public void setPicture(Bitmap picture) {
        clear();
        this.picture = picture.copy(Bitmap.Config.ARGB_8888, true);
        pictureCanvas = new Canvas(this.picture);
    }

    public Bitmap getPicture() {
        pictureCanvas.drawBitmap(changes, 0, 0, new Paint());
        return picture;
    }

    public PaintEngine getPaintEngine() {
        return paintEngine;
    }

    public void clear() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        picture = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        pictureCanvas = new Canvas(picture);
        picture.eraseColor(0xffffffff);
        changes = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        changesCanvas = new Canvas(changes);
        invalidate();
    }

    private void init() {
        clear();
    }

}
