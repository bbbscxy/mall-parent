import React, { Component } from 'react';
import { List, Toast, Modal } from 'antd-mobile';
import './userSetting.less';

import * as common from 'common/common.js';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

import * as userApi from 'common/api/UserApi.js';

const alert = Modal.alert;
const Item = List.Item;
const isIPhone = new RegExp('\\biPhone\\b|\\biPod\\b', 'i').test(window.navigator.userAgent);
let wrapProps;
if (isIPhone) {
  wrapProps = {
    onTouchStart: e => e.preventDefault(),
  };
}

/**
 * 设置组件
 */
class UserSetting extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	userInfo: {},
	    	exitModel: false
	    }
	}    
	
	componentDidMount(){
		Toast.loading("", 3);
		userApi.userInfo().then(result => {
			Toast.hide();
			if(result.code == 20000){
				this.setState({
					userInfo: result.data
				})
			}else{
				Toast.info("获取用户信息失败");
			}
		})
	}
	
	//退出账号
	exit = () => {
		this.setState({
	      exitModel: true,
		})
	}
	
	//确定注销
	sure = () => {
			this.setState({
      	exitModel: false,
    	});
    	Toast.info("退出成功", 1);
    	common.storeRemove("token");
			setTimeout(() => {
				window.history.back();
			}, 1000)
	}
	
	//取消
	cancel = () => {
		this.setState({
      exitModel: false,
    });
	}
	
	onClose = () => {
    this.setState({
      exitModel: false,
    });
  }
	
	render(){
		const { userInfo, exitModel } = this.state;
		return(
			<div className="userSetting">
				<Modal
					className="modal"
          title="确定退出?"
          transparent
          maskClosable={true}
          platform="ios"
          visible={exitModel}
          onClose={this.onClose.bind(this)}
          footer={[{ text: '确定', onPress: this.sure.bind(this)}, { text: '取消', onPress: this.cancel.bind(this)}]}
        >
        </Modal>
				<Header title={"设置"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<List className="my_list">
	        	<Item extra={<img className="my_head" src="assets/img/head.jpg"/>}>头像</Item>
	        	<Item extra={userInfo.userName}>昵称</Item>
	        	<Item extra={userInfo.loginAccount}>账号</Item>
      	</List>
      	<SeperateLine lineHeight={"0.1rem"} bg={"#eee"}></SeperateLine>
      	<List className="my_list">
	        	<Item extra={"退出账号"} onClick={this.exit.bind(this)}>{userInfo.loginAccount}</Item>
      	</List>
			</div>
		)
	}
}

export default UserSetting;