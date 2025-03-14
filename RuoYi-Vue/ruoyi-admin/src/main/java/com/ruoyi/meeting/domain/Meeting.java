package com.ruoyi.meeting.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会议管理对象 meeting
 * 
 * @author cc
 * @date 2025-03-12
 */
public class Meeting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会议唯一标识 */
    private Long meetingID;

    /** 会议题目 */
    @Excel(name = "会议题目")
    private String meetingTitle;

    /** 会议地点 */
    @Excel(name = "会议地点")
    private String location;

    /** 会议开始时间，具体到年月日几点几分 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会议开始时间，具体到年月日几点几分", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 会议结束时间，具体到年月日几点几分（可选） */
    @Excel(name = "会议结束时间，具体到年月日几点几分", readConverterExp = "可=选")
    private Date endTime;

    /** 截止报名时间，具体到年月日几点几分 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止报名时间，具体到年月日几点几分", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadlineForRegistration;

    /** 会议介绍 */
    @Excel(name = "会议介绍")
    private String departmentRequirement;

    public void setMeetingID(Long meetingID) 
    {
        this.meetingID = meetingID;
    }

    public Long getMeetingID() 
    {
        return meetingID;
    }

    public void setMeetingTitle(String meetingTitle) 
    {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingTitle() 
    {
        return meetingTitle;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setDeadlineForRegistration(Date deadlineForRegistration) 
    {
        this.deadlineForRegistration = deadlineForRegistration;
    }

    public Date getDeadlineForRegistration() 
    {
        return deadlineForRegistration;
    }

    public void setDepartmentRequirement(String departmentRequirement) 
    {
        this.departmentRequirement = departmentRequirement;
    }

    public String getDepartmentRequirement() 
    {
        return departmentRequirement;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("meetingID", getMeetingID())
            .append("meetingTitle", getMeetingTitle())
            .append("location", getLocation())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("deadlineForRegistration", getDeadlineForRegistration())
            .append("departmentRequirement", getDepartmentRequirement())
            .toString();
    }
}
