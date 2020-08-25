import React, { Component } from 'react';
import './cartBottom.less';

/**
 * 购物车子组件:底部下单
 */
class CartBottom extends Component{
	
	//订单页面
	gotoOrder = () => {
		this.props.gotoOrder();
	}
	
	//全选
	allselectMethod = () => {
		const { allselect } = this.props;
		this.props.allselectMethod(!allselect);
	}
	
	render(){
		const { allselect, totalPrice } = this.props;
		var statusImg = allselect ? "assets/img/select.png" : "assets/img/unselect.png";
		return(
			<div className="cart_bottom list_item">
				<div className="left" onClick={this.allselectMethod.bind(this)}>
					<img src={statusImg}/>
				</div>
				<div className="content" onClick={this.allselectMethod.bind(this)}>
					全选
				</div>
				<div className="right">
					<span className="totalPrice">¥{totalPrice}</span>
					<div className="btn" onClick={this.gotoOrder.bind(this)}>
						<span>去结算</span>
					</div>
				</div>
			</div>
		)
	}
}

export default CartBottom;