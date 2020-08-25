import React, { Component } from 'react';
import { Toast } from 'antd-mobile';
import { hashHistory } from 'react-router';
import * as common from 'common/common.js';
import './order.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

import OrderAddress from './sub/OrderAddress.js';
import OrderGoods from './sub/OrderGoods.js';
import OrderCount from './sub/OrderCount.js';

import * as userApi from 'common/api/UserApi.js';
import * as orderApi from 'common/api/OrderApi.js';

/**
 * 订单预览组件
 * @param {address} 	地址信息
 * @param {orderGoods} 	订单商品列表数据
 * @param {orderCount} 	订单金额
 */
class Order extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	addressId: this.props.params.addressId,
	    	cartIds: this.props.params.cartIds,
	    	address: {},
    		orderGoods: [],
    		orderCount: {}
	    }
	}
	
	componentDidMount(){
		const { cartIds, addressId } = this.state;
		Toast.loading("", 3);
		//1.获取地址详情
		if(addressId != -1){
			userApi.userAddressDetail({
				userId: common.userId(),
				addressId: addressId
			}).then(result => {
				if(result.code == 20000){
					this.setState({
						address: result.data
					})
				}
			})
		}else{
			userApi.userDefaultAddress().then(result => {
				if(result.code == 20000 && result.data){
					this.setState({
						address: result.data,
						addressId: result.data.id
					})
				}
			})
		}
		//2.预下单
		orderApi.preOrder({
			cartIds: cartIds,
			addressId: addressId
		}).then(result => {
			Toast.hide();
			console.log(result)
			if(result.code == 20000){
				if(result.data){
					this.setState({
						orderGoods: result.data.cartList,
						orderCount: {
							goodsTotalPrice: result.data.goodsTotalPrice,
			    			orderFreightPrice: result.data.orderFreightPrice,
			    			orderTotalPrice: result.data.orderTotalPrice,
						}
					})
				}else{
					window.history.back();
					Toast.info("去我的菜单查看待付款订单", 2);
				}
			}
		})
	}
	
	//返回
	back = () => {
		window.history.back();
	}
	
	//立即支付
	gotoPay = () => {
		Toast.loading("", 3);
		const { cartIds, addressId } = this.state;
		if(addressId == "-1"){
			Toast.info("请添加地址信息", 1.5);
			return;
		}
		orderApi.createOrder({
			userId: common.userId(),
			cartIds: cartIds,
			addressId: addressId
		}).then(result => {
			Toast.hide();
			if(result.code == 20000){
				Toast.info("前往支付页面中...", 1);
				setTimeout(() => {
					Toast.hide();
					common.gotoOrderPay({
						orderId: result.data.orderId
					})
				}, 1);
				
			}
		})
	}
	
	render(){
		return(
			<div className="order">
				<Header title={"订单"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<OrderAddress data={this.state.address}></OrderAddress>
				<OrderGoods data={this.state.orderGoods}></OrderGoods>
				<SeperateLine lineHeight={"0.2rem"} bg={"#e8e8ed"}></SeperateLine>
				<OrderCount data={this.state.orderCount}></OrderCount>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<div className="order_pay" onClick={this.gotoPay.bind(this)}>
					立即支付
				</div>
			</div>
		)
	}
}

export default Order;