package com.nodomain.paint.paint.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.nodomain.paint.R;

public class EaserToolFragment extends BaseToolFragment implements SeekBar.OnSeekBarChangeListener {

    public static EaserToolFragment newInstance(int size) {
        EaserToolFragment fragment = new EaserToolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_easer, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (listener != null) listener.onSizeChange(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void initViews(View view) {
        SeekBar sbSize = (SeekBar) view.findViewById(R.id.sb_size);
        int size = getSize();
        sbSize.setProgress(size);
        sbSize.setOnSeekBarChangeListener(this);
    }

}
