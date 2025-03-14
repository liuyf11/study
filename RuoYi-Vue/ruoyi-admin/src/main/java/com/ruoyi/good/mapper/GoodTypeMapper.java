package com.ruoyi.good.mapper;

import java.util.List;
import com.ruoyi.good.domain.GoodType;
import com.ruoyi.good.domain.Good;
import com.ruoyi.good.domain.vo.GoodTypeVo;

/**
 * 商品分类Mapper接口
 * 
 * @author cc
 * @date 2025-03-11
 */
public interface GoodTypeMapper 
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
     * 删除商品分类
     * 
     * @param typeId 商品分类主键
     * @return 结果
     */
    public int deleteGoodTypeByTypeId(Long typeId);

    /**
     * 批量删除商品分类
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodTypeByTypeIds(Long[] typeIds);

    /**
     * 批量删除商品信息
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodByTypeIds(Long[] typeIds);
    
    /**
     * 批量新增商品信息
     * 
     * @param goodList 商品信息列表
     * @return 结果
     */
    public int batchGood(List<Good> goodList);
    

    /**
     * 通过商品分类主键删除商品信息信息
     * 
     * @param typeId 商品分类ID
     * @return 结果
     */
    public int deleteGoodByTypeId(Long typeId);
}
