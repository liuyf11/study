package com.ruoyi.good.service;

import java.util.List;
import com.ruoyi.good.domain.Good;
import com.ruoyi.good.domain.vo.GoodVo;

/**
 * 商品信息Service接口
 * 
 * @author cc
 * @date 2025-03-11
 */
public interface IGoodService 
{
    /**
     * 查询商品信息
     * 
     * @param goodId 商品信息主键
     * @return 商品信息
     */
    public Good selectGoodByGoodId(Long goodId);

    /**
     * 查询商品信息列表
     * 
     * @param good 商品信息
     * @return 商品信息集合
     */
    public List<Good> selectGoodList(Good good);

    /**
     * 新增商品信息
     * 
     * @param good 商品信息
     * @return 结果
     */
    public int insertGood(Good good);

    /**
     * 修改商品信息
     * 
     * @param good 商品信息
     * @return 结果
     */
    public int updateGood(Good good);

    /**
     * 批量删除商品信息
     * 
     * @param goodIds 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteGoodByGoodIds(Long[] goodIds);

    /**
     * 删除商品信息信息
     * 
     * @param goodId 商品信息主键
     * @return 结果
     */
    public int deleteGoodByGoodId(Long goodId);

    /**
     * 通过联表方式查询商品信息
     *
     * @param good 商品信息
     * @return 商品信息集合
     */
    public List<GoodVo> selectGoodListVo(Good good);
}
