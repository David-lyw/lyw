package com.example.david.bean.user;

/**
 * Created by json on 2016/09/28.
 * 用户资料
 * 31参数
 */

public class UserBean {
    /**
     * "id": 203,
     * "appkey": "",
     * "uuid": "",
     * "udid": "",
     * "nickName": "M843790",
     * "status": 1,
     * "sex": 0,
     * "birthday": "",
     * "mobile": "18800197547",
     * "email": "",
     * "score": 0,
     * "wallet": 0,
     * "portrait": "",
     * "background": "",
     * "accountName": null,
     * "type": 0,
     * "attentionCount": 0,
     * "collectCount": 0,
     * "autograph": null,
     * "osName": "",
     * "openId": null,
     * "unionId": null,
     * "vip": 0,
     * <p>
     * "qqOpenid": null,
     * "realName": null,
     * "job": null,
     * "income": null,
     * "arriage": null,
     * "childbirth": null,
     * "vehicle": null,
     * "house": null
     */

    public int id;
    public String appkey;
    public String uuid;
    public String udid;
    public String nickName;
    public int status;
    public int sex;//1 男 2:女 0:保密
    public String birthday;
    public String mobile;
    public String email;
    public int score;
    public double wallet;
    public String portrait;//头像
    public String background;
    public int type;
    public int attentionCount;
    public int collectCount;
    public String autograph;
    public String osName;
    public String openId;
    public String unionId;
    public int vip;
    public String qqOpenid;
    public String realName;
    public String job;
    public String income;
    public int arriage;
    public int childbirth;
    public int vehicle;
    public int house;
    public String honour;//用户等级
}
