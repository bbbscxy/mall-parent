import React, { Component } from 'react';
import './orderGoodsItem.less';

/**
 * 购物车子组件:购物车子项
 * @param {data} 数据
 * @param {data.goodsImage} 商品图片
 * @param {data.goodsName} 	商品名称
 * @param {data.goodsTitle} 商品描述
 * @param {data.goodsPrice} 商品价格
 */
class OrderGoodsItem extends Component{
	render(){
		const { data } = this.props;
		return(
			<div className="orderGoodsItemBox">
				<div className="orderGoodsItem list_item">
					<div className="content">
						<div className="list_item_img">
							<img src={data.goodsImage}/>
						</div>
						<div className="list_item_info">
							<span className="name">{data.goodsName}</span>
							<div className="list_item_info_other">
								<span className="price">¥{data.goodsPrice}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default OrderGoodsItem;