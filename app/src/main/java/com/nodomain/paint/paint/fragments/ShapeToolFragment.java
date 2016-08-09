package com.nodomain.paint.paint.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nodomain.paint.R;

public class ShapeToolFragment extends BaseToolFragment implements SeekBar.OnSeekBarChangeListener {

    ImageView ivArgbColor;
    SeekBar sbAlpha;
    SeekBar sbRed;
    SeekBar sbGreen;
    SeekBar sbBlue;

    public static ShapeToolFragment newInstance(int color) {
        ShapeToolFragment fragment = new ShapeToolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_shape, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int color = Color.argb(sbAlpha.getProgress(), sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress());
        ivArgbColor.setBackgroundColor(color);
        if (listener != null) listener.onColorChange(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void initViews(View view) {
        ivArgbColor = (ImageView) view.findViewById(R.id.iv_argb_color);
        sbAlpha = (SeekBar) view.findViewById(R.id.sb_alpha);
        sbRed = (SeekBar) view.findViewById(R.id.sb_red);
        sbGreen = (SeekBar) view.findViewById(R.id.sb_green);
        sbBlue = (SeekBar) view.findViewById(R.id.sb_blue);

        int color = getColor();
        int alpha = getAlpha(color);
        int red = getRed(color);
        int green = getGreen(color);
        int blue = getBlue(color);

        ivArgbColor.setBackgroundColor(color);
        sbAlpha.setProgress(alpha);
        sbRed.setProgress(red);
        sbGreen.setProgress(green);
        sbBlue.setProgress(blue);

        sbAlpha.setOnSeekBarChangeListener(this);
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
    }

}