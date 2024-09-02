package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 數據統計相關接口
 */
@RestController
@RequestMapping("/admin/report")
@Api(tags = "數據統計相關接口")
@Slf4j
public class ReportController {

    @Resource
    private ReportService reportService;

    /**
     * 營業額統計
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("營業額統計")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end){
        log.info("營業額統計: {}, {}", begin, end);
        return Result.success(reportService.getTurnoverStatistics(begin, end));
    }
}
