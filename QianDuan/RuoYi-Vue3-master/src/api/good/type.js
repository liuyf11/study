import request from '@/utils/request'

// 查询商品分类列表
export function listType(query) {
  return request({
    url: '/good/type/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类详细
export function getType(typeId) {
  return request({
    url: '/good/type/' + typeId,
    method: 'get'
  })
}

// 新增商品分类
export function addType(data) {
  return request({
    url: '/good/type',
    method: 'post',
    data: data
  })
}

// 修改商品分类
export function updateType(data) {
  return request({
    url: '/good/type',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function delType(typeId) {
  return request({
    url: '/good/type/' + typeId,
    method: 'delete'
  })
}
