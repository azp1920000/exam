package com.example.exam.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    //自增分类ID
    private Long categoryId;
    //分类名字
    private String categoryName;
    //分类创建时间
    private LocalDateTime createTime;
    //删除（0：否，1：是）
    private Boolean delete;
}
