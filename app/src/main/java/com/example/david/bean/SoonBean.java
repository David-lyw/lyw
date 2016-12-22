package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by David on 16/10/13.
 */
public class SoonBean implements Serializable {
    /**
     * "id": 871,
     * "productId": 1,
     * "templateId": 1,
     * "activityNo": "16505581",
     * "name": "久清堂芪贞",
     * "status": 1,
     * "activityStatus": 3,
     * "tips": "久清堂芪贞",
     * "unitPrice": 1,
     * "percent": 100,
     * "totalNum": 2,
     * "personTime": 2,
     * "beginTime": "2016-10-10 09:53:40",
     * "endTime": "2016-10-13 15:45:40",
     * "recommend": 1,
     * "icon": "http://static.test.mobioa.cn/star/1.jpg",
     * "networkJustice": "<p><img alt=\"\" class=\"lazyout-detail\" src=\"http://img30.360buyimg.com/cf/jfs/t2038/268/1790951644/500417/1e03f20e/5680aa6eNbf160f1d.jpg\" style=\"display:inline; height:2150px; width:647px\" /></p>",
     * "collection": 1,
     * <p>
     * "accountId": 201,
     * "accountName": "一枝",
     * "portrait": null,
     * "personNo": "871-358842",
     * "haomiao": -4249616,
     * "timeString": "今天 15:45",
     * "accountIdMy": null,
     * "accountNameMy": null,
     * "personNoMy": null
     */
    public int id;
    public int productId;
    public int templateId;
    public String activityNo;
    public String name;//活动名称
    public int status;
    public int activityStatus;
    public String tips;
    public double unitPrice;
    public double percent;//百分比
    public int totalNum;//参与人数
    public int personTime;//总需人数
    public String beginTime;
    public String endTime;
    public int recommend;
    public String icon;
    public String networkJustice;
    public int collection;//收藏  1 收藏   0不收藏

    public int accountId;
    public String accountName;
    public String portrait;
    public String personNo;
    public long haomiao;
    public String timeString;

//    public int accountIdMy;
//    public String accountNameMy;
//    public String personNoMy;
}
