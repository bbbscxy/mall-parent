import React, { Component } from 'react';
import * as common from 'common/common.js';
import { Modal, Toast } from 'antd-mobile';
import './cart.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

import CartItem from "./sub/CartItem.js";
import CartHeader from "./sub/CartHeader.js";
import CartBottom from "./sub/CartBottom.js";

import CartBlank from "./sub/CartBlank.js";
import CartLogin from "./sub/CartLogin.js";

import * as cartApi from 'common/api/CartApi.js';
import * as userApi from 'common/api/UserApi.js';

const alert = Modal.alert;

/**
 * 购物车组件
 * @param {cartList} 购物车列表数据
 */
class Cart extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	cartIds: [],
	    	cartList: [],
	    	empty: false,
	    	allselect: false,
	    	totalPrice: 0.00.toFixed(2),
	    	delModal: false
	    }
	}    
	
	componentDidMount(){
		this.refreshCartList();
	}
	
	//刷新购物车列表
	refreshCartList = () => {
		cartApi.cartList().then(result => {
			if(result.code == 20000){
				if(result.data.length == 0){
					this.setState({
						cartList: result.data,
						empty: true
					}, function() {
						this.countPrice();
				   	})
				}else{
					this.setState({
						cartList: result.data,
						empty: false
					}, function() {
						this.countPrice();
				   	})
				}
			}
		})
	}
	
	//订单页面
	gotoOrder = () => {
		const { cartIds, cartList } = this.state;
		if(cartList.length == 0){
			Toast.info("购物车没有商品", 1.5);
			return;
		}
		if(cartIds.length == 0){
			Toast.info("请选择要购买的商品", 1.5);
			return;
		}
		Toast.loading("", 3);
		userApi.userDefaultAddress().then(result => {
			Toast.hide();
			if(result.code == 20000){
				if(result.data){
					var addressId = "-1";
					if(result.data.length > 0){
						addressId = result.data[0].addressId
					}
					common.gotoOrder({
						cartIds: cartIds.join(","),
						addressId: addressId
					})
				}
			}
		})
	}
	
	//数组位置
	indexInArray = (arr,value) => {
	    for(var i = 0; i < arr.length; i++){
	        if(value === arr[i]){
	            return i;
	        }
	    }
	    return -1;
	}
	
	//是否在数组中
	isInArray = (arr,value) => {
	    for(var i = 0; i < arr.length; i++){
	        if(value === arr[i]){
	            return true;
	        }
	    }
	    return false;
	}
	
	//选中购物车
	select = (status, cartId) => {
		var cartIds = this.state.cartIds;
		var cartList = this.state.cartList;
		if(status){
			if(!this.isInArray(cartIds, cartId)){
				cartIds.push(cartId);
			}
		}else{
			cartIds.splice(this.indexInArray(cartIds, cartId), 1);
		}
		if(cartIds.length == cartList.length){
			this.setState({
				cartIds: cartIds,
				allselect: true
			}, function() {
				this.countPrice();
		   	})
		}else{
			this.setState({
				cartIds: cartIds,
				allselect: false
			}, function() {
				this.countPrice();
		   	})
		}
	}
	
	//全选
	allselectMethod = (status) => {
		const { cartList } = this.state;
		var cartIds = [];
		if(status){
			cartList.map((item, index) => {
				cartIds.push(item.id);
			})
			this.setState({
				cartIds: cartIds,
				allselect: true
			}, function() {
				this.countPrice();
		   	})
		}else{
			this.setState({
				cartIds: cartIds,
				allselect: false
			}, function() {
				this.countPrice();
		   	})
		}
	}
	
	//删除
	del = () => {
		this.setState({
	      delModal: true,
	    });
	}
	
	//计算选中购物车的总金额
	countPrice = () => {
		var totalPrice = 0;
		const { cartIds, cartList } = this.state;
		cartList.map((item, index) => {
			if(this.isInArray(cartIds, item.id)){
				totalPrice += parseFloat(item.goodsPrice)*parseFloat(item.goodsNum);
			}
		})
		this.setState({
			totalPrice: totalPrice.toFixed(2)
		})
	}
	
	//确定删除
	sure = () => {
		this.setState({
	      delModal: false,
	    });
		const { cartIds } = this.state;
    	Toast.loading("", 3);
    	cartApi.delCart({
    		cartIds: cartIds.join(",")
    	}).then(result => {
    		
    		if(result.code == 20000){
    			this.refreshCartList();
    			setTimeout(() => {
					this.countPrice();
				}, 100)
    			Toast.info("删除成功", 1.5);
    		}else{
    			Toast.info("删除失败", 1.5);
    		}
    	})
	}
	
	//取消
	cancel = () => {
		this.setState({
	      delModal: false,
	    });
	}
		
	onClose = () => {
	    this.setState({
	      delModal: false,
	    });
  	}
	
	render(){
		const { empty, allselect, cartIds, cartList, totalPrice, delModal } = this.state;
		return(
			<div className="cart">
			<Modal
				className="modal"
	          	title="确定删除?"
	          	transparent
	          	maskClosable={true}
	          	platform="ios"
	          	visible={delModal}
	          	onClose={this.onClose.bind(this)}
	          	footer={[{ text: '确定', onPress: this.sure.bind(this)}, { text: '取消', onPress: this.cancel.bind(this)}]}
	        >
	        </Modal>
				<CartHeader del={this.del.bind(this)}></CartHeader>
				<SeperateLine lineHeight={"1.1rem"}></SeperateLine>
				{
					!empty && cartList.map((item, index) => {
						return(
							<CartItem refreshCartList={this.refreshCartList.bind(this)} select={this.select.bind(this)} cartIds={cartIds} key={index} data={item}></CartItem>
						)
					})
				}
				{
					empty && 
					<CartBlank></CartBlank>
				}
				<SeperateLine lineHeight={"1.1rem"}></SeperateLine>
				<CartBottom totalPrice={totalPrice} allselectMethod={this.allselectMethod.bind(this)} allselect={allselect} gotoOrder={this.gotoOrder.bind(this)}></CartBottom>
			</div>
		)
	}
}

export default Cart;