import * as fetch from 'common/fetch.js';

/**
 * 预下单
 */
export function preOrder({cartIds, addressId}) {
  return fetch.post('/api/shop/order/preOrder', {cartIds, addressId});
}

/**
 * 下单
 */
export function createOrder({userId, cartIds, addressId}) {
  return fetch.post('/api/shop/order/createOrder', {userId, cartIds, addressId});
}

/**
 * 获取用户订单列表
 */
export function userOrderList({page, limit, userId}) {
  return fetch.get('/api/shop/order/userOrderList?page='+page+"&limit="+limit+"&userId="+userId);
}

/**
 * 获取订单详情
 */
export function orderDetail({orderId}) {
  return fetch.get('/api/shop/order/orderDetail?orderId='+orderId);
}