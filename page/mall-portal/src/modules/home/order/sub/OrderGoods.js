import React, { Component } from 'react';
import './orderGoods.less';

/**
 * 订单预览子组件:订单商品列表
 * @param {data} 数据
 * @param {data[0].goodsImage} 	商品图片
 * @param {data[0].goodsName} 	商品名称
 * @param {data[0].goodsPriceStr} 	商品价格
 * @param {data[0].goodsNum} 	商品数量
 */
class OrderGoods extends Component{
	render(){
		const { data } = this.props;
		return(
			<div className="order_goods_container">
				<div className="order_goods">
				{
					data.map((item, index) => {
						return(
							<div key={index} className="order_goods_item">
								<div className="left">
									<img src='assets/img/001.jpg'/>
								</div>
								<div className="right">
									<div className="top">
										{item.goodsName}
									</div>
									<div className="bottom">
										<span className="price">￥{item.goodsPriceStr}</span>
										<span className="num">x&nbsp;{item.goodsNum}</span>
									</div>
								</div>
							</div>
						)
					})
				}
				</div>
			</div>
		)
	}
}

export default OrderGoods;