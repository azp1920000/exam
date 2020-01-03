package com.example.exam.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sku {

    //自增商品ID
    private Long skuId;
    //商品名字
    private String skuName;
    //售价
    private Float price;
    //分类ID
    private Long categoryId;
    //品牌ID
    private Long brandId;
    //描述
    private String description;
    //商品创建时间
    private LocalDateTime createTime;
    //商品最后修改时间
    private LocalDateTime updateTime;
    //删除（0：否，1：是）
    private Boolean deleted;



}
