import React, { Component } from 'react';
import SeperateLine from 'common/components/SeperateLine.js';
import ImgCarousel from 'common/components/ImgCarousel.js';
import './goodsTitle.less';

/**
 * 商品详情子组件:商品简介和商品轮播图
 */
class GoodsTitle extends Component{
	
	constructor(props) {
	    super(props);
	}
	
	render(){
		const { goodsInfo, goodsImages } = this.props;
		return(
			<div id="goodsTitle">
				<SeperateLine lineHeight={"0.9rem"}></SeperateLine>
				<ImgCarousel data={goodsImages}></ImgCarousel>
				<div className="goods_title_container">
					<div className="goods_title">
						<p>
							<img src="assets/img/title_tips.png"/>
							{goodsInfo.goodsName}
						</p>
					</div>
					<div className="goods_subtitle">
						<p>
							{goodsInfo.goodsTitle}
						</p>
					</div>
					<div className="goods_price">
						<p>
							￥{goodsInfo.goodsPriceStr}
						</p>
					</div>
				</div>
			</div>
		)
	}
}

export default GoodsTitle;