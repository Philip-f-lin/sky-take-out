package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜色相關接口")
@Slf4j
public class DishController {

    @Resource
    private DishService dishService;

    /**
     * 新增菜色
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜色")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜色: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 菜色分頁查詢
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜色分頁查詢")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜色分頁查詢: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);

    }

    /**
     * 菜色批量刪除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜色批量刪除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜色批量刪除: {}", ids);
        dishService.deleteBatch(ids);
        return Result.success(ids);
    }

    /**
     * 根據 id 查詢菜色
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據 id 查詢菜色")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根據 id 查詢菜色: {}" ,id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

}
