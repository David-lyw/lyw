package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/10/14.
 */
public class ImageDto implements Serializable {
    /**
     * "id": 326,
     * "newsId": 282,
     * "createTime": "2016-10-14 17:46:51",
     * "url": "http://emall.eteclab.com/static//images/20161014/65/1476438410765953239.png"
     */
    public int id;
    public int newsId;
    public String createTime;
    public String url;

}
