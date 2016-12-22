package com.example.david.Dto;

import java.io.Serializable;

/**
 * Created by David on 16/10/11.
 */
public class ActivityImageDto implements Serializable {
    public int id;
    public int productId;//商品ID
    public String image;//图片路径
    public int status;//状态 1 有效  0 无效
    public int sort;//排序
}
