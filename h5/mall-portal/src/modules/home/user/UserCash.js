import React, { Component } from 'react';
import { Modal, List, Button, WingBlank, WhiteSpace, Popup } from 'antd-mobile';
import UserCashRecharge from './sub/userCash/userCashRecharge.js';
import './userCash.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

class UserCash extends Component{

	constructor(props) {
	    super(props);
	    this.state = {
      		modal1: false,
	      	modal2: false,
	    };
  	}

  	closeRecharge = () => {
		Popup.hide();
  	}

  	showRecharge = (e) => {
  		// 修复 Android 上点击穿透
	    e.preventDefault(); 
	   	Popup.show(<UserCashRecharge closeRecharge={this.closeRecharge}></UserCashRecharge>, {animationType: 'slide-up'});
 	}

	onClose = key => () => {
	    this.setState({
	      [key]: false,
	    });
  	}

	render(){
		return(
			<div className="container userCash">
				<Header title={"我的账户"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<WingBlank>
					<h1>100.00<em>元</em></h1>
					<h4>余额充值</h4>
					<ul className="cashRecharge">
						<li onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>1<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>1元</span>
								<span className="present">&nbsp;+&nbsp;0元</span>
							</div>
						</li>
						<li className="ml1" onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>5<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>5元</span>
								<span className="present">&nbsp;+&nbsp;1元</span>
							</div>
						</li>
						<li onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>20<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>20元</span>
								<span className="present">&nbsp;+&nbsp;5元</span>
							</div>
						</li>
						<li className="ml1" onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>50<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>50元</span>
								<span className="present">&nbsp;+&nbsp;15元</span>
							</div>
						</li>
						<li onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>100<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>100元</span>
								<span className="present">&nbsp;+&nbsp;40元</span>
							</div>
						</li>
						<li className="ml1" onClick={this.showRecharge}>
							<div className="rechargeNum">
								<span>500<em>元</em></span>
							</div>
							<div className="cashNum">
								<span>500元</span>
								<span className="present">&nbsp;+&nbsp;300元</span>
							</div>
						</li>
					</ul>
				</WingBlank>
			</div>
		)
	}
}

export default UserCash;