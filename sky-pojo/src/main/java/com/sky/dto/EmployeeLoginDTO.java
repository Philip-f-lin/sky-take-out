package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "員工登入時傳遞的資料模型")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty("使用者名稱")
    private String username;

    @ApiModelProperty("密碼")
    private String password;

}
