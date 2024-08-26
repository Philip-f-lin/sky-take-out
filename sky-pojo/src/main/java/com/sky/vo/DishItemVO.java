package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishItemVO implements Serializable {

    //菜色名稱
    private String name;

    //份數
    private Integer copies;

    //菜色圖片
    private String image;

    //菜色描述
    private String description;
}
