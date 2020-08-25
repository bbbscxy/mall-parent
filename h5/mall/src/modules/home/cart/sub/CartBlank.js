import React, { Component } from 'react';
import './cartBlank.less';

/**
 * 购物车空
 */
class CartBlank extends Component{
	render(){
		return(
			<div className="cartBlank">
				<img src="assets/img/cart_empty.png"/>
				<span>购物车空空如也!</span>
			</div>
		)
	}
}

export default CartBlank;