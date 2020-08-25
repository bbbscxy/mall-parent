import React, { Component } from 'react';
import * as common from 'common/common.js';
import './app.less';
import './init.less';

/**
 * 根路由组件
 */
class App extends Component{

	constructor(props) {
	    super(props);
	    this.state = {
	    }
  	}

	render(){
		return(
            <div style={{position:"absolute", width: "100%"}}>
				{this.props.children}
			</div>
		)
	}
}

export default App;