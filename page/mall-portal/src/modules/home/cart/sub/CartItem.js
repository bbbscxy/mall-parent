import React, { Component } from 'react';
import { Stepper, Toast } from 'antd-mobile';
import './cartItem.less';

import * as common from 'common/common.js';
import * as cartApi from 'common/api/CartApi.js';

/**
 * 购物车子组件:购物车子项
 * @param {data} 数据
 * @param {data.goodsImage} 商品图片
 * @param {data.goodsName} 	商品名称
 * @param {data.goodsTitle} 商品描述
 * @param {data.goodsPriceStr} 商品价格
 */
class CartItem extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	      	val: this.props.data.goodsNum,
	    };
  	}
	
	//数值改变
	onChange = (val) => {
		Toast.loading("", 3);
		const { data, cartIds } = this.props;
		//修改购物车数量
		cartApi.addCart({
			goodsId: data.goodsId,
			goodsNum: val,
		}).then(result => {
			Toast.hide();
			if(result.code == 20000){
				this.setState({ val });
				this.props.select(true, data.id);
				this.props.refreshCartList();
			}
		})
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
	
	//选中
	select = () => {
		const { data, cartIds } = this.props;
		if(this.isInArray(cartIds, data.id)){
			this.props.select(false, data.id);
		}else{
			this.props.select(true, data.id);
		}
	}
	
	render(){
		const { val } = this.state;
		const { data, cartIds } = this.props;
		var statusImg = "assets/img/unselect.png";
		if(this.isInArray(cartIds, data.id)){
			statusImg = "assets/img/select.png";
		}
		return(
			<div className="cartItemBox">
				<div className="cartItem list_item">
					<div className="left" onClick={this.select.bind(this)}>
						<img src={statusImg}/>
					</div>
					<div className="content">
						<div className="list_item_img">
							<img src='assets/img/001.jpg'/>
						</div>
						<div className="list_item_info">
							<span className="name">{data.goodsName}</span>
							<div className="list_item_info_other">
								<span className="price">¥{data.goodsPriceStr}</span>
								<Stepper
					              showNumber
					              max={99}
					              min={1}
					              value={val}
					              useTouch={false}
					              onChange={this.onChange}
					            />
							</div>
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default CartItem;