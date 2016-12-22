package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/10/11.
 * 关注 用户收藏
 */
public class CollectionDto implements Serializable {
    /**
     * "id": 4,
     "productId": 4,
     "templateId": 4,
     "activityNo": "20160926002",
     "name": "凯尔威",
     "status": 1,
     "activityStatus": 3,
     "tips": "凯尔威",
     "unitPrice": 1,
     "percent": 1,
     "totalNum": 2,
     "personTime": 2,
     "beginTime": "2016-09-26 13:35:59",
     "endTime": "2016-10-10 10:24:13",
     "recommend": 1,
     "icon": "http://static.test.mobioa.cn/star/1.jpg",
     "networkJustice": "<p>《港囧》是由北京真乐道文化传播有限公司、北京光线影业有限公司等联合出品的爱情喜剧影片，该影片由徐峥执导，由徐峥、赵薇、包贝尔、杜鹃、葛民辉联合主 演。影片于2015年9月25日在全国上映。影片讲述了徐来陪伴老婆及家人来到香港旅游，计划与大学初恋杨伊偷偷会面，无奈被小舅子识破其醉翁之意不在 酒，从而引发一场欢乐香港游的囧途的故事。</p>",
     "collection": 1,
     "userActivityStatus": 0
     */

    public int id;//
    public int productId;//商品id
    public int templateId;//模板id
    public String activityNo;//活动期号
    public String name;//活动名称
    public int status;//状态（1：有效；0：无效）
    public int activityStatus;//活动状态（0：活动未开始；1：活动进行中；2：即将揭晓；3：活动结束）
    public String tips;//活动提示
    public int unitPrice;//人次单价（单位：分；默认：100分）
    public float percent;//进度(百分比)
    public int totalNum;//参与人次
    public int personTime;//总需人次（当活动类型为1时有效）
    public String beginTime;//
    public String endTime;//
    public int recommend;//0 不推荐   1 推荐
    public String icon;//图片路径
    public String networkJustice;//网络公正
    public int collection;//收藏  1 收藏   0不收藏
    public int userActivityStatus;//
}
