package com.ruoyi.meeting.controller;

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
import com.ruoyi.meeting.domain.Meeting;
import com.ruoyi.meeting.service.IMeetingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会议管理Controller
 * 
 * @author cc
 * @date 2025-03-12
 */
@RestController
@RequestMapping("/meeting/meeting")
public class MeetingController extends BaseController
{
    @Autowired
    private IMeetingService meetingService;

    /**
     * 查询会议管理列表
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:list')")
    @GetMapping("/list")
    public TableDataInfo list(Meeting meeting)
    {
        startPage();
        List<Meeting> list = meetingService.selectMeetingList(meeting);
        return getDataTable(list);
    }

    /**
     * 导出会议管理列表
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:export')")
    @Log(title = "会议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Meeting meeting)
    {
        List<Meeting> list = meetingService.selectMeetingList(meeting);
        ExcelUtil<Meeting> util = new ExcelUtil<Meeting>(Meeting.class);
        util.exportExcel(response, list, "会议管理数据");
    }

    /**
     * 获取会议管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:query')")
    @GetMapping(value = "/{meetingID}")
    public AjaxResult getInfo(@PathVariable("meetingID") Long meetingID)
    {
        return success(meetingService.selectMeetingByMeetingID(meetingID));
    }

    /**
     * 新增会议管理
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:add')")
    @Log(title = "会议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Meeting meeting)
    {
        return toAjax(meetingService.insertMeeting(meeting));
    }

    /**
     * 修改会议管理
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:edit')")
    @Log(title = "会议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Meeting meeting)
    {
        return toAjax(meetingService.updateMeeting(meeting));
    }

    /**
     * 删除会议管理
     */
    @PreAuthorize("@ss.hasPermi('meeting:meeting:remove')")
    @Log(title = "会议管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{meetingIDs}")
    public AjaxResult remove(@PathVariable Long[] meetingIDs)
    {
        return toAjax(meetingService.deleteMeetingByMeetingIDs(meetingIDs));
    }
}
