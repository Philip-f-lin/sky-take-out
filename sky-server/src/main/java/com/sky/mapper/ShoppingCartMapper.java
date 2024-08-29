package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 動態條件查詢
     * @param shoppingCartDTO
     * @return
     */
    List<ShoppingCart> list(ShoppingCartDTO shoppingCartDTO);

    /**
     * 根據 id 修改商品數量
     * @param shoppingCart
     */
    @Update("update sky_take_out.shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);
}
