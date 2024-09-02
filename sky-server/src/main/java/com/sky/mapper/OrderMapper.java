package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    /**
     * 插入訂單數據
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根據訂單狀態和下單時間查詢訂單
     * @param status
     * @param time
     * @return
     */
    @Select("select * from sky_take_out.orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    @Select("select * from sky_take_out.orders where id = #{id}")
    Orders getById(Long id);

    /**
     * 根據動態條件統計營業額數據
     * @param map
     * @return
     */
    Double sumByMap(Map map);
}
