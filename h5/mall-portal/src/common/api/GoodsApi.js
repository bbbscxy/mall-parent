import * as fetch from 'common/fetch.js';

/**
 * 获取商品详情
 */
export function goodsDetail({goodsId}) {
  return fetch.get('/api/shop/goods/goodsDetail?goodsId='+goodsId);
}