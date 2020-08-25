import React, { Component } from 'react';
import { SearchBar } from 'antd-mobile';
import './classifyHeader.less';

/**
 * 分类子组件:头部搜索
 */
class ClassifyHeader extends Component{
	render(){
		return(
			<div className="classifyHeader">
				<div className="top">
					<div className="content">
						<SearchBar 
						 	placeholder="搜索商品" 
							cancelText={"关闭"} 
						/>
					</div>
					<div className="right">
						<span>搜索</span>
					</div>
				</div>
			</div>
		)
	}
}

export default ClassifyHeader;