import React, { Component } from 'react';
import { hashHistory } from 'react-router';
import { Popover } from 'antd-mobile';
import './goodsTop.less';

const Item = Popover.Item;

/**
 *商品详情子组件:头部导航
 * @param {goodsTitleActive} 	选中状态
 * @param {goodsTitle} 			导航列表数据
 */
class GoodsTop extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	goodsTitleActive: 0,
	    	goodsTitle: [
	    		{
	    			name: "商品",
	    			type: "goodsTitle"
	    		},
//	    		{
//	    			name: "评价",
//	    			type: "goodsHotComments"
//	    		},
	    		{
	    			name: "详情",
	    			type: "goodsBody"
	    		}
	    	],
	    }
	}
	
	//锚点滚动
	scrollToType = (name, index) => {
		this.setState({
			goodsTitleActive: index
		})
		this.props.scrollToType(name);
	}
	
	//返回
	back = () => {
		window.history.back();
	}
	
	render(){
		return(
			<div className="goods_top_container">
				<div className="goods_top">
					<div className="left" onClick={this.back.bind(this)}>
						<img src="assets/img/left.png"/>
					</div>
					<div className="content">
					{
						this.state.goodsTitle.map((item, index) => {
							var goodsTitleClass = "tools_item";
							if(index == this.state.goodsTitleActive){
								goodsTitleClass = "tools_item active";
							}
							return(
								<div key={index} className={goodsTitleClass} onClick={() => this.scrollToType(item.type, index)}>
									{item.name}
								</div>	
							)
						})
					}
					</div>
					<div className="right"></div>
				</div>
			</div>
		)
	}
}

export default GoodsTop;