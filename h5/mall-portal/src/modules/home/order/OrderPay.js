import React, { Component } from 'react';
import { List, Toast, Modal } from 'antd-mobile';
import './orderPay.less';

import * as common from 'common/common.js';

import * as orderApi from 'common/api/OrderApi.js';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

const Item = List.Item;

/**
 * 页面路由失败组件
 */
class OrderPay extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	orderDetail: {},
	    }
	}
	
	componentDidMount(){
		const { orderId } = this.props.params;
		orderApi.orderDetail({
			orderId: orderId,
		}).then(result => {
			if(result.code == 20000){
				this.setState({
					orderDetail: result.data
				})
			}
		})
	}
	
	render(){
		const { orderDetail } = this.state;
		return(
			<div className="orderPay">
				<Header title={"支付"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<List className="my_list">
		        	<Item extra={orderDetail.orderTotalPriceStr+" 元"}>订单金额</Item>
		      	</List>
		      	<SeperateLine lineHeight={"0.1rem"} bg={"#eee"}></SeperateLine>
		      	<List className="my_list">
		      		<Item extra={<div className="pay_type"><span>0.00 元</span><img src="assets/img/right.png"/></div>}>
		      			<img className="pay_img" src="assets/img/wallet.png"/>
		        		<span>余额</span>
		        	</Item>
		        	<Item extra={<div className="pay_type"><img src="assets/img/right.png"/></div>}>
		        		<img className="pay_img" src="assets/img/weixin_bg.png"/>
		        		<span>微信</span>
		        	</Item>
		        	<Item extra={<div className="pay_type"><img src="assets/img/right.png"/></div>}>
		        		<img className="pay_img" src="assets/img/zfb.png"/>
		        		<span>支付宝</span>
		        	</Item>
		      	</List>
			</div>
		)
	}
}

export default OrderPay;