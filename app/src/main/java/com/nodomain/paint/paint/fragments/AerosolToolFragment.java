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

public class AerosolToolFragment extends BaseToolFragment implements SeekBar.OnSeekBarChangeListener {

    ImageView ivArgbColor;
    SeekBar sbAlpha;
    SeekBar sbRed;
    SeekBar sbGreen;
    SeekBar sbBlue;

    public static AerosolToolFragment newInstance(int color, int intensity, int radius) {
        AerosolToolFragment fragment = new AerosolToolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        args.putInt(ARG_INTENSITY, intensity);
        args.putInt(ARG_RADIUS, radius);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_aerosol, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_radius:
                if (listener != null) listener.onRadiusChange(i);
                break;
            case R.id.sb_intensity:
                if (listener != null) listener.onIntensityChange(i);
                break;
            case R.id.sb_alpha:
            case R.id.sb_red:
            case R.id.sb_green:
            case R.id.sb_blue:
                int color = Color.argb(sbAlpha.getProgress(), sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress());
                ivArgbColor.setBackgroundColor(color);
                if (listener != null) listener.onColorChange(color);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void initViews(View view) {
        SeekBar sbRadius = (SeekBar) view.findViewById(R.id.sb_radius);
        SeekBar sbIntensity = (SeekBar) view.findViewById(R.id.sb_intensity);
        ivArgbColor = (ImageView) view.findViewById(R.id.iv_argb_color);
        sbAlpha = (SeekBar) view.findViewById(R.id.sb_alpha);
        sbRed = (SeekBar) view.findViewById(R.id.sb_red);
        sbGreen = (SeekBar) view.findViewById(R.id.sb_green);
        sbBlue = (SeekBar) view.findViewById(R.id.sb_blue);

        int radius = getRadius();
        int intensity = getIntensity();
        int color = getColor();
        int alpha = getAlpha(color);
        int red = getRed(color);
        int green = getGreen(color);
        int blue = getBlue(color);

        sbRadius.setProgress(radius);
        sbIntensity.setProgress(intensity);
        ivArgbColor.setBackgroundColor(color);
        sbAlpha.setProgress(alpha);
        sbRed.setProgress(red);
        sbGreen.setProgress(green);
        sbBlue.setProgress(blue);

        sbRadius.setOnSeekBarChangeListener(this);
        sbIntensity.setOnSeekBarChangeListener(this);
        sbAlpha.setOnSeekBarChangeListener(this);
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
    }

}
