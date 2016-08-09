package com.nodomain.paint.paint.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.nodomain.paint.paint.ToolsParamsChangeListener;

public class BaseToolFragment extends Fragment {

    public static final String ARG_COLOR = "color";
    public static final String ARG_SIZE = "size";
    public static final String ARG_INTENSITY = "intensity";
    public static final String ARG_RADIUS = "radius";
    public static final String ARG_TEXT = "text";

    ToolsParamsChangeListener listener;

    public void setListener(ToolsParamsChangeListener listener) {
        this.listener = listener;
    }

    protected int getIntensity() {
        if (getArguments() != null) {
            return getArguments().getInt(ARG_INTENSITY);
        } else {
            return 0;
        }
    }

    protected int getRadius() {
        if (getArguments() != null) {
            return getArguments().getInt(ARG_RADIUS);
        } else {
            return 0;
        }
    }

    protected int getSize() {
        if (getArguments() != null) {
            return getArguments().getInt(ARG_SIZE);
        } else {
            return 0;
        }
    }

    protected String getText() {
        if (getArguments() != null) {
            return getArguments().getString(ARG_TEXT);
        } else {
            return "";
        }
    }

    protected int getColor() {
        if (getArguments() != null) {
            return getArguments().getInt(ARG_COLOR);
        } else {
            return 0;
        }
    }

    protected int getAlpha(int color) {
        //return (color >> 24);
        return Color.alpha(color);
    }

    protected int getRed(int color) {
        //return (color & 0x00ff0000) >> 16;
        return Color.red(color);
    }

    protected int getGreen(int color) {
        //return (color & 0x0000ff00) >> 8;
        return Color.green(color);
    }

    protected int getBlue(int color) {
        //return (color & 0x000000ff);
        return Color.blue(color);
    }

}
