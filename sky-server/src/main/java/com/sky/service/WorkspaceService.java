package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import java.time.LocalDateTime;

public interface WorkspaceService {

    /**
     * 根據時段統計營業數據
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * 查詢訂單管理數據
     * @return
     */
    OrderOverViewVO getOrderOverView();

    /**
     * 查詢菜品總覽
     * @return
     */
    DishOverViewVO getDishOverView();

    /**
     * 查詢套餐總覽
     * @return
     */
    SetmealOverViewVO getSetmealOverView();

}
