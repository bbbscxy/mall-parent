import React, { Component } from 'react';
import './classifyContent.less';

import GoodsItem from 'common/components/GoodsItem.js';

/**
 * 分类子组件:分类主体
 */
class ClassifyContent extends Component{
	
	//选择分类
	categoryClick = (item) => {
		this.props.categoryClick(item.id);
	}
	
	render(){
		const { selectedClassifyId, categoryList, categoryGoodsList } = this.props;
		return(
			<div className="classifyContent">
				<ul>
				{
					categoryList && categoryList.length > 0 &&
					categoryList.map((item, index) => {
						var selectClass = "";
						if(item.id == selectedClassifyId){
							selectClass = "active";
						}
						return(
							<li key={index} className={selectClass} onClick={() => this.categoryClick(item)}>
								<span>{item.classifyName}</span>
							</li>
						)
					})
				}
				</ul>
				<div className="content">
					{
						categoryGoodsList && categoryGoodsList.length > 0 &&
						categoryGoodsList.map((item, index) => {
							return(
								<GoodsItem key={index} data={item}></GoodsItem>
							)
						})
					}
				</div>
			</div>
		)
	}
}

export default ClassifyContent;