import React, { Component } from 'react';
import { hashHistory } from 'react-router';
import './addressList.less';

/**
 * 地址列表子组件
 */
class AddressList extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	addressSelect: this.props.addressId,
	    }
	}
	
	//编辑地址
	editAddress = (item) => {
		hashHistory.push({
			pathname: "/editAddress/"+item.addressId,
			state: item
		});
	}
	
	//选中地址
	selectAddress = (item) => {
		this.setState({
			addressSelect: item.addressId
		})
		
		this.props.selectAddress(item.addressId);
	}
	
	render(){
		return(
			<div className="address_list">
			{
				this.props.addressList &&
				this.props.addressList.length > 0 &&
				this.props.addressList.map((item, index) => {
					//设置地址选中状态
					var selectClassImg = "assets/img/unselect.png";
					if(this.state.addressSelect == "-1" && index == 0){
						selectClassImg = "assets/img/select.png";
					}else if(item.addressId == this.state.addressSelect){
						selectClassImg = "assets/img/select.png";
					}
					return(
						<div key={index} className="list_item address_item">
							<div className="left" onClick={() => this.selectAddress(item)}>
								<img src={selectClassImg}/>
							</div>
							<div className="content" onClick={() => this.selectAddress(item)}>
								<div className="order_address_container">
									<div className="order_address">
										<div className="order_address_title">
											{item.buyerName} {item.buyerPhone}
										</div>
										<div className="order_address_info">
										  	{item.deliveryProvince}{item.deliveryCity}{item.deliveryArea}{item.deliveryStreet}
										</div>
									</div>
								</div>
							</div>
							<div className="right" onClick={() => this.editAddress(item)}>
								<img src="assets/img/edit.png"/>
							</div>
						</div>
					)
				})
			}
			</div>
		)
	}
}

export default AddressList;