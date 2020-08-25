import React, { Component } from 'react';
import { Router, Route, IndexRoute } from 'react-router';
import App from 'common/containers/App.js';
import Error from 'common/containers/Error.js';
import 'common/containers/app.less';

import Main from '../main/Main.js';
import Classify from '../classify/Classify.js';
import Cart from '../cart/Cart.js';

import User from '../user/User.js';
import UserCash from '../user/UserCash.js';
import UserOrderList from '../user/UserOrderList.js';
import UserSetting from '../user/UserSetting.js';

import Login from '../login/Login.js';
import Register from '../login/Register.js';

import Goods from '../goods/Goods.js';
import Order from '../order/Order.js';
import OrderPay from '../order/OrderPay.js';
import Address from '../address/Address.js';
import EditAddress from '../address/EditAddress.js';

/**
 * 路由
 */
class RouteMap extends Component{

	onUpdate = () => {
		window.scrollTo(0,0);
	}

	render(){
		return(
			<Router history={this.props.history} onUpdate={this.onUpdate}>
				<Route path="/" component={App}>
					<IndexRoute component={Main} title="主页" />
					<Route path="/main" component={Main}/>
					<Route path="/classify" component={Classify}/>
					<Route path="/cart" component={Cart}/>
					
					<Route path="/user" component={User}/>
					<Route path="/userCash" component={UserCash}/>
					<Route path="/userOrderList" component={UserOrderList}/>
					<Route path="/userSetting" component={UserSetting}/>
					
					<Route path="/login" component={Login}/>
					<Route path="/register" component={Register}/>
					
					<Route path="/goods/:goodsId" component={Goods}/>
					<Route path="/order/:cartIds/:addressId" component={Order}/>
					<Route path="/orderPay/:orderId" component={OrderPay}/>
					<Route path="/address/:addressId" component={Address}/>
					<Route path="/editAddress/:addressId" component={EditAddress}/>
					<Route path="*" component={Error}/>
				</Route>
			</Router>
		)
	}
}

export default RouteMap;