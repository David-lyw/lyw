package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by moon1 on 2016/9/20.
 */
public class UserTagBean implements Serializable {
    public String mUserTag;
    public String mUserValue;

    public UserTagBean(String mUserTag, String mUserValue) {
        this.mUserTag = mUserTag;
        this.mUserValue = mUserValue;
    }
}
