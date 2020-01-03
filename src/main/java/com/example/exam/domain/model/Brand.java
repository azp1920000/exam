package com.example.exam.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Brand {

    private Long brandId;
    private String brandName;
    private LocalDateTime createTime;
    private Boolean delete;

}
