package com.ruoyi.meeting.service;

import java.util.List;
import com.ruoyi.meeting.domain.Meeting;

/**
 * 会议管理Service接口
 * 
 * @author cc
 * @date 2025-03-12
 */
public interface IMeetingService 
{
    /**
     * 查询会议管理
     * 
     * @param meetingID 会议管理主键
     * @return 会议管理
     */
    public Meeting selectMeetingByMeetingID(Long meetingID);

    /**
     * 查询会议管理列表
     * 
     * @param meeting 会议管理
     * @return 会议管理集合
     */
    public List<Meeting> selectMeetingList(Meeting meeting);

    /**
     * 新增会议管理
     * 
     * @param meeting 会议管理
     * @return 结果
     */
    public int insertMeeting(Meeting meeting);

    /**
     * 修改会议管理
     * 
     * @param meeting 会议管理
     * @return 结果
     */
    public int updateMeeting(Meeting meeting);

    /**
     * 批量删除会议管理
     * 
     * @param meetingIDs 需要删除的会议管理主键集合
     * @return 结果
     */
    public int deleteMeetingByMeetingIDs(Long[] meetingIDs);

    /**
     * 删除会议管理信息
     * 
     * @param meetingID 会议管理主键
     * @return 结果
     */
    public int deleteMeetingByMeetingID(Long meetingID);
}
