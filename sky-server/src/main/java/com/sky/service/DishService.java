package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService{

    /**
     * 新增菜色和相對應的口味
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜色分頁查詢
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜色批量刪除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根據 id 查詢菜色和對應的口味數據
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);
}
