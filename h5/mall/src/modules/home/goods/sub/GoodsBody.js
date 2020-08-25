import React, { Component } from 'react';
import './goodsBody.less';

/**
 * 商品详情子组件:详情展示
 */
class GoodsBody extends Component{
	render(){
		var content = '<div class="goods_body_content">';
		content += '</div>';
		return(
			<div id="goodsBody" className="goods_body">
				<ul className="goods_body_title">
					<li className="active">商品介绍</li>
					{/*<li>售后保障</li>*/}
				</ul>
				<div className="goods_info" dangerouslySetInnerHTML={{
	              __html: content
	            }}/>
			</div>
		)
	}
}

export default GoodsBody;