import React, { Component } from 'react';
import './userOrderList.less';

import * as common from 'common/common.js';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';
import Blank from 'common/components/Blank.js';

import OrderItem from './sub/userOrderList/OrderItem.js';

import * as orderApi from 'common/api/OrderApi.js';

/**
 * 用户订单列表组件
 */
class UserOrderList extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	userOrderList: []
	    }
	}    
	
	componentDidMount(){
		orderApi.userOrderList({
			page: 1,
			limit: 100,
			userId: common.userId()
		}).then(result => {
			if(result.code == 20000){
				this.setState({
					userOrderList: result.data
				})
			}else if(result.code == 800){
				window.history.back();
			}
		})
	}
	
	render(){
		const { userOrderList } = this.state;
		return(
			<div className="userOrderList">
				<Header title={"订单列表"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				{
					userOrderList.length == 0 &&
					<Blank tips={"没有任何订单"}></Blank>
				}
				{
					userOrderList && userOrderList.length > 0 && 
					userOrderList.map((item, index) => {
						return(
							<div key={index}>
								<OrderItem data={item}></OrderItem>
								{
									index != (userOrderList.length-1) &&
									<SeperateLine lineHeight={"0.2rem"} bg={"#e8e8ed"}></SeperateLine>
								}
							</div>
						)
					})
				}
			</div>
		)
	}
}

export default UserOrderList;