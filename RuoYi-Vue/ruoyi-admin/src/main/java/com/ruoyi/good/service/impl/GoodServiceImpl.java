package com.ruoyi.good.service.impl;

import java.util.List;

import com.ruoyi.good.domain.vo.GoodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.good.mapper.GoodMapper;
import com.ruoyi.good.domain.Good;
import com.ruoyi.good.service.IGoodService;

/**
 * 商品信息Service业务层处理
 * 
 * @author cc
 * @date 2025-03-11
 */
@Service
public class GoodServiceImpl implements IGoodService 
{
    @Autowired
    private GoodMapper goodMapper;

    /**
     * 查询商品信息
     * 
     * @param goodId 商品信息主键
     * @return 商品信息
     */
    @Override
    public Good selectGoodByGoodId(Long goodId)
    {
        return goodMapper.selectGoodByGoodId(goodId);
    }

    /**
     * 查询商品信息列表
     * 
     * @param good 商品信息
     * @return 商品信息
     */
    @Override
    public List<Good> selectGoodList(Good good)
    {
        return goodMapper.selectGoodList(good);
    }

    /**
     * 新增商品信息
     * 
     * @param good 商品信息
     * @return 结果
     */
    @Override
    public int insertGood(Good good)
    {
        return goodMapper.insertGood(good);
    }

    /**
     * 修改商品信息
     * 
     * @param good 商品信息
     * @return 结果
     */
    @Override
    public int updateGood(Good good)
    {
        return goodMapper.updateGood(good);
    }

    /**
     * 批量删除商品信息
     * 
     * @param goodIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodByGoodIds(Long[] goodIds)
    {
        return goodMapper.deleteGoodByGoodIds(goodIds);
    }

    /**
     * 删除商品信息信息
     * 
     * @param goodId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodByGoodId(Long goodId)
    {
        return goodMapper.deleteGoodByGoodId(goodId);
    }

    @Override
    public List<GoodVo> selectGoodListVo(Good good) {
        return goodMapper.selectGoodListVo(good);
    }
}
