package com.sky.vo;

import com.sky.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishVO implements Serializable {

    private Long id;
    //菜色名稱
    private String name;
    //菜色分類id
    private Long categoryId;
    //菜價
    private BigDecimal price;
    //圖片
    private String image;
    //描述訊息
    private String description;
    //0 停售 1 起售
    private Integer status;
    //更新時間
    private LocalDateTime updateTime;
    //分類名稱
    private String categoryName;
    //菜色關聯的口味
    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
