import React, { Component } from 'react';
import 'common/containers/error.less';

/**
 * 页面路由失败组件
 */
class Error extends Component{
	render(){
		return(
			<div className="error">
				No Page Found
			</div>
		)
	}
}

export default Error;