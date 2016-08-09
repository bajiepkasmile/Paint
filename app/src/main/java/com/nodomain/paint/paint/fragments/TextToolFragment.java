package com.nodomain.paint.paint.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.nodomain.paint.R;

public class TextToolFragment extends BaseToolFragment implements SeekBar.OnSeekBarChangeListener {

    ImageView ivArgbColor;
    SeekBar sbAlpha;
    SeekBar sbRed;
    SeekBar sbGreen;
    SeekBar sbBlue;

    public static TextToolFragment newInstance(String text, int color, int size) {
        TextToolFragment fragment = new TextToolFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        args.putInt(ARG_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_text, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_alpha:
            case R.id.sb_red:
            case R.id.sb_green:
            case R.id.sb_blue:
                int color = Color.argb(sbAlpha.getProgress(), sbRed.getProgress(), sbGreen.getProgress(), sbBlue.getProgress());
                ivArgbColor.setBackgroundColor(color);
                if (listener != null) listener.onColorChange(color);
                break;
            case R.id.sb_size:
                listener.onSizeChange(i);
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
        final EditText etText = (EditText) view.findViewById(R.id.et_text);
        SeekBar sbSize = (SeekBar) view.findViewById(R.id.sb_size);
        ivArgbColor = (ImageView) view.findViewById(R.id.iv_argb_color);
        sbAlpha = (SeekBar) view.findViewById(R.id.sb_alpha);
        sbRed = (SeekBar) view.findViewById(R.id.sb_red);
        sbGreen = (SeekBar) view.findViewById(R.id.sb_green);
        sbBlue = (SeekBar) view.findViewById(R.id.sb_blue);

        String text = getText();
        int size = getSize();
        int color = getColor();
        int alpha = getAlpha(color);
        int red = getRed(color);
        int green = getGreen(color);
        int blue = getBlue(color);
        etText.setText(text);
        sbSize.setProgress(size);
        ivArgbColor.setBackgroundColor(color);
        sbAlpha.setProgress(alpha);
        sbRed.setProgress(red);
        sbGreen.setProgress(green);
        sbBlue.setProgress(blue);

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (listener != null) listener.onTextChange(etText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbSize.setOnSeekBarChangeListener(this);
        sbAlpha.setOnSeekBarChangeListener(this);
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
    }

}
