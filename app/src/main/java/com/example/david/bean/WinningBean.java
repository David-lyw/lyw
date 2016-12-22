package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by moon1 on 2016/9/29.
 */

public class WinningBean implements Serializable {

    /**
     * id: 3,
     * activityId: 3,
     * activityName: "新感绝（镇喘散）",
     * activityIcon: "http://p0.qhimg.com/t0126a97ec27f8f4846.jpg",
     * udid: "",
     * accountName: "asd",
     * personNo: "1-927377",
     * status: 1,
     * activityStatus: 3,
     * activityNo: "20160926001",
     * personCount: 1,
     * createTime: "2016-09-28 11:48:30",
     * endTime: "2016-09-26 15:36:01",
     * winning: 1,
     * isGet: 0,
     * isReveal: 0
     */
    public String id;
    public String activityId;    //活动id
    public String activityName;    //活动名称
    public String activityIcon;    //活动icon
    public String udid;
    public int status;//1 待揭晓  2 待领取  3待晒单  4未中奖  5 已中奖  6 待收货 7 已揭晓
    public int activityStatus;
    public String personNo;    //参与者编号
    public String accountName;    //参与者用户名
    public String activityNo;    //活动期号
    public String personCount;    //参与人次
    public String portrait;    //用户头像
    public int winning;    //中奖	（0：未中奖；1：中奖）
    public String createTime;//创建时间
    public int isGet;    //是否领取   1 已领取   0未领取
    public String endTime;    //揭晓时间
    public int isReveal;    //是否晒单   1 已晒单   0未晒单
    public String networkJustice;
    public String getTime;//领奖时间
    public String revealTime;//晒单时间

    public String getCurrentStatus() {
        if ("1".equals(status)) {
            return "待揭晓";
        } else if ("2".equals(status)) {
            return "待领取";
        } else if ("3".equals(status)) {
            return "待晒单";
        } else if ("4".equals(status)) {
            return "未中奖";
        } else if ("5".equals(status)) {
            return "中奖";
        }
        return "";
    }
}
