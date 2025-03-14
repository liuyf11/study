import request from '@/utils/request'

// 查询商品信息列表
export function listGood(query) {
  return request({
    url: '/good/good/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getGood(goodId) {
  return request({
    url: '/good/good/' + goodId,
    method: 'get'
  })
}

// 新增商品信息
export function addGood(data) {
  return request({
    url: '/good/good',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateGood(data) {
  return request({
    url: '/good/good',
    method: 'put',
    data: data
  })
}

// 删除商品信息
export function delGood(goodId) {
  return request({
    url: '/good/good/' + goodId,
    method: 'delete'
  })
}
