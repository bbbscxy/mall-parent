import { hashHistory } from 'react-router';

//服务器配置
export const DEFAULT_TITLE = "React Test";
export const PRODUCT_SERVER_URL = "http://www.51vim.com";
export const DEV_SERVER_URL = "http://10.10.1.15:9000";

//微信配置
export const APPID = "wx969e42c277d5f824";
export const REDIRECT_URI = "http://www.51vim.com/views/index.html";
export const WEIXIN_LOGIN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"

//微信登录
export function weixinLogin(){
	window.location.href = common.WEIXIN_LOGIN_URL;
}

//登录页面
export function gotoLogin(){
	hashHistory.push("/login");
}

//注册页面
export function gotoRegister(){
	hashHistory.push("/register");
}

//我的页面
export function gotoUser(){
	hashHistory.push("/user");
}

//购物车页面
export function gotoCart(){
	hashHistory.push("/cart");
}

//商品详情页面
export function gotoGoodsDetail(goodsId){
	hashHistory.push("/goods/"+goodsId);
}

//订单页面
export function gotoOrder({cartIds, addressId}){
	hashHistory.push("/order/"+cartIds+"/"+addressId);
}

//支付页面
export function gotoOrderPay({orderId}){
	hashHistory.push("/orderPay/"+orderId);
}

//用户订单列表页面
export function gotoUserOrderList(){
	hashHistory.push("/userOrderList");
}

//用户账户页面
export function gotoUserCash(){
	hashHistory.push("/userCash");
}

//当前登录用户ID
export function userId(){
	return userInfo().userId;
}

//当前是否处于开发环境
export function isDevEnv(){
	let env = process.env;
	if(env.NODE_ENV == 'dev'){
		return true;
	}else{
		return false;
	}
}

//开发、线上环境转换url
export function urlTransmit(url){
	let env = process.env;
	if(env.NODE_ENV == 'dev'){
		return DEV_SERVER_URL + url;
	}else{
		return PRODUCT_SERVER_URL + url;
	}
}

export function cacheExists(name){
	let cacheData = sessionStorage.getItem(name);
	if(cacheData){
		return true;
	}else{
		return false;
	}
}
export function cacheClear(name){
	return sessionStorage.clear();
}
export function cacheRemove(name){
	return sessionStorage.removeItem(name);
}
export function cacheGet(name){
	return JSON.parse(sessionStorage.getItem(name));
}
export function cacheSet(name, data){
	sessionStorage.setItem(name, JSON.stringify(data));
}

export function storeRemove(name){
	return localStorage.removeItem(name);
}
export function storeGet(name){
	return JSON.parse(localStorage.getItem(name));
}
export function storeSet(name, data){
	localStorage.setItem(name, JSON.stringify(data));
}