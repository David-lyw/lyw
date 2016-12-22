package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by moon1 on 2016/9/13.
 */
public class AddressBean implements Serializable {
    public String accountId;
    public String id;
    public String province;
    public String city;
    public String region;
    public String address;
    public String recipientsName;
    public String recipientsPhone;
    public int showOrder;
    public int status;//状态  -1 删除  1 默认地址 0 正常
    public int tags;
}
