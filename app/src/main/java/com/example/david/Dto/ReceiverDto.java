package com.example.david.Dto;

/**
 * Created by David on 16/10/20.
 * 我的账户－领取记录 dto
 */
public class ReceiverDto {
    /**
     * "id": 367,
     * "userId": 203,
     * "createTime": "2016-10-20 17:21:58",
     * "mentionAccount": "",
     * "money": 0.53,
     * "activityId": 906,
     * "type": 1,
     * "status": 1
     */

    public int id;
    public int userId;
    public String createTime;
    public String mentionAccount;
    public double money;
    public int activityId;
    public int type;//1 领取红包  2 提现
    public int status;//0:提现中,1:已到账。
    public String no;//提现号


}
