package com.example.david.bean;

import java.io.Serializable;

/**
 * Created by David on 16/10/12.
 */
public class MessageBean implements Serializable{
    /**
     * "id": 199,
     "userId": 199,
     "activityId": 1,
     "type": 1,
     "createTime": "2016-04-15 11:09:21",
     "status": 1,
     "content": "中国畜牧业协会对2016中国畜牧行业颁奖晚会招商火爆进行中。。。 有新的评论！"
     */
    public int id;
    public int userId;
    public int activityId;
    public int type;
    public String createTime;//发布时间
    public int status;//状态 0 正常；1 已读
    public String content;//消息内容

}
