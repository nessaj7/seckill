package com.company.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.seckill.pojo.User;
import com.company.seckill.vo.LoginVo;
import com.company.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kuhn
 * @since 2022-08-27
 */
public interface IUserService extends IService<User> {
    /**
     * 功能描述：登录
     * @param loginVo
     * @return
     */
//    RespBean doLogin(LoginVo loginVo);
    RespBean login(HttpServletRequest request, HttpServletResponse response,
                   LoginVo loginVo);

    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    User getByUserTicket(String userTicket, HttpServletRequest request,
                         HttpServletResponse response);
}
