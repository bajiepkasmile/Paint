package com.nodomain.paint.paint;

public interface PictureStorageManager {

    void saveCurrentPicture(String pictureName);

    void loadPicture(String pictureName);

}
