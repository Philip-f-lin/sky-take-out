package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根據菜色 id 查詢對應的套餐 id
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in(1, 2, 3, 4)
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);
}
