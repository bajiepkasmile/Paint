package com.nodomain.paint.paint.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageManager {

    public static final String STORAGE_PATH = Environment.getExternalStorageDirectory().toString() + "/Pictures/Paint/";

    private static StorageManager storageManager;

    public static StorageManager getInstance() {
        if (storageManager == null) {
            storageManager = new StorageManager();
        }
        return storageManager;
    }

    public void savePicture(Bitmap picture, String pictureName) {
        FileOutputStream out = null;
        try {
            new File(STORAGE_PATH).mkdirs();
            out = new FileOutputStream(STORAGE_PATH + pictureName + ".jpg");
            picture.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap loadPicture(String pictureName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(STORAGE_PATH + "/" + pictureName + ".jpg", options);
    }

}
