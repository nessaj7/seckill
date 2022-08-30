package com.company.seckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.seckill.exception.GlobalException;
import com.company.seckill.mapper.UserMapper;
import com.company.seckill.pojo.User;
import com.company.seckill.service.IUserService;
import com.company.seckill.utils.CookieUtil;
import com.company.seckill.utils.MD5Util;
import com.company.seckill.utils.UUIDUtil;
import com.company.seckill.utils.ValidatorUtil;
import com.company.seckill.vo.LoginVo;
import com.company.seckill.vo.RespBean;
import com.company.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kuhn
 * @since 2022-08-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements IUserService {
    @Resource
    private UserMapper userMapper;// 数据库查询 注入userMapper

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录实现类
     * @param loginVo
     * @return
     */
    @Override
    public RespBean login(HttpServletRequest request, HttpServletResponse response,
                          LoginVo loginVo) {
        //登录业务逻辑
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        // 参数校验 (使用自定义注解后，这部分就不需要了)
//        if (!StringUtils.hasText(mobile) || !StringUtils.hasText(password)) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        // 根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (user == null) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        // 判断密码是否正确
        if (!MD5Util.fromPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        return RespBean.success();
        // 生成cookie
        String ticket = UUIDUtil.uuid();
        // 用户信息放入session
//        request.getSession().setAttribute(ticket, user);

        // 将用户信息存入redis中
        redisTemplate.opsForValue().set("user:" + ticket, user);
        // 将信息存入本地cookie
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    @Override
    public User getByUserTicket(String userTicket, HttpServletRequest request,
                                HttpServletResponse response) {

        if (!StringUtils.hasText(userTicket)) {
            return null;
        }

        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }
}
