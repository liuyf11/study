import request from '@/utils/request'

// 查询会议管理列表
export function listMeeting(query) {
  return request({
    url: '/meeting/meeting/list',
    method: 'get',
    params: query
  })
}

// 查询会议管理详细
export function getMeeting(meetingID) {
  return request({
    url: '/meeting/meeting/' + meetingID,
    method: 'get'
  })
}

// 新增会议管理
export function addMeeting(data) {
  return request({
    url: '/meeting/meeting',
    method: 'post',
    data: data
  })
}

// 修改会议管理
export function updateMeeting(data) {
  return request({
    url: '/meeting/meeting',
    method: 'put',
    data: data
  })
}

// 删除会议管理
export function delMeeting(meetingID) {
  return request({
    url: '/meeting/meeting/' + meetingID,
    method: 'delete'
  })
}
