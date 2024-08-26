package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根據分類 id 查詢菜色數量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from sky_take_out.dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜色數據
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜色分頁查詢
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根據主鍵值查詢菜色
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.dish where id = #{id}")
    Dish getId(Long id);

    /**
     * 根據主鍵刪除菜色數據
     * @param id
     */
    @Delete("DELETE from sky_take_out.dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 根據菜色 id 集合批量刪除菜色數據
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根據菜色 id 集合批量刪除關聯的口味數據
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根據 id 動態修改菜品數據
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 動態條件查詢菜色
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 依套餐id查詢菜色
     * @param setmealId
     * @return
     */
    @Select("select a.* from sky_take_out.dish a left join sky_take_out.setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 根據條件統計菜色數量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
