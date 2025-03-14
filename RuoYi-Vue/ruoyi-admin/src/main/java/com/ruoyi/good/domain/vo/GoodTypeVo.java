package com.ruoyi.good.domain.vo;

import com.ruoyi.good.domain.GoodType;

public class GoodTypeVo extends GoodType {
    //商品个数
    private Integer goodCount;

    //get方法
    public Integer getGoodCount() {
        return goodCount;
    }

    //set方法
    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }
}
