package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("userShopController")
@Slf4j
@RequestMapping("/user/shop")
@Api(tags = "店舖相關接口")
public class ShopController {

    private static final String KEY = "SHOP_STATUS";

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("取得店鋪營業狀態")
    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        if(shopStatus != null){
            log.info("取得店鋪營業狀態:{}", shopStatus == 1 ? "營業中" : "打烊中");
        }
        return Result.success(shopStatus);
    }
}
