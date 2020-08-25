import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { List, InputItem, Picker, Button, Toast } from 'antd-mobile';
import { createForm } from 'rc-form';
import './editAddress.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Header from 'common/components/Header.js';

import * as common from 'common/common.js';
import * as addressApi from 'common/api/AddressApi.js';
import * as userApi from 'common/api/UserApi.js';

const district = addressApi.getAreaData();
const Item = List.Item;

/**
 * 地址编辑组件
 * @param {state} 路由参数
 */
class EditAddress extends Component{
	
	constructor(props) {
		super(props);
  	}

  	componentDidMount() {
  		//初始化表单数据
    	this.props.form.setFieldsValue({
      		...this.props.location.state,
      		areaIds: [
        		this.props.location.state.deliveryProvinceId,
        		this.props.location.state.deliveryCityId,
        		this.props.location.state.deliveryAreaId
      		]
    	});
  	}
  	
  	//保存地址
  	onSubmit = () => {
  		const fieldsValue = this.props.form.getFieldsValue();
  		
  		//联系人,联系电话,详细地址
  		const buyerName = fieldsValue.buyerName;
  		const buyerPhone = fieldsValue.buyerPhone;
  		const deliveryStreet = fieldsValue.deliveryStreet;
  		
  		//地址信息
  		const provinceId = fieldsValue.areaIds[0];
	    const cityId = fieldsValue.areaIds[1];
	    const areaId = fieldsValue.areaIds[2];
	
  		if(!buyerName || buyerName == ""){
  			Toast.info("请填写收件人", 1.5);
  			return;
  		}
  		if(!buyerPhone || buyerPhone == ""){
  			Toast.info("请填写联系方式", 1.5);
  			return;
  		}
  		if(!deliveryStreet || deliveryStreet == ""){
  			Toast.info("请填写详细地址", 1.5);
  			return;
  		}
  		if(!provinceId || provinceId == ""){
  			Toast.info("请选择省", 1.5);
  			return;
  		}
  		if(!cityId || cityId == ""){
  			Toast.info("请选择市", 1.5);
  			return;
  		}
  		if(!areaId || areaId == ""){
  			Toast.info("请选择区", 1.5);
  			return;
  		}
  		
  		//修改地址
  		userApi.editUserAddress({
  			addressId: this.props.params.addressId,
  			buyerId: common.userId(),
  			buyerName: buyerName,
  			buyerPhone: buyerPhone,
  			deliveryProvinceId: provinceId ,
  			deliveryCityId: cityId,
  			deliveryAreaId: areaId,
  			deliveryStreet: deliveryStreet
  		}).then(result => {
  			if(result.code == 20000){
  				Toast.info("保存成功", 1);
  				setTimeout(() => {
  					window.history.back();
  				}, 1000);
  			}
  		})
  	}
	
	render(){
		const { getFieldProps, getFieldError } = this.props.form;
		
		return(
			<div className="edit_address">
				<Header title={"编辑地址"}></Header>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<div className="inputBox">
				 	<List>
			          	<InputItem {...getFieldProps('buyerName')}>
				          	<div className="label-img" style={{backgroundImage: 'url("assets/img/name.png")'}}>
				          		<span>联系姓名</span>
				          	</div>
			          	</InputItem>
			          	<InputItem {...getFieldProps('buyerPhone')}>
				          	<div className="label-img" style={{backgroundImage: 'url("assets/img/phone.png")'}}>
				          		<span>手机号码</span>
				          	</div>
			          	</InputItem>
			          	<Picker data={district} title="选择地区" {...getFieldProps('areaIds')}>
			          		<List.Item arrow="horizontal">
			          			<div className="label-img" style={{backgroundImage: 'url("assets/img/address.png")'}}>
					          		<span>地址信息</span>
					          	</div>
			          		</List.Item>
			        	</Picker>
			        	<InputItem {...getFieldProps('deliveryStreet')}>
				          	<div className="label-img" style={{backgroundImage: 'url("assets/img/detail.png")'}}>
				          		<span>详细地址</span>
				          	</div>
			          	</InputItem>
			          	<Item>
				          	<Button onClick={this.onSubmit.bind(this)} type='primary'>保存</Button>
				        </Item>
			        </List>
				</div>
			</div>
		)
	}
}

export default withRouter(
  createForm()(EditAddress)
);