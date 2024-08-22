package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
}
