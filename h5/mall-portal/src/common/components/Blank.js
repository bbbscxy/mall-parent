import React, { Component } from 'react';
import './blank.less';

/**
 * 内容为空时显示的组件
 * @param {tips} 下方提示文字
 */
class Blank extends Component{
	render(){
		const { tips } = this.props;
		return(
			<div className="blank">
				<img src="assets/img/empty.png"/>
				<span>{tips}</span>
			</div>
		)
	}
}

export default Blank;