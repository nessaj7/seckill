package com.company.seckill.vo;

import com.company.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登录参数
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile // 自定义注解
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
