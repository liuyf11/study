package com.ruoyi.meeting.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.meeting.mapper.MeetingMapper;
import com.ruoyi.meeting.domain.Meeting;
import com.ruoyi.meeting.service.IMeetingService;

/**
 * 会议管理Service业务层处理
 * 
 * @author cc
 * @date 2025-03-12
 */
@Service
public class MeetingServiceImpl implements IMeetingService 
{
    @Autowired
    private MeetingMapper meetingMapper;

    /**
     * 查询会议管理
     * 
     * @param meetingID 会议管理主键
     * @return 会议管理
     */
    @Override
    public Meeting selectMeetingByMeetingID(Long meetingID)
    {
        return meetingMapper.selectMeetingByMeetingID(meetingID);
    }

    /**
     * 查询会议管理列表
     * 
     * @param meeting 会议管理
     * @return 会议管理
     */
    @Override
    public List<Meeting> selectMeetingList(Meeting meeting)
    {
        return meetingMapper.selectMeetingList(meeting);
    }

    /**
     * 新增会议管理
     * 
     * @param meeting 会议管理
     * @return 结果
     */
    @Override
    public int insertMeeting(Meeting meeting)
    {
        return meetingMapper.insertMeeting(meeting);
    }

    /**
     * 修改会议管理
     * 
     * @param meeting 会议管理
     * @return 结果
     */
    @Override
    public int updateMeeting(Meeting meeting)
    {
        return meetingMapper.updateMeeting(meeting);
    }

    /**
     * 批量删除会议管理
     * 
     * @param meetingIDs 需要删除的会议管理主键
     * @return 结果
     */
    @Override
    public int deleteMeetingByMeetingIDs(Long[] meetingIDs)
    {
        return meetingMapper.deleteMeetingByMeetingIDs(meetingIDs);
    }

    /**
     * 删除会议管理信息
     * 
     * @param meetingID 会议管理主键
     * @return 结果
     */
    @Override
    public int deleteMeetingByMeetingID(Long meetingID)
    {
        return meetingMapper.deleteMeetingByMeetingID(meetingID);
    }
}
