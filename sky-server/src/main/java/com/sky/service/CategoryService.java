package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * 新增分類
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分頁查詢
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根據id刪除分類
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改分類
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 啟用、停用分類
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根據類型查詢分類
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
