package com.ruoyi.good.domain.vo;
import com.ruoyi.good.domain.Good;

public class GoodVo extends Good{
    private String typeName;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
