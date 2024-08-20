package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(value = "通用接口")
@Slf4j
public class CommonController {


    /**
     * 文件上傳
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件上傳")
    public Result<String> upload(MultipartFile file){
        log.info("文件上傳");
        return null;
    }
}
