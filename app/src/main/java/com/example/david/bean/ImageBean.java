package com.example.david.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David on 16/10/25.
 */
public class ImageBean implements Parcelable{
    public String path = "";

    public Boolean isSelect = false;
    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {

        @Override
        public ImageBean[] newArray(int size) {
            return null;
        }

        @Override
        public ImageBean createFromParcel(Parcel source) {
            String path = source.readString();
            Boolean isSelect = source.readByte()!=0;
            ImageBean result = new ImageBean(isSelect,path);
            return result;
        }
    };

    public ImageBean(Boolean isSelect, String path) {
        this.isSelect = isSelect;
        this.path = path;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeByte((byte)(isSelect ?1:0));//if isSelect == true, byte == 1
    }


    @Override
    public int describeContents() {
        return 0;
    }
}
