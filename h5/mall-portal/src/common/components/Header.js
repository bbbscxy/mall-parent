import React, { Component } from 'react';
import './header.less';

/**
 * 页面头部公共组件
 * @param {title} 显示标题内容
 */
class Header extends Component{
	
	//返回上个页面
	back = () => {
		window.history.back();
	}
	
	render(){
		const { title } = this.props;
		return(
			<div className="top_container">
				<div className="top">
					<div className="left" onClick={this.back.bind(this)}>
						<img src="assets/img/left.png"/>
					</div>
					<div className="content">
						{title}
					</div>
					<div className="right"></div>
				</div>
			</div>
		)
	}
}

export default Header;