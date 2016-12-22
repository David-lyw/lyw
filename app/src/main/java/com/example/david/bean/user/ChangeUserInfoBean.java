package com.example.david.bean.user;

import java.io.Serializable;

/**
 * Created by David on 16/10/12.
 */
public class ChangeUserInfoBean implements Serializable {
    public String userId;
    public String modifyName;
    public String modifyValue;

    public ChangeUserInfoBean(String str1, String str2, String str3) {
        this.userId = str1;
        this.modifyName = str2;
        this.modifyValue = str3;
    }
}
