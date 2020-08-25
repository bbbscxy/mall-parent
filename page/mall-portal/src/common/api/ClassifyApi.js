import * as fetch from 'common/fetch.js';

/**
 * 获取所有分类列表
 */
export function classifyList() {
  return fetch.get('/api/shop/classify/classifyList');
}

/**
 * 获取分类下商品列表
 */
export function goodsList({page, limit, classifyId}) {
  return fetch.get('/api/shop/goods/goodsList?page='+page+"&limit="+limit+"&classifyId="+classifyId);
}