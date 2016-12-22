package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/10/11.
 */
public class RecordDto implements Serializable {
    public int id;
    public int activityId;//商品id
    public String accountName;//用户昵称
    public String personNo;//活动名称
    public int status;//状态（1：有效；0：无效）
    public int activityStatus;//活动状态（0：活动未开始；1：活动进行中；2：即将揭晓；3：活动结束）
    public String activityNo;//活动期号
    public int personCount;//参与人次
    public String createTime;//参与时间
    public String endTime;//开奖时间
    public int winning;//中奖（0：未中奖；1中奖）
    public String portrait;//头像
    public int isGet;//
    public int isReveal;//
}
