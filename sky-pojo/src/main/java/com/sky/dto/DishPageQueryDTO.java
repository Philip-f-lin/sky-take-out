package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    //分類id
    private Integer categoryId;

    //狀態 0表示停用 1表示啟用
    private Integer status;

}
