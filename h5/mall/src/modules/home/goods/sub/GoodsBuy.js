import React, { Component } from 'react';
import * as common from 'common/common.js';
import './goodsBuy.less';

class GoodsBuy extends Component{
	
	//加入购物车
	addCart = () => {
		this.props.addCart();
	}
	
	//订单页面
	gotoOrder = () => {
		this.props.gotoOrder();
	}
	
	//我的页面
	gotoUser = () => {
		common.gotoUser();
	}
	
	//购物车页面
	gotoCart = () => {
		common.gotoCart();
	}
	
	render(){
		return(
			<div className="goods_buy_container">
				<div className="left">
					<div className="goods_tools" onClick={this.gotoUser.bind(this)}>
						<img src="assets/img/menu_my.png"/>
						<span>我的</span>
					</div>
					<div className="goods_tools" onClick={this.gotoCart.bind(this)}>
						<img src="assets/img/menu_cart.png"/>
						<span>购物车</span>
					</div>
				</div>
				<div className="right">
					<span className="addCart" onClick={this.addCart.bind(this)}>加入购物车</span>
					<span className="buy" onClick={this.gotoOrder.bind(this)}>立即购买</span>
				</div>
			</div>
		)
	}
}

export default GoodsBuy;