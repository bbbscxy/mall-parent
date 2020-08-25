import * as fetch from 'common/fetch.js';

/**
 * 获取用户地址列表
 */
export function userAddressList({userId}) {
  return fetch.get('/api/shop/address/userAddressList?userId='+userId);
}

/**
 * 获取用户默认地址
 */
export function userDefaultAddress() {
  return fetch.get('/api/shop/address/userDefaultAddress');
}

/**
 * 获取用户地址详情
 */
export function userAddressDetail({addressId}) {
  return fetch.get('/api/shop/address/userAddressDetail?addressId='+addressId);
}

/**
 * 修改用户地址
 */
export function editUserAddress({addressId, buyerId, buyerName, buyerPhone, deliveryProvinceId, deliveryCityId, deliveryAreaId, deliveryStreet}) {
  return fetch.post('/api/shop/address/editUserAddress', {
  	addressId, buyerId, buyerName, buyerPhone, deliveryProvinceId, deliveryCityId, deliveryAreaId, deliveryStreet
  });
}

/**
 * 发送短信
 */
export function sendPhoneCode({phone}) {
  return fetch.post('/api/shop/sendPhoneCode', { phone });
}

/**
 * 账号登录
 */
export function loginByAccount({loginAccount, loginPassword}) {
  return fetch.post('/api/shop/user/loginByAccount', {loginAccount, loginPassword});
}

/**
 * 手机号登录
 */
export function loginByPhone({loginPhone, loginCode}) {
  return fetch.post('/api/shop/user/loginByPhone', {loginPhone, loginCode});
}

/**
 * 获取用户信息
 */
export function userInfo() {
  return fetch.get('/api/shop/userInfo');
}