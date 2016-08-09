package com.nodomain.paint.paint.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.nodomain.paint.paint.Filters;
import com.nodomain.paint.R;

public class FilterToolFragment extends BaseToolFragment implements View.OnClickListener {

    RadioGroup rgFilter;

    public static FilterToolFragment newInstance() {
        return new FilterToolFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_filter, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (rgFilter.getCheckedRadioButtonId()) {
            case R.id.rb_gray_scale:
                listener.onApplyFilter(Filters.GRAY_SCALE_FILTER);
                break;
            case R.id.rb_sepia:
                listener.onApplyFilter(Filters.SEPIA_FILTER);
                break;
            case R.id.rb_binary:
                listener.onApplyFilter(Filters.BINARY_FILTER);
                break;
            case R.id.rb_invert:
                listener.onApplyFilter(Filters.INVERT_FILTER);
                break;
            case R.id.rb_alpha:
                listener.onApplyFilter(Filters.ALPHA_FILTER);
                break;
        }
    }

    private void initViews(View view) {
        rgFilter = (RadioGroup) view.findViewById(R.id.rg_filter);
        View btnApply = view.findViewById(R.id.btn_apply);
        btnApply.setOnClickListener(this);
    }

}
