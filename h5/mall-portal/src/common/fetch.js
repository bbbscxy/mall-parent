import 'whatwg-fetch';
import 'es6-promise';
import * as common from 'common/common.js';
import { Toast } from 'antd-mobile';

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response.text();
  } else {
    var error = new Error(response.statusText);
    error.response = response;
    throw error;
  }
}

function parseJSON(response) {
	var result = JSON.parse(response);
	if(result.code == 99){
		//需要登录
		common.gotoLogin();
		common.storeRemove("token");
		return result;
	}
  return result;
}

// 发送 get 请求
export function get(url) {
	url = common.urlTransmit(url);
	var headers = new Headers();
	headers.append('Accept', 'application/json'); 
	if(common.storeGet("token")){
		headers.append('token', common.storeGet("token")); 
	}
  let requestConfig = {
    method: "GET",
    credentials: "include",
    headers: headers
  }
  return fetch(url, requestConfig).then(checkStatus).then(parseJSON);
}

// 发送 post 请求
export function post(url, paramsObj) {
  url = common.urlTransmit(url);
  var headers = new Headers();
	headers.append('Accept', 'application/json, text/plain, */*'); 
	headers.append('Content-Type', 'application/x-www-form-urlencoded'); 
	if(common.storeGet("token")){
		headers.append('token', common.storeGet("token")); 
	}
	return fetch(url, {
	    method: 'POST',
	    credentials: "include",
	    headers: headers,
	    body: obj2params(paramsObj)
	}).then(checkStatus).then(parseJSON);
}

// 将对象拼接成 key1=val1&key2=val2&key3=val3 的字符串形式
function obj2params(obj) {
    var result = '';
    var item;
    for (item in obj) {
        result += '&' + item + '=' + encodeURIComponent(obj[item]);
    }

    if (result) {
        result = result.slice(1);
    }

    return result;
}
