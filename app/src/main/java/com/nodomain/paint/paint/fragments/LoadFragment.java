package com.nodomain.paint.paint.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.paint.R;
import com.nodomain.paint.paint.PictureStorageManager;
import com.nodomain.paint.paint.utils.StorageManager;

import java.util.List;

public class LoadFragment extends Fragment implements View.OnClickListener {


    PictureStorageManager pictureStorageManager;

    View btnLoad;
    EditText etFilename;

    public static LoadFragment newInstance() {
        return new LoadFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.load, container, false);
        pictureStorageManager = (PictureStorageManager) getActivity();
        initVIews(view);
        return view;
    }

    private void initVIews(View view) {
        TextView tvPath = (TextView) view.findViewById(R.id.tv_path);
        btnLoad = view.findViewById(R.id.btn_load);
        etFilename = (EditText) view.findViewById(R.id.et_filename);

        tvPath.setText(StorageManager.STORAGE_PATH);

        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!etFilename.getText().toString().matches("")) {
            pictureStorageManager.loadPicture(etFilename.getText().toString());
        } else {
            Toast.makeText(getContext(), "Введите имя файла", Toast.LENGTH_LONG).show();
        }
    }

}
