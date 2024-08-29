package com.sky.service.impl;

import com.sky.dto.ShoppingCartDTO;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 添加購物車
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // 判斷當前加入到購物車中的商品是否已經存在

        // 如果已經存在，只需將數量加一

        // 如果不存在，需要插入一條購物車數據
    }
}
