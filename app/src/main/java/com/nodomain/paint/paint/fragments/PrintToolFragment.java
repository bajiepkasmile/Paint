package com.nodomain.paint.paint.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nodomain.paint.R;

public class PrintToolFragment extends BaseToolFragment
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView ivArgbColor;
    private SeekBar sbAlpha;
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;

    public static PrintToolFragment newInstance(int color, int size) {
        PrintToolFragment fragment = new PrintToolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        args.putInt(ARG_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_print, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        ImageView imageView = (ImageView) view;
        //VectorDrawable vectorDrawable = (VectorDrawable) imageView.getDrawable();

        Drawable drawable = imageView.getDrawable();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        if (listener != null) listener.onPrintChange(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_size:
                if (listener != null) listener.onSizeChange(i);
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
        ivArgbColor = (ImageView) view.findViewById(R.id.iv_argb_color);
        SeekBar sbSize = (SeekBar) view.findViewById(R.id.sb_size);
        sbAlpha = (SeekBar) view.findViewById(R.id.sb_alpha);
        sbRed = (SeekBar) view.findViewById(R.id.sb_red);
        sbGreen = (SeekBar) view.findViewById(R.id.sb_green);
        sbBlue = (SeekBar) view.findViewById(R.id.sb_blue);

        int size = getSize();
        int color = getColor();
        int alpha = getAlpha(color);
        int red = getRed(color);
        int green = getGreen(color);
        int blue = getBlue(color);

        sbSize.setProgress(size);
        ivArgbColor.setBackgroundColor(color);
        sbAlpha.setProgress(alpha);
        sbRed.setProgress(red);
        sbGreen.setProgress(green);
        sbBlue.setProgress(blue);

        sbSize.setOnSeekBarChangeListener(this);
        sbAlpha.setOnSeekBarChangeListener(this);
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
        view.findViewById(R.id.iv_brush).setOnClickListener(this);
        view.findViewById(R.id.iv_fingerprint).setOnClickListener(this);
        view.findViewById(R.id.iv_forbidden).setOnClickListener(this);
        view.findViewById(R.id.iv_globe).setOnClickListener(this);
        view.findViewById(R.id.iv_hand).setOnClickListener(this);
        view.findViewById(R.id.iv_happy).setOnClickListener(this);
        view.findViewById(R.id.iv_sad).setOnClickListener(this);
        view.findViewById(R.id.iv_key).setOnClickListener(this);
        view.findViewById(R.id.iv_man).setOnClickListener(this);
        view.findViewById(R.id.iv_mosaic).setOnClickListener(this);
    }

}
