package com.ruoyi.good.service;

import java.util.List;
import com.ruoyi.good.domain.GoodType;
import com.ruoyi.good.domain.vo.GoodTypeVo;
import com.ruoyi.good.domain.vo.GoodVo;

/**
 * 商品分类Service接口
 * 
 * @author cc
 * @date 2025-03-11
 */
public interface IGoodTypeService 
{
    /**
     * 查询商品分类
     * 
     * @param typeId 商品分类主键
     * @return 商品分类
     */
    public GoodType selectGoodTypeByTypeId(Long typeId);

    /**
     * 查询商品分类列表
     * 
     * @param goodType 商品分类
     * @return 商品分类集合
     */
    public List<GoodType> selectGoodTypeList(GoodType goodType);

    /**
     * 查询商品分类列表，主要是为了显示分类对应的商品个数
     *
     * @param goodType 商品分类
     * @return GoodTypeVo 商品分类集合
     */
    public List<GoodTypeVo> selectGoodTypeListVo(GoodType goodType);
    /**
     * 新增商品分类
     * 
     * @param goodType 商品分类
     * @return 结果
     */
    public int insertGoodType(GoodType goodType);

    /**
     * 修改商品分类
     * 
     * @param goodType 商品分类
     * @return 结果
     */
    public int updateGoodType(GoodType goodType);

    /**
     * 批量删除商品分类
     * 
     * @param typeIds 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteGoodTypeByTypeIds(Long[] typeIds);

    /**
     * 删除商品分类信息
     * 
     * @param typeId 商品分类主键
     * @return 结果
     */
    public int deleteGoodTypeByTypeId(Long typeId);

}
