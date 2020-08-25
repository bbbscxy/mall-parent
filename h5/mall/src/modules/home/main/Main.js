import React, { Component } from 'react';
import './main.less';

import SeperateLine from 'common/components/SeperateLine.js';
import Bottom from 'common/containers/Bottom.js';

class Main extends Component{
	render(){
		return(
			<div className="main">
				主页
				<SeperateLine lineHeight={"1.1rem"}></SeperateLine>
				<Bottom selectedTab="main"></Bottom>
			</div>
		)
	}
}

export default Main;