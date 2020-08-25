import React, { Component } from 'react';
import { Toast } from 'antd-mobile';
import './address.less';
import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';
import Blank from 'common/components/Blank.js';

import AddressList from "./sub/AddressList.js";

import * as common from 'common/common.js';
import * as userApi from 'common/api/UserApi.js';

/**
 * 地址管理组件
 * @param {addressId} 	路由参数:选中的地址ID
 * @param {addressList} 地址列表数据
 */
class Address extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	addressId: this.props.params.addressId,
	    	addressList: []
	    }
	}
	
	componentDidMount(){
		Toast.loading("", 3);
		//获取用户地址列表
		userApi.userAddressList({
			userId: common.userId()
		}).then(result => {
			Toast.hide();
			if(result.code == 20000){
				this.setState({
					addressList: result.data
				})
			}
		})
	}
	
	//选中地址
	selectAddress = (addressId) => {
		window.history.back();
	}
	
	render(){
		return(
			<div className="address">
				<Header title={"地址"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<AddressList addressId={this.state.addressId} addressList={this.state.addressList} selectAddress={this.selectAddress.bind(this)}></AddressList>
			</div>
		)
	}
}

export default Address;