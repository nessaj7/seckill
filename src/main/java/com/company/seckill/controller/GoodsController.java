package com.company.seckill.controller;

import com.company.seckill.pojo.User;
import com.company.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 商品
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

//    @Autowired
//    private IUserService userService;

    /**
     * 跳转到商品列表页
     * @return
     */
//    @RequestMapping("/toList")
//    public String toList(/*HttpSession session, */HttpServletRequest request,
//                            HttpServletResponse response, Model model,
//                            @CookieValue("userTicket") String ticket) {
//        if (!StringUtils.hasText(ticket)) {
//            return "login";
//        }
//        // 通过session获取用户信息
////        User user = (User) session.getAttribute(ticket);
//        // 通过redis获取用户信息
//        User user = userService.getUserByCookie(ticket, request, response);
//
//        if (user == null) {
//            return "login";
//        }
//        model.addAttribute("user", user);
//        return "goodsList";
//    }

    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        return "goodsList";
    }
}
