package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/9/29.
 * 反馈信息dto.
 */
public class FeedBackDto implements Serializable {
    public String appkey;//应用key
    public String udid;//设备标识
    public String uuid;//应用用户标识
    public String userId;//用户账号ID
    public String content;//反馈内容


    public FeedBackDto(String appkey, String udid, String uuid, String userId, String content) {
        this.appkey = appkey;
        this.udid = udid;
        this.uuid = uuid;
        this.userId = userId;
        this.content = content;

    }
}
