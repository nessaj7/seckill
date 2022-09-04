package com.company.seckill.controller;

import com.company.seckill.pojo.User;
import com.company.seckill.service.IGoodsService;
import com.company.seckill.service.IUserService;
import com.company.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 商品
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

//    @Autowired
//    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;


//    /**
//     * 跳转到商品列表页
//     * @return
//     */
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
    /**
     * 跳转到商品列表页
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    /**
     * 跳转商品详情页
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        // 秒杀状态
        int secKillStatus = 0;
        // 秒杀倒计时
        int remainSeconds = 0;
        if (nowDate.before(startDate)) {
            // 秒杀未开始
            remainSeconds = (int) (startDate.getTime() - nowDate.getTime()) / 1000;

        } else if (nowDate.after(endDate)) {
            secKillStatus = 2; // 秒杀已结束
            remainSeconds = -1;
        } else {
            secKillStatus = 1; // 秒杀进行中
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }
}
