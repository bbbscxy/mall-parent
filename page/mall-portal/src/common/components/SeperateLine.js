import React, { Component } from 'react';

/**
 * 分割线区域
 * @param {lineHeight} 	区域高度
 * @param {bg} 			区域背景颜色,默认白色	
 */
class SperateLine extends Component{
	render(){
		const { lineHeight, bg } = this.props;
		return(
			<div style={{clear: "both",height: lineHeight, background: bg ? bg : '#fff'}}></div>
		)
	}
}

export default SperateLine;