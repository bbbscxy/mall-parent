import React, { Component } from 'react';
import './userCashRecharge.less';

class UserCashRecharge extends Component{

	constructor(props) {
	    super(props);
	    this.state = {
	    	payType: 0
	    }
	}

	//前往支付
	gotoPay = () => {
		this.props.closeRecharge();
	}

	//取消支付
	closeRecharge = () => {
		this.props.closeRecharge();
	}

	//选择支付方式
	selectPayType = (index) => {
		this.setState({
			payType: index
		})
	}

	render(){
		return(
			<div className="userCashRecharge">
				<div className="userCashRechargeTitle">
					<img src="assets/img/close.png" onClick={this.closeRecharge}/>
					<span>充值</span>
				</div>
				<h1>1<em>元</em></h1>
				{
					this.state.payType == 0
					?
					<ul>
						<li className="active">
							<img src="assets/img/pay_zfb.png"/>
							<span>支付宝</span>
						</li>
						<li onClick={() => this.selectPayType(1)}>
							<img src="assets/img/pay_weixin.png"/>
							<span>微信</span>
						</li>
					</ul>
					:
					<ul>
						<li onClick={() => this.selectPayType(0)}>
							<img src="assets/img/pay_zfb.png"/>
							<span>支付宝</span>
						</li>
						<li className="active">
							<img src="assets/img/pay_weixin.png"/>
							<span>微信</span>
						</li>
					</ul>
				}
				
				<div className="userCashRechargeNow" onClick={this.gotoPay}>
					立即支付
				</div>
			</div>
		)
	}
}

export default UserCashRecharge;