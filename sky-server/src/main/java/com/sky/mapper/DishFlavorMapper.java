package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味數據
     * @param flavors
     */
    void batchInsert(List<DishFlavor> flavors);

    /**
     * 根據菜色 id 刪除對應的口味數據
     * @param dishId
     */
    @Delete("DELETE FROM sky_take_out.dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);
}
