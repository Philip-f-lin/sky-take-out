package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Resource
    private DishMapper dishMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Resource
    private SetMealDishMapper setMealDishMapper;

    /**
     * 新增菜色和相對應的口味
     * @param dishDTO
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();

        BeanUtils.copyProperties(dishDTO, dish);
        // 向菜色插入一條數據
        dishMapper.insert(dish);

        // 獲取 insert 語句生成的主鍵值
        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            // 向口味表插入 n 條數據
            dishFlavorMapper.batchInsert(flavors);
        }
    }

    /**
     * 菜色分頁查詢
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getPageNum(), page.getResult());
    }

    /**
     * 菜色批量刪除
     * @param ids
     */
    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 判斷當前菜色是否能夠刪除 - 是否存在正在販售的菜色
        for (Long id : ids) {
            Dish dish = dishMapper.getId(id);
            if (dish.getStatus() == StatusConstant.ENABLE){
                // 當前菜色正在販售，無法刪除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 判斷當前菜色是否能夠刪除 - 是否被套餐關聯了
        List<Long> setMealIds = setMealDishMapper.getSetMealIdsByDishIds(ids);
        if (setMealIds != null && !setMealIds.isEmpty()){
            // 當前菜色關聯套餐，無法刪除
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        // 刪除菜色表中的菜色數據
        /*for (Long id : ids) {
            dishMapper.deleteById(id);
            // 刪除菜色關聯的口味數據
            dishFlavorMapper.deleteByDishId(id);
        }*/

        // 根據菜色 id 集合批量刪除菜色數據
        // sql: delete from dish where id in (? ? ?)
        dishMapper.deleteByIds(ids);

        // 根據菜色 id 集合批量刪除關聯的口味數據
        // sql: delete from dish_flavor where dish_id in (? ? ?)
        dishMapper.deleteByDishIds(ids);

    }

    /**
     * 根據 id 查詢菜色和對應的口味數據
     * @param id
     * @return
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        // 根據 id 查詢菜色數據
        Dish dish = dishMapper.getId(id);

        // 根據菜色 id 查詢口味數據
        List<DishFlavor> dishFlavor = dishFlavorMapper.getByDishId(id);

        // 將查詢到的數據封裝到 DishVO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavor);

        return dishVO;
    }

    /**
     * 根據 id 修改菜色基本訊息和對應的口味訊息
     * @param dishDTO
     */
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 修改菜色表基本訊息
        dishMapper.update(dish);

        // 刪除原有的口味訊息
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        // 重新插入口味數據
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            // 向口味表插入 n 條數據
            dishFlavorMapper.batchInsert(flavors);
        }

    }
}
