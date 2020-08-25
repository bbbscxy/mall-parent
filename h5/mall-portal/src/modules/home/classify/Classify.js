import React, { Component } from 'react';
import { Toast } from 'antd-mobile';
import './classify.less';

import Header from 'common/components/Header.js';
import SeperateLine from 'common/components/SeperateLine.js';
import Bottom from 'common/containers/Bottom.js';

import ClassifyHeader from './sub/ClassifyHeader.js';
import ClassifyContent from './sub/ClassifyContent.js';

import * as classifyApi from 'common/api/ClassifyApi.js';

/**
 * 分类组件
 * @param {categoryList} 		分类列表数据
 * @param {categoryGoodsList} 	选中分类下的商品列表数据
 */
class Classify extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	selectedClassifyId: "",
	    	categoryList: [],
	    	categoryGoodsList: []
	    }
	}
	
	componentDidMount(){
		//获取所有分类
		classifyApi.classifyList().then(result => {
			if(result.code == 20000){
				this.setState({
					categoryList: result.data
				})
				//初始化分类商品列表
				this.refreshCategoryGoodsList(result.data[0].id);
			}
		})
	}
	
	//选择分类
	categoryClick = (classifyId) => {
		this.refreshCategoryGoodsList(classifyId);
	}
	
	//刷新分类商品列表
	refreshCategoryGoodsList = (classifyId) => {
		Toast.loading("", 3);
		classifyApi.goodsList({
			page: 0,
			limit: 100,
			classifyId: classifyId
		}).then(result => {
			Toast.hide();
			if(result.code == 20000){
				this.setState({
					selectedClassifyId:classifyId,
					categoryGoodsList: result.data
				})
			}
		})
	}
	
	render(){
		const { selectedClassifyId, categoryList, categoryGoodsList } = this.state;
		return(
			<div className="classifyBox">
				<ClassifyHeader></ClassifyHeader>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<ClassifyContent selectedClassifyId={selectedClassifyId} categoryClick={this.categoryClick.bind(this)} categoryList={categoryList} categoryGoodsList={categoryGoodsList}></ClassifyContent>
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<Bottom selectedTab="classify"></Bottom>
			</div>
		)
	}
}

export default Classify;