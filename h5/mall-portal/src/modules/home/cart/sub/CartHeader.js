import React, { Component } from 'react';
import './cartHeader.less';

/**
 * 购物车子组件:头部显示
 */
class CartHeader extends Component{
	
	//返回
	back = () => {
		window.history.back();
	}
	
	//删除
	del = () => {
		this.props.del();
	}
	
	render(){
		return(
			<div className="cartHeader top_container">
				<div className="top">
					<div className="left" onClick={this.back.bind(this)}>
						<img src="assets/img/left.png"/>
					</div>
					<div className="content">
						购物车
					</div>
					<div className="right" onClick={this.del.bind(this)}>
						<img src="assets/img/del.png"/>
					</div>
				</div>
			</div>
		)
	}
}

export default CartHeader;