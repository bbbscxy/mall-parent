import React, { Component } from 'react';
import './userToolsList.less';

import * as common from 'common/common.js';

/**
 * 用户子组件:工具栏操作
 */
class UserToolsList extends Component{
	
	//订单页面
	gotoUserOrderList = (key) => {
		common.gotoUserOrderList();
	}
	
	//账户页面
	gotoUserCash = () => {
		common.gotoUserCash();
	}
	
	render(){
		return(
			<div className="userToolsList">
				<ul className="ver_list_item">
					<li onClick={() => this.gotoUserOrderList(10)}>
						<img src="assets/img/order_nopay.png"/>
						<span>待付款</span>
					</li>
					<li onClick={() => this.gotoUserOrderList(30)}>
						<img src="assets/img/order_transport.png"/>
						<span>待收货</span>
					</li>
					<li onClick={() => this.gotoUserOrderList(-1)}>
						<img src="assets/img/order_return.png"/>
						<span>退换/售后</span>
					</li>
					<li onClick={() => this.gotoUserOrderList(100)}>
						<img src="assets/img/order_all.png"/>
						<span>全部订单</span>
					</li>
				</ul>
				<ul className="ver_list_item">
					<li>
						<span className="num">0</span>
						<span>积分</span>
					</li>
					{/*<li onClick={this.gotoUserCash}>
						<span className="num">100.00</span>
						<span>余额</span>
					</li>*/}
					<li>
						<span className="num">0张</span>
						<span>优惠券</span>
					</li>
					<li onClick={this.gotoUserCash}>
						<img src="assets/img/account.png"/>
						<span>全部资产</span>
					</li>
				</ul>
			</div>
		)
	}
}

export default UserToolsList;