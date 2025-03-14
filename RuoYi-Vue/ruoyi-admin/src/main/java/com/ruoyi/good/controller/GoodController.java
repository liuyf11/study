package com.ruoyi.good.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.good.domain.vo.GoodVo;
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
import com.ruoyi.good.domain.Good;
import com.ruoyi.good.service.IGoodService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author cc
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/good/good")
public class GoodController extends BaseController
{
    @Autowired
    private IGoodService goodService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('good:good:list')")
    @GetMapping("/list")
    public TableDataInfo list(Good good)
    {
//        startPage();
//        List<Good> list = goodService.selectGoodList(good);
//        return getDataTable(list);
          startPage();
          List<GoodVo> list = goodService.selectGoodListVo(good);
          return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('good:good:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Good good)
    {
        List<Good> list = goodService.selectGoodList(good);
        ExcelUtil<Good> util = new ExcelUtil<Good>(Good.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('good:good:query')")
    @GetMapping(value = "/{goodId}")
    public AjaxResult getInfo(@PathVariable("goodId") Long goodId)
    {
        return success(goodService.selectGoodByGoodId(goodId));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('good:good:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Good good)
    {
        return toAjax(goodService.insertGood(good));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('good:good:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Good good)
    {
        return toAjax(goodService.updateGood(good));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('good:good:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodIds}")
    public AjaxResult remove(@PathVariable Long[] goodIds)
    {
        return toAjax(goodService.deleteGoodByGoodIds(goodIds));
    }
}
