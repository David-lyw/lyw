package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/10/11.
 *
 * 清单列表
 */
public class ListDto implements Serializable {
    public int id;//记录ID
    public String udid;
    public int accountId;//用户id
    public int activityId;//活动id
    public String activityNo;//
    public String icon;//
    public int surplusNum;//剩余人次
    public int personTime;//总需人次
    public String name;//
    public int unitPrice;//
    public int productCount;//商品数量
    public String createTime;//

    public int tag = 0;//标识 未被选中（编辑）
    public int productCount_chosed;
}
