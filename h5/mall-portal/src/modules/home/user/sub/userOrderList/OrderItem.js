import React, { Component } from 'react';
import './orderItem.less';

import OrderGoodsItem from "./OrderGoodsItem.js";

/**
 * 用户订单列表子组件
 */
class OrderItem extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {}
	}    
	
	render(){
		const { data } = this.props;
		return(
			<div className="orderItem">
				<div className="order_sn">
					<div>
						<span className="order_sn_text"><img src="assets/img/order.png"/>订单：</span>
						<span className="order_sn_value">&nbsp;{data.orderSn}</span>
					</div>
					<div>
						<span className="order_sn_value">&nbsp;{data.orderStateStr}</span>
					</div>
				</div>
				<div className="order_sn">
					<div>
						<span className="order_sn_text"><img src="assets/img/account.png"/>总价：</span>
						<span className="order_sn_value">&nbsp;¥{data.orderPayPrice}</span>
					</div>
				</div>
				<div className="order_goods">
					{
						data.orderGoodsList.map((item, index) => {
							return(
								<OrderGoodsItem key={index} data={item}></OrderGoodsItem>
							)
						})
					}
				</div>
			</div>
		)
	}
}

export default OrderItem;