var koa = require('koa');
var cors = require('koa-cors');
var koaRouter = require('koa-router');

var app = new koa();
var router = new koaRouter();

router.get('/api/shop/classify/classifyList', function *(next) {
    this.body = {
	    "code":20000,
	    "msg":"OK",
	    "data":[
	        {
	            "classifyId":"1",
	            "classifyName":"手机数码"
	        }
	    ]
	}
});

router.get('/api/shop/goods/goodsList', function *(next) {
    this.body = {
	    "code":20000,
	    "msg":"OK",
	    "data":{
	        "pageNum":0,
	        "pageSize":10,
	        "size":1,
	        "startRow":1,
	        "endRow":1,
	        "total":1,
	        "pages":1,
	        "list":[
	            {
	                "goodsId":"1",
	                "goodsName":"小辣椒 红辣椒R15 全面屏双摄拍照手机 6+128GB 黑色 全网通4G 双卡双待 大屏安卓手机京东自营",
	                "goodsTitle":"6.2英寸屏|红外人像识别解锁|无线充电，前置1600万AI美颜后置1600万+500万AI双摄",
	                "goodsImage":"http://m.360buyimg.com/mobilecms/s750x750_jfs/t23131/54/1346507459/233648/178a183a/5b5af8d3N0fed8584.jpg!q80.jpg.webp",
	                "goodsBody":null,
	                "goodsPrice":1899,
	                "goodsStock":100,
	                "classifyId":"0",
	                "classifyName":"平台"
	            }
	        ],
	        "prePage":0,
	        "nextPage":1,
	        "isFirstPage":false,
	        "isLastPage":false,
	        "hasPreviousPage":false,
	        "hasNextPage":true,
	        "navigatePages":8,
	        "navigatepageNums":[
	            1
	        ],
	        "navigateFirstPage":1,
	        "navigateLastPage":1,
	        "lastPage":1,
	        "firstPage":1
	    }
	}
});

router.get('/demo', function *(next) {
    // 参数
    const params = this.params;
    const paramsCity = params.city;
    const paramsPage = params.page;

    this.body = {
    	id: '002',
    	name: 'jack'
    }
});

// 开始服务并生成路由
app.use(cors({
    credentials:true,
    origin: 'http://localhost:4001'
})).use(router.routes()).use(router.allowedMethods());
app.listen(9000);