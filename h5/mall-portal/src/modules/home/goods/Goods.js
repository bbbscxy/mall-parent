import React, { Component } from 'react';
import { WingBlank, WhiteSpace, Toast } from 'antd-mobile';
import * as common from 'common/common.js';
import './goods.less';

import SeperateLine from 'common/components/SeperateLine.js';

import GoodsTop from "./sub/GoodsTop.js";
import GoodsTitle from "./sub/GoodsTitle.js";
import GoodsInfoItem from "./sub/GoodsInfoItem.js";
import GoodsHotComments from "./sub/GoodsHotComments.js";
import GoodsBody from "./sub/GoodsBody.js";
import GoodsBuy from "./sub/GoodsBuy.js";

import * as goodsApi from 'common/api/GoodsApi.js';
import * as userApi from 'common/api/UserApi.js';
import * as cartApi from 'common/api/CartApi.js';

/**
 * 商品详情组件
 * @param {goodsImages} 		商品轮播图数据
 * @param {goodsInfo} 			商品描述信息
 * @param {goodsHotComments} 	商品热门评论
 */
class Goods extends Component{

	constructor(props) {
	    super(props);
	    this.state = {
	    	goodsImages: [],
	    	goodsInfo: {}
	    }
	}

	componentDidMount(){
		const { goodsId } = this.props.params;
		goodsApi.goodsDetail({
			goodsId: goodsId
		}).then(result => {
			if(result.code == 20000){
				this.setState({
					goodsInfo: result.data,
					goodsImages: ['assets/img/001.jpg','assets/img/001.jpg']
//					goodsImages: result.data.goodsImage.split(",")
				})
			}
		})
	}
	
	//加入购物车
	addCart = () => {
		Toast.loading("", 3);
		const { goodsInfo } = this.state;
		cartApi.addCart({
			goodsId: goodsInfo.id,
			goodsNum: 1
		}).then(result => {
			Toast.hide();
			if(result.code == 20000){
				Toast.info("加入成功", 1);
			}else if(result.code == 800){
				common.gotoLogin();
			}
		})
	}

	//订单页面
	gotoOrder = () => {
		Toast.loading("", 3);
		const { goodsInfo } = this.state;
		//1.获取默认地址
		//2.添加购物车
		userApi.userDefaultAddress({
			userId: common.userId()
		}).then(result => {
			if(result.code == 20000){
				if(result.data){
					var addressId = "-1";
					if(result.data.length > 0){
						addressId = result.data[0].addressId
					}
					cartApi.addCart({
						userId: common.userId(),
						goodsId: goodsInfo.goodsId,
						goodsNum: 1
					}).then(result => {
						if(result.code == 20000){
							Toast.hide();
							common.gotoOrder({
								userId: common.userId(),
								cartIds: result.data.cartId,
								addressId: addressId
							})
						}
					})
				}
			}else if(result.code == 800){
				common.gotoLogin();
			}
		})
	}
	
	//锚点滚动
	scrollToType = (name) => {
	    if (name) {
	        let nameElement = document.getElementById(name);
	        if(nameElement) { nameElement.scrollIntoView(); }
	    }
  	}

	render(){
		const { goodsImages, goodsInfo, goodsHotComments } = this.state;
		return(
			<div className="container">
				<GoodsTop scrollToType={this.scrollToType.bind(this)}></GoodsTop>
				<GoodsTitle goodsImages={goodsImages} goodsInfo={goodsInfo}></GoodsTitle>
				<GoodsInfoItem data={{name: "送至", desc: "北京朝阳区三环到四环之间"}}></GoodsInfoItem>
				<GoodsInfoItem data={{name: "运费", desc: "在线支付免运费"}}></GoodsInfoItem>
				<SeperateLine lineHeight={"0.2rem"} bg={"#e8e8ed"}></SeperateLine>
				{/*<GoodsHotComments></GoodsHotComments>
				<SeperateLine lineHeight={"0.2rem"} bg={"#e8e8ed"}></SeperateLine>*/}
				<GoodsBody></GoodsBody>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<GoodsBuy addCart={this.addCart.bind(this)} gotoOrder={this.gotoOrder.bind(this)}></GoodsBuy>
	      	</div>
		)
	}
}

export default Goods;