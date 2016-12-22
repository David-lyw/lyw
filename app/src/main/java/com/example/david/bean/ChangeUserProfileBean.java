package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by moon1 on 2016/9/27.
 */

public class ChangeUserProfileBean implements Serializable {
    public String userId;
    public String modifyName;
    public String modifyValue;

    public ChangeUserProfileBean(String userId, String modifyName, String modifyValue) {
        this.userId = userId;
        this.modifyName = modifyName;
        this.modifyValue = modifyValue;
    }

    public ChangeUserProfileBean() {
    }
}
