package com.example.david.bean;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by David on 16/10/28.
 */
public class ImageItem implements Serializable {

    //    public String thumbnailPath;
    public String imagePath;
    public int tag = 0;
    public Uri uri;
    public String imageId;
}
