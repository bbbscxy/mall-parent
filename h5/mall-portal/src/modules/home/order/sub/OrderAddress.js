import React, { Component } from 'react';
import { hashHistory } from 'react-router';
import './OrderAddress.less';

/**
 * 订单预览子组件:地址信息
 * @param {data} 数据
 * @param {data.userPhone} 		用户联系方式
 * @param {data.userAddress} 	用户地址
 */
class OrderAddress extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    }
	}
	
	//地址页面
	gotoAddressList = (addressId) => {
		hashHistory.push("/address/"+addressId);
	}
	
	//添加地址
	addAddress = () => {
		hashHistory.push({
			pathname: "/editAddress/-1",
			state: {
				deliveryProvinceId: "42",
				deliveryCityId: "4201",
				deliveryAreaId: "420115",
				deliveryStreet: "",
				buyerName: "",
				buyerPhone: "",
			}
		});
	}
	
	render(){
		const { data } = this.props;
		console.log(data)
		return(
			<div className="order_address_container">
			{
				data && !data.addressId &&
				<div className="blank_address" onClick={this.addAddress.bind(this)}>
					<span>请添加地址信息</span>
					<img src="assets/img/add.png"/>
				</div>
			}
			{
				data && data.addressId &&
				<div className="order_address" onClick={() => this.gotoAddressList(data.addressId)}>
					<div className="order_address_title">
						{data.buyerName} {data.buyerPhone}
					</div>
					<div className="order_address_info">
					  	{data.addressInfo}{data.deliveryStreet}
					</div>
				</div>
			}
			{
				data && data.addressId &&
				<div className="order_address_edit" onClick={() => this.gotoAddressList(data.addressId)}>
					<img src="assets/img/right.png"/>
				</div>
			}
			</div>
		)
	}
}

export default OrderAddress;