package com.company.seckill.controller;

import com.company.seckill.service.IUserService;
import com.company.seckill.vo.LoginVo;
import com.company.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录
 * @author kuhn
 */

@Controller
@RequestMapping("/login")
@Slf4j // 日志注解
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 功能描述：跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录功能
     * @param loginVo
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(HttpServletRequest request, HttpServletResponse response,
                            @Valid LoginVo loginVo) { // valid注解使自定义注解生效
//        log.info("{}", loginVo);
        log.info(loginVo.toString());
        return userService.login(request, response, loginVo);
    }
}
