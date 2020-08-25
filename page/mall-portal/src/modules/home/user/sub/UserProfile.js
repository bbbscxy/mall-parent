import React, { Component } from 'react';
import { hashHistory } from 'react-router';
import { List, Button, Toast } from 'antd-mobile';
import './userProfile.less';

import * as common from 'common/common.js';

const Item = List.Item;

/**
 * 用户子组件:用户介绍
 */
class UserProfile extends Component{
	
	//登录
	login = () => {
		common.gotoLogin();
	}
	
	//设置页面
	gotoSetting = () => {
		hashHistory.push("/userSetting");
	}
	
	render(){
		const { userInfo } = this.props;
		return(
			<div className="userinfo_box">
				<div className="userinfo">
					<div className="head">
						<img src="assets/img/head.jpg"/>
					</div>
					<img className="setting" src="assets/img/setting.png" onClick={this.gotoSetting.bind(this)}/>
					<div className="info">
						<span>{userInfo.loginAccount}</span>
					</div>
				</div>
			</div>
		)
	}
}

export default UserProfile;