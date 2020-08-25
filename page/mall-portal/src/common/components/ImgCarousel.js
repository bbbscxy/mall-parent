import React, { Component } from 'react';
import { Carousel } from 'antd-mobile';
import 'common/components/imgCarousel.less';

/**
 * 轮播图组件
 * @param {data} 图片数据
 * @param {data[0].imgUrl} 图片地址
 */
class ImgCarousel extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	initialHeight: '7.5rem',
	    }
	}
	
	render(){
		const { data } = this.props;
	 	const hProp = this.state.initialHeight ? { height: this.state.initialHeight } : {};
	 	var autoplay = true;
	 	var dots = true;
	 	if(data && data.length == 1){
	 		autoplay = false;
	 		dots = false;
	 	}
		return(
	        <Carousel autoplay={autoplay} dots={dots} infinite autoplayInterval={2000}>
          		{
          			data && data.length >= 0 && data.map((item, index) => {
		          		return(
		          			<div key={index} className='carousel'>
			          			<img style={hProp} src={item}
			          				onLoad={() => {
					                  window.dispatchEvent(new Event('resize'));
					                  this.setState({
					                    initialHeight: null,
					                  });
					                }}
			          			/>
		          			</div>
		          		)
		          	})
	          	}
	        </Carousel>
		)
	}
}

export default ImgCarousel;