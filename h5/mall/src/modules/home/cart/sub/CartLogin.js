import React, { Component } from 'react';
import { List, Button, Toast } from 'antd-mobile';
import './cartLogin.less';

import * as common from 'common/common.js';

const Item = List.Item;

/**
 * 购物车登录
 */
class CartLogin extends Component{
	
	//登录
	login = () => {
		common.gotoLogin();
	}
	
	render(){
		return(
			<div className="cartLogin">
				<img src="assets/img/cart_empty.png"/>
				<div className="login">
					<List>
				    	<Item>
				          	<Button type='primary' onClick={this.login.bind(this)}>登录</Button>
				        </Item>
				    </List>
			    </div>
			</div>
		)
	}
}

export default CartLogin;