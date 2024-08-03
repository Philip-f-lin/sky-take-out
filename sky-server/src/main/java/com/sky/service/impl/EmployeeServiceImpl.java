package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 員工登入
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根據使用者名稱查詢資料庫中的數據
        Employee employee = employeeMapper.getByUsername(username);

        //2、處理各種異常情況（使用者名稱不存在、密碼不對、帳號被鎖定）
        if (employee == null) {
            //帳號不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密碼比對
        // 對前端傳過來的密碼進行 MD5 加密處理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密碼錯誤
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //帳號被鎖定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、傳回實體對象
        return employee;
    }

    /**
     * 新增員工
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        // 對象屬性拷貝
        BeanUtils.copyProperties(employeeDTO, employee);

        // 設置帳號的狀態，默認正常狀態 1:啟用 0:停用
        employee.setStatus(StatusConstant.ENABLE);

        // 新增密碼，默認 123456 ，使用 MD5 加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        // 設置創建時間和修改時間
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // 設置當前創建者 id 和 修改者 id
        // TODO 之後再將此設置為當前登錄使用者的 id
        employee.setCreateUser(10L);
        employee.setUpdateUser(10L);

        employeeMapper.insert(employee);
    }
}
