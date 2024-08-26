package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根據菜色 id 查詢對應的套餐 id
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in(1, 2, 3, 4)
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根據套餐 id 刪除套餐和菜色的關聯關係
     * @param setmealId
     */
    @Delete("delete from sky_take_out.setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根據套餐id查詢套餐和菜色的關聯關係
     * @param setmealId
     * @return
     */
    @Select("select * from sky_take_out.setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
