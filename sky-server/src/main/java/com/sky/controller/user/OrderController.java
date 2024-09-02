package com.sky.controller.user;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(value = "使用者端訂單相關接口")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 使用者下單
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("使用者下單")
    public Result<OrderSubmitVO> submit(OrdersSubmitDTO ordersSubmitDTO){
        log.info("使用者下單，參數為： {}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);

    }

    @GetMapping("/reminder/{id}")
    @ApiOperation("使用者催單")
    public Result reminder(@PathVariable("id") Long id){
        orderService.reminder(id);
        return Result.success();
    }
}
