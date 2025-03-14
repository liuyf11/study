import request from '@/utils/request'

// 查询用户管理1列表
export function listQcluster(query) {
  return request({
    url: '/qcluster/qcluster/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理1详细
export function getQcluster(id) {
  return request({
    url: '/qcluster/qcluster/' + id,
    method: 'get'
  })
}

// 新增用户管理1
export function addQcluster(data) {
  return request({
    url: '/qcluster/qcluster',
    method: 'post',
    data: data
  })
}

// 修改用户管理1
export function updateQcluster(data) {
  return request({
    url: '/qcluster/qcluster',
    method: 'put',
    data: data
  })
}

// 删除用户管理1
export function delQcluster(id) {
  return request({
    url: '/qcluster/qcluster/' + id,
    method: 'delete'
  })
}
