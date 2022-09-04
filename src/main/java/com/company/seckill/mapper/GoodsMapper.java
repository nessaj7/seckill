package com.company.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.seckill.pojo.Goods;
import com.company.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author kuhn
 * @since 2022-08-31
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();


    /**
     * 获取商品详情
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
