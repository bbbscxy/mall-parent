import React, { Component } from 'react';
import './goodsHotComments.less';

/**
 * 商品详情子组件:热门评论
 * @param {data} 		数据
 * @param {data.total} 	评价总条数
 * @param {data.list} 	评论列表数据
 * @param {data.list[0].userName}} 			评论人名称
 * @param {data.list[0].commentsTime}} 		评论时间
 * @param {data.list[0].commentsContent}} 	评论内容
 */
class GoodsHotComments extends Component{
	
	constructor(props) {
	    super(props);
	    this.state = {
	    	goodsHotComments: {
	    		total: "900",
	    		list: [
	    			{
	    				userName: "柠***8",
	    				commentsTime: "2018-06-23",
	    				commentsContent: "东西很好，物流很快，试过加热蛋挞，蒸鱼，烤翅根，启动噪音有点大，可能是瓦数大吧，只要时间掌握好，叮出来的东西还是不错的，总的来说满意，买家电京东值得信赖。"
	    			},
	    			{
	    				userName: "柠***8",
	    				commentsTime: "2018-06-23",
	    				commentsContent: "东西很好，物流很快，试过加热蛋挞，蒸鱼，烤翅根，启动噪音有点大，可能是瓦数大吧，只要时间掌握好，叮出来的东西还是不错的，总的来说满意，买家电京东值得信赖。"
	    			},
	    			{
	    				userName: "柠***8",
	    				commentsTime: "2018-06-23",
	    				commentsContent: "东西很好，物流很快，试过加热蛋挞，蒸鱼，烤翅根，启动噪音有点大，可能是瓦数大吧，只要时间掌握好，叮出来的东西还是不错的，总的来说满意，买家电京东值得信赖。"
	    			},
	    			{
	    				userName: "柠***8",
	    				commentsTime: "2018-06-23",
	    				commentsContent: "东西很好，物流很快，试过加热蛋挞，蒸鱼，烤翅根，启动噪音有点大，可能是瓦数大吧，只要时间掌握好，叮出来的东西还是不错的，总的来说满意，买家电京东值得信赖。"
	    			}
	    		]
	    	}
	    }
	}
	
	render(){
		const { goodsHotComments } = this.state;
		return(
			<div id="goodsHotComments" className="goods_hot_comments_container">
				<div className="goods_hot_comments_title">
					<div className="left">
						<span>评价</span>
					</div>
					<div className="content"></div>
					<div className="right">
						共 {goodsHotComments.total} 条
					</div>
				</div>
				<div className="goods_hot_comments_content">
				{
					goodsHotComments.list.map((item, index) => {
						return(
							<div key={index} className="comments_item">
								<div className="top">
									<div className="left">
										{item.userName}
									</div>
									<div className="right">
										{item.commentsTime}
									</div>
								</div>
								<div className="bottom">
									{item.commentsContent}
								</div>
							</div>
						)
					})
				}
				</div>
			</div>
		)
	}
}

export default GoodsHotComments;