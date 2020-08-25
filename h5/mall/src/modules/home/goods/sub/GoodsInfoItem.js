import React, { Component } from 'react';
import './goodsInfoItem.less';

class GoodsInfoItem extends Component{
	render(){
		const { data } = this.props;
		
		return(
			<div className="list_item">
				<div className="left">
					<span>{data.name}</span>
				</div>
				<div className="content">
					{data.desc}
				</div>
				<div className="right">
					<img src="assets/img/dot3.png"/>
				</div>
			</div>
		)
	}
}

export default GoodsInfoItem;