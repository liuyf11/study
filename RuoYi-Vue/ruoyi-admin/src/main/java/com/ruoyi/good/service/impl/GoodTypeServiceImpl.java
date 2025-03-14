package com.ruoyi.good.service.impl;

import java.util.List;

import com.ruoyi.good.domain.vo.GoodTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.good.domain.Good;
import com.ruoyi.good.mapper.GoodTypeMapper;
import com.ruoyi.good.domain.GoodType;
import com.ruoyi.good.service.IGoodTypeService;

/**
 * 商品分类Service业务层处理
 * 
 * @author cc
 * @date 2025-03-11
 */
@Service
public class GoodTypeServiceImpl implements IGoodTypeService 
{
    @Autowired
    private GoodTypeMapper goodTypeMapper;

    /**
     * 查询商品分类
     * 
     * @param typeId 商品分类主键
     * @return 商品分类
     */
    @Override
    public GoodType selectGoodTypeByTypeId(Long typeId)
    {
        return goodTypeMapper.selectGoodTypeByTypeId(typeId);
    }

    /**
     * 查询商品分类列表
     * 
     * @param goodType 商品分类
     * @return 商品分类
     */
    @Override
    public List<GoodType> selectGoodTypeList(GoodType goodType)
    {
        return goodTypeMapper.selectGoodTypeList(goodType);
    }

    /**
     * 查询商品分类列表,主要是为了显示分类对应的商品个数
     *
     * @param goodType 商品分类
     * @return GoodTypeVo 商品分类
     */
    @Override
    public List<GoodTypeVo> selectGoodTypeListVo(GoodType goodType) {
        return goodTypeMapper.selectGoodTypeListVo(goodType);
    }

    /**
     * 新增商品分类
     * 
     * @param goodType 商品分类
     * @return 结果
     */
    @Transactional
    @Override
    public int insertGoodType(GoodType goodType)
    {
        int rows = goodTypeMapper.insertGoodType(goodType);
        insertGood(goodType);
        return rows;
    }

    /**
     * 修改商品分类
     * 
     * @param goodType 商品分类
     * @return 结果
     */
    @Transactional
    @Override
    public int updateGoodType(GoodType goodType)
    {
        goodTypeMapper.deleteGoodByTypeId(goodType.getTypeId());
        insertGood(goodType);
        return goodTypeMapper.updateGoodType(goodType);
    }

    /**
     * 批量删除商品分类
     * 
     * @param typeIds 需要删除的商品分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGoodTypeByTypeIds(Long[] typeIds)
    {
        goodTypeMapper.deleteGoodByTypeIds(typeIds);
        return goodTypeMapper.deleteGoodTypeByTypeIds(typeIds);
    }

    /**
     * 删除商品分类信息
     * 
     * @param typeId 商品分类主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGoodTypeByTypeId(Long typeId)
    {
        goodTypeMapper.deleteGoodByTypeId(typeId);
        return goodTypeMapper.deleteGoodTypeByTypeId(typeId);
    }

    /**
     * 新增商品信息信息
     * 
     * @param goodType 商品分类对象
     */
    public void insertGood(GoodType goodType)
    {
        List<Good> goodList = goodType.getGoodList();
        Long typeId = goodType.getTypeId();
        if (StringUtils.isNotNull(goodList))
        {
            List<Good> list = new ArrayList<Good>();
            for (Good good : goodList)
            {
                good.setTypeId(typeId);
                list.add(good);
            }
            if (list.size() > 0)
            {
                goodTypeMapper.batchGood(list);
            }
        }
    }
}
