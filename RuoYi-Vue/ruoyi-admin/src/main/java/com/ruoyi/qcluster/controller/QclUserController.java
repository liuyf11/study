package com.ruoyi.qcluster.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.qcluster.domain.QclUser;
import com.ruoyi.qcluster.service.IQclUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/qcluster/qcluster")
public class QclUserController extends BaseController
{
    @Autowired
    private IQclUserService qclUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:list')")
    @GetMapping("/list")
    public TableDataInfo list(QclUser qclUser)
    {
        startPage();
        List<QclUser> list = qclUserService.selectQclUserList(qclUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QclUser qclUser)
    {
        List<QclUser> list = qclUserService.selectQclUserList(qclUser);
        ExcelUtil<QclUser> util = new ExcelUtil<QclUser>(QclUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qclUserService.selectQclUserById(id));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QclUser qclUser)
    {
        return toAjax(qclUserService.insertQclUser(qclUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QclUser qclUser)
    {
        return toAjax(qclUserService.updateQclUser(qclUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('qcluster:qcluster:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qclUserService.deleteQclUserByIds(ids));
    }
}
