import * as fetch from 'common/fetch.js';

/**
 * 购物车列表
 */
export function cartList() {
  return fetch.get('/api/shop/order/cartList');
}

/**
 * 添加购物车
 */
export function addCart({goodsId, goodsNum}) {
  return fetch.post('/api/shop/order/addCart', {goodsId, goodsNum});
}

/**
 * 删除购物车
 */
export function delCart({cartIds}) {
  return fetch.post('/api/shop/order/delCart', {cartIds});
}