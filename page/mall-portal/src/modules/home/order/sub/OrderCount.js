import React, { Component } from 'react';
import './orderCount.less';

/**
 * 订单预览子组件:订单金额
 * @param {data} 数据
 * @param {data.goodsTotalPrice} 	商品总额
 * @param {data.orderFreightPrice} 	运费总额
 * @param {data.orderTotalPrice} 	订单总额
 */
class OrderCount extends Component{
	render(){
		const { data } = this.props;
		return(
			<div className="order_count_container">
				<div className="order_count">
					<div className="order_count_item">
						<span>商品金额</span>
						<span>￥{data.goodsTotalPrice}</span>
					</div>
					<div className="order_count_item">
						<span>订单运费</span>
						<span>+￥{data.orderFreightPrice}</span>
					</div>
				</div>
				<div className="order_count_total">
					<span>总金额: </span>
					<span>￥{data.orderTotalPrice}</span>
				</div>
			</div>
		)
	}
}

export default OrderCount;