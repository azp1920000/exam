package com.example.exam.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SkuDTO {


    @ApiModelProperty("商品Id")
    private Long skuId;

    @ApiModelProperty("商品名字")
    private String skuName;

    @ApiModelProperty("商品价格")
    private Float price;

    @ApiModelProperty("分类名字")
    private String categoryName;

    @ApiModelProperty("品牌名字")
    private String brandName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("最后更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("品牌id")
    private Long brandId;


    @ApiModelProperty("多个分类ID，list集合")
    private List<Long> categoryIds;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
