import React, { Component } from 'react';
import * as common from "common/common.js";
import './goodsItem.less';

/**
 * 单个商品显示样式
 * @param {data} 单个商品数据
 * @param {data.goodsImage} 商品图片
 * @param {data.goodsName} 	商品名称
 * @param {data.goodsPriceStr} 商品价格
 */
class GoodsItem extends Component{
	
	//查看商品详情
	gotoGoodsDetail = (data) => {
		common.gotoGoodsDetail(data.id);
	}
	
	render(){
		const { data } = this.props;
		return(
			<div className="goodsItemBox">
				<div className="goodsItem">
				    <div className="wrap">
						<img src='assets/img/001.jpg' onClick={() => this.gotoGoodsDetail(data)}/>
				    </div>
					<span className="title">{data.goodsName}</span>
					<span className="price">¥{data.goodsPriceStr}</span>
				</div>
			</div>
		)
	}
}

export default GoodsItem;