import React, { Component } from 'react';
import './userRecommend.less';

import GoodsItem from 'common/components/GoodsItem.js';

/**
 * 用户子组件:推荐商品
 * @param {goodsList} 推荐商品列表数据
 */
class UserRecommend extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	goodsList: [
	    		{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		},{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		},
	    		{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "Apple iPhone 6s Plus (A1699) 32G 玫瑰金色 移动联通电信4G手机",
	    			goodsPrice: "2999.00",
	    			goodsImage: "assets/img/goods_list_01.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		},
	    		{
	    			goodsName: "美的（Midea） 京东微联App智能微波炉 X3-L239C 23L 微蒸烤一体",
	    			goodsPrice: "399.00",
	    			goodsImage: "assets/img/goods_list_02.jpg"
	    		}
	    	]
	    }
	}
	
	render(){
		return(
			<div className="userRecommend">
				<div className="userRecommendTitle">
					<span>为你推荐</span>
				</div>
				<div className="userRecommendList">
					{
						this.state.goodsList.map((item, index) => {
							return(
								<GoodsItem key={index} data={item}></GoodsItem>
							)
						})
					}
				</div>
			</div>
		)
	}
}

export default UserRecommend;