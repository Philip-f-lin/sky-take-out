package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
@Api(tags = "店舖相關接口")
public class ShopController {

    private static final String KEY = "SHOP_STATUS";

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 設置店鋪的營業狀態
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("設置店鋪的營業狀態")
    public Result setStatus(@PathVariable Integer status){
        log.info("設置店鋪的營業狀態: {}", status == 1 ? "營業中" : "打烊中");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

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
