package com.ruoyi.good.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.good.domain.vo.GoodTypeVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.good.domain.GoodType;
import com.ruoyi.good.service.IGoodTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品分类Controller
 * 
 * @author cc
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/good/type")
public class GoodTypeController extends BaseController
{
    @Autowired
    private IGoodTypeService goodTypeService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('good:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodType goodType)
    {
//        startPage();
//        List<GoodType> list = goodTypeService.selectGoodTypeList(goodType);
//        return getDataTable(list);
        startPage();
        List<GoodTypeVo> list = goodTypeService.selectGoodTypeListVo(goodType);
        return getDataTable(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('good:type:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodType goodType)
    {
        List<GoodType> list = goodTypeService.selectGoodTypeList(goodType);
        ExcelUtil<GoodType> util = new ExcelUtil<GoodType>(GoodType.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('good:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(goodTypeService.selectGoodTypeByTypeId(typeId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('good:type:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodType goodType)
    {
        return toAjax(goodTypeService.insertGoodType(goodType));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('good:type:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodType goodType)
    {
        return toAjax(goodTypeService.updateGoodType(goodType));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('good:type:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(goodTypeService.deleteGoodTypeByTypeIds(typeIds));
    }
}
