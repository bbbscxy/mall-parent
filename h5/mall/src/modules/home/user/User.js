import React, { Component } from 'react';
import { Toast } from 'antd-mobile';
import './user.less';

import * as common from 'common/common.js';

import SeperateLine from 'common/components/SeperateLine.js';
import Bottom from 'common/containers/Bottom.js';

import UserProfile from './sub/UserProfile.js';
import UserToolsList from './sub/UserToolsList.js';
import UserRecommend from './sub/UserRecommend.js';

import * as userApi from 'common/api/UserApi.js';

/**
 * 用户组件
 */
class User extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	userInfo: {}
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
	
	render(){
		const { userInfo } = this.state;
		return(
			<div className="user">
				<UserProfile userInfo={userInfo}></UserProfile>
				<UserToolsList></UserToolsList>
				{/*<UserRecommend></UserRecommend>*/}
				<SeperateLine lineHeight={"1.1rem"}></SeperateLine>
				<Bottom selectedTab="user"></Bottom>
			</div>
		)
	}
}

export default User;