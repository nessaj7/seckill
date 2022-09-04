package com.company.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.seckill.pojo.Order;
import com.company.seckill.pojo.User;
import com.company.seckill.vo.GoodsVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kuhn
 * @since 2022-08-31
 */
public interface IOrderService extends IService<Order> {
    /**
     * 秒杀
     * @param user
     * @param goods
     * @return
     */
    Order seckill(User user, GoodsVo goods);
}
