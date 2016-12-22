package com.example.david.Dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by David on 16/10/14.
 */
public class ShowBillDto implements Serializable {
    /**
     * "id": 282,
     * "userId": 230,
     * "createTime": "2016-10-14 17:46:51",
     * "status": 0,
     * "createBy": "123",
     * "portrait": "http://emall.eteclab.com/static//head/20161011/230/230997419.png",
     * "productName": "凯尔威",
     * "images": [
     * {
     * "id": 326,
     * "newsId": 282,
     * "createTime": "2016-10-14 17:46:51",
     * "url": "http://emall.eteclab.com/static//images/20161014/65/1476438410765953239.png"
     * }
     * ],
     * "activityId": 895,
     * "content": "天啦噜"
     */
    public int id;
    public int newsId;
    public String createTime;
    public int status;
    public String createBy;
    public String portrait;
    public String productName;
    public List<ImageDto> images;
    public int activityId;
    public String content;
    public String activityNo;//期号

}
