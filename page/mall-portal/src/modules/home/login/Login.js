import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Tabs, WhiteSpace, Badge, List, InputItem, Picker, Button, Toast } from 'antd-mobile';
import { createForm } from 'rc-form';
import './login.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

import * as common from 'common/common.js';
import * as userApi from 'common/api/UserApi.js';

const Item = List.Item;
const TabPane = Tabs.TabPane;
const TabTitle = ({title}) => {
	return(
		<span>{title}</span>
	)
}

/**
 * 登录组件
 */
class Login extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	loginType: "accountType",
	    	codeMsg: "获取验证码",
	    	codeStatus: false,
	    }
	}
	
	//切换登录方式
	callback = (key) => {
  		this.setState({
  			loginType: key
  		})
	}
	
	//发送验证码
	sendCode = () => {
		const { loginType } = this.state;
		const fieldsValue = this.props.form.getFieldsValue();
		if(loginType == "phoneType"){
			var phone = fieldsValue.loginPhone;
			var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
			if(!phone || phone == ""){
	  			Toast.info("请填写手机号码", 1);
	  			return;
	  		}
			if(!reg.test(phone)){
				Toast.info("手机号码格式不正确", 1);
	  			return;
			}
			//禁用按钮
			this.setState({
	  			codeStatus: true
	  		})
			//启动定时器
			this.openTimer();
			//发送短信
			userApi.sendPhoneCode({
				phone: phone
			}).then(result => {
				if(result.code == 20000){
					Toast.info("发送成功,请注意查收");
				}else{
					Toast.info("发送失败,请稍后重试");
				}
			})
		}
		
	}
	
	//定时器
	openTimer = () => {
		const { codeVal } = this.state;
		var num = 30;
		this.setState({
			codeMsg: num+"s后重新发送"
		})
 		this.timer = setInterval(() => {
 			--num;
 			this.setState({
				codeMsg: num+"s后重新发送"
			})
 			if(num == 0){
 				this.setState({
		  			codeMsg: "获取验证码",
		  			codeStatus: false
		  		})
 				clearInterval(this.timer);
 			}
 		},1000)
	}
	
	//登录
	login = () => {
		const { loginType } = this.state;
		const fieldsValue = this.props.form.getFieldsValue();
		var loginAccount = fieldsValue.loginAccount;
		var loginPassword = fieldsValue.loginPassword;
		var loginPhone = fieldsValue.loginPhone;
		var loginCode = fieldsValue.loginCode;
		
		if(loginType == "accountType"){
			if(!loginAccount || loginAccount == ""){
	  			Toast.info("请填写用户名", 1);
	  			return;
	  		}
			if(!loginPassword || loginPassword == ""){
	  			Toast.info("请填写密码", 1);
	  			return;
	  		}
			Toast.loading("登录中", 3);
			userApi.loginByAccount({
				loginAccount: loginAccount,
				loginPassword: loginPassword
			}).then(result => {
				if(result.code == 20000){
					Toast.info("登录成功", 1.5);
					common.storeSet("token", result.data.token);
					setTimeout(() => {
						window.history.back();
					}, 1000)
				}else{
					Toast.info(result.msg, 1.5);
				}
			})
		}else if(loginType == "phoneType"){
			var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
			if(!loginPhone || loginPhone == ""){
	  			Toast.info("请填写手机号码", 1);
	  			return;
	  		}
			if(!reg.test(loginPhone)){
				Toast.info("手机号码格式不正确", 1);
	  			return;
			}
			if(!loginCode || loginCode == ""){
	  			Toast.info("请填写收到的验证码", 1);
	  			return;
	  		}
			userApi.loginByPhone({
				loginPhone: loginPhone,
				loginCode: loginCode
			}).then(result => {
				if(result.code == 20000){
					Toast.info("登录成功", 1.5);
					common.storeSet("token", result.data.token);
					setTimeout(() => {
						window.history.back();
					}, 1000)
				}else{
					Toast.info(result.msg, 1.5);
				}
			})
		}
	}
	
	//微信登录
	weixinLogin = () => {
		common.weixinLogin();
	}
	
	render(){
		const { getFieldProps, getFieldError } = this.props.form;
		const { codeMsg, codeStatus } = this.state;
		return(
			<div className="login">
				<Header title={"登录"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<Tabs 
					swipeable={false} 
					defaultActiveKey="accountType" 
					onChange={this.callback}> 
			      	<TabPane tab={<TabTitle title="账号密码登录"></TabTitle>} key="accountType">
				        <div className="inputBox loginBox">
				          	<List>
				          		<InputItem {...getFieldProps('loginAccount')}>
				          			<div className="label-img" style={{backgroundImage: 'url("assets/img/name.png")'}}>
						          		<span>账号</span>
						          	</div>
				          		</InputItem>
				          		<InputItem type={"password"} {...getFieldProps('loginPassword')}>
				          			<div className="label-img" style={{backgroundImage: 'url("assets/img/password.png")'}}>
						          		<span>密码</span>
						          	</div>
				          		</InputItem>
				          	</List>
				        </div>
			      	</TabPane>
			      	<TabPane tab={<TabTitle title="短信验证码登录"></TabTitle>} key="phoneType">
				        <div className="inputBox loginBox phoneBox">
				          	<List>
				          		<InputItem {...getFieldProps('loginPhone')}>
				          			<div className="label-img" style={{backgroundImage: 'url("assets/img/phone.png")'}}>
						          		<span>手机号</span>
						          	</div>
						          	<div className="verifyCode">
						          		<Button disabled={codeStatus} type='primary' onClick={this.sendCode.bind(this)}>{codeMsg}</Button>
					          		</div>
				          		</InputItem>
				          		<InputItem {...getFieldProps('loginCode')}>
				          			<div className="label-img" style={{backgroundImage: 'url("assets/img/code.png")'}}>
						          		<span>验证码</span>
						          	</div>
				          		</InputItem>
				          	</List>
				        </div>
			      	</TabPane>
			    </Tabs>
			    <List className="inputBox">
			    	<Item>
			          	<Button type='primary' onClick={this.login.bind(this)}>登录</Button>
			        </Item>
			    </List>
			    <div className="otherLogin">
			    	<div className="lineBox">
					   	<span className="line"></span>
					   	<span className="text">&nbsp;&nbsp;其他登录方式&nbsp;&nbsp;</span>
					   	<span className="line"></span>
					</div>
					<div className="other">
						<img src="assets/img/qq.png"/>
						<img src="assets/img/weixin.png" onClick={this.weixinLogin.bind(this)}/>
					</div>
			    </div>
			</div>
		)
	}
}

export default withRouter(
  createForm()(Login)
);