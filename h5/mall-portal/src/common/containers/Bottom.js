import React, { Component } from 'react';
import { hashHistory } from 'react-router';
import { TabBar } from 'antd-mobile';
import './bottom.less';

/**
 * 菜单栏组件
 * @param {selectedTab} 选中的菜单标识,默认选中第一个
 */
class Bottom extends Component{
	constructor(props) {
	    super(props);
	    this.state = {
	      selectedTab: this.props.selectedTab ? this.props.selectedTab : 'classify',
	    };
  	}
	
	//切换菜单
	changeTab = (tabName) => {
		hashHistory.push(tabName);
	}
	
	render(){
		return(
			<div className="menu_bottom">
				<TabBar tintColor={"#e4393c"} unselectedTintColor={"#888"}>
					{/*<TabBar.Item
	            		icon={"assets/img/menu_main.png"}
	            		selectedIcon={"assets/img/menu_main_selected.png"}
	            		title="主页"
	            		key="main"
	            		selected={this.state.selectedTab === 'main'}
			            onPress={() => this.changeTab("main")}>
	          		</TabBar.Item>*/}
	          		<TabBar.Item
	            		icon={"assets/img/menu_classify.png"}
	            		selectedIcon={"assets/img/menu_classify_selected.png"}
	            		title="分类"
	            		key="classify"
	            		selected={this.state.selectedTab === 'classify'}
	            		onPress={() => this.changeTab("classify")}>
	          		</TabBar.Item>
	          		<TabBar.Item
	            		icon={"assets/img/menu_cart.png"}
	            		selectedIcon={"assets/img/menu_cart_selected.png"}
	            		title="购物车"
	            		key="cart"
	            		selected={this.state.selectedTab === 'cart'}
			            onPress={() => this.changeTab("cart")}>
	          		</TabBar.Item>
	          		<TabBar.Item
	            		icon={"assets/img/menu_my.png"}
	            		selectedIcon={"assets/img/menu_my_selected.png"}
	            		title="我的"
	            		key="user"
	            		selected={this.state.selectedTab === 'user'}
			            onPress={() => this.changeTab("user")}>
	          		</TabBar.Item>
          		</TabBar>
			</div>
		)
	}
}

export default Bottom;