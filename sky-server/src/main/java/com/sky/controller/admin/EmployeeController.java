package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 員工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "員工相關接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登入
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "員工登入")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("員工登入：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登入成功後，產生jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */

    @PostMapping("/logout")
    @ApiOperation("員工退出")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 新增員工
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增員工")
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增員工: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 員工分頁查詢
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("員工分頁查詢")
    // 因為傳進來的參數不是 jason ，所以不用加上 @RequestBody
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("員工分頁查詢: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 啟用禁用員工帳號
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("啟用禁用員工帳號")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("啟用禁用員工帳號：{}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根據 id 查詢使用者資訊
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根據 id 查詢使用者資訊")
    public Result<Employee> getById(@PathVariable Integer id){
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 編輯員工資訊
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("編輯員工資訊")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("編輯員工資訊：{}", employeeDTO );
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
