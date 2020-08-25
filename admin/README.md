# template

#### 项目介绍
template 是一个基于 SpringBoot 的快速开发平台，前端采用 AdminLTE，后端使用经典的 SpringMvc、Mybatis、Freemarker 模式。
平台具备基本的字典、配置、部门、区域以及日志记录功能，集成了 Shiro 权限管理，提供了可配置的代码生成以及常用的工具类如 excel、cookie、ftp 等；
项目在前端方面对 adminLTE 进行了精简，并搭配后端常用的 js 控件，提供了 ztree 树、日期选择框、搜索下拉框、单选控件以及bootstrap table和jqgrid。

#### 项目初衷
本项目的构思，来源于[Jeesite](http://www.jeesite.com?target=_blank)，一个用来快速开发的项目平台，目前已经到了4.0版本。公司中，使用该架构，开发了多个项目，均达到了良好的效果。
后来4.0的版本升级中，Jeesite开始出现了部门闭源，针对商业化进行了限制，同时前端采用了beetl模板引擎，针对熟悉freemarker和jsp的开发者来说，有点不近人情。另外jeesite的强大之处
在于提供了丰富的页面功能和后台功能插件，但是功能的繁多,造成了项目抽离复杂，以及学习成本的急速上升，为项目的定制化带来了巨大的麻烦。
由于种种原因，抱着学习的态度，去网上下载了adminLTE框架，然后对其进行了精简，将bootstrap table和form集成了进去，后期根据jeesite不断的添加新的内容，从前端到后端，全程借鉴了
jeesite的页面功能需求，但并未和jeesite代码有任何关联以及冲突，类似的平台还有[若依](http://www.ruoyi.com?target=_blank)，均是不错的快速开发平台。

#### 项目优势
+ 主流框架、精简功能、层次清晰、上手容易、丰富的前端控件

#### 技术选型
+ 主框架：Spring Boot 2.0.3.RELEASE、SpringMVC、Mybatis、Freemarker
+ 视图层：Freemarker、AdminLTE、Bootstrap
+ 前端组件：BootstrapTable、JqGrid、Daterangepicker、ICheck、JQueryUI、Layer、Select2、Ztree、Websocket
+ 权限控制：Shiro
+ 缓存框架：Ehcache
+ 工具组件：POI、FTP、Hibernate-validator、Jsoup

#### 技术介绍
+ 权限管理：使用 Shiro 框架，集成了 ehcache 缓存、session 超时、密码规则以及记住我的功能
+ 缓存管理：使用 Ehcache 框架，主要针对字典、配置以及用户菜单权限，进行了数据缓存
+ 日志记录：使用线程池处理队列的方式，根据自定义注解完成日志的记录
+ 代码生成：生成列表和编辑页面，并生成 controller-service-dao-model-sql 文件，提供了可配置的选项，来决定页面的查询条件以及编辑的表单内容
+ 区域管理：使用 jqgrid 表格异步渲染，显示省市区，并提供了 jsoup 针对国家省市区文件的爬虫代码
+ 消息推送：使用 websocket 动态记录登录人数的变化，并且管理员可以发送实时推送到其他用户
+ excel 导入导出：使用注解的方式，配置需要导出的字段，简便操作

#### 快速体验
1. 服务器准备中。。。

#### 项目截图
1. 用户管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/user_list.png)
2. 用户管理 -- 编辑

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/user_edit.png)
![Image text](https://gitee.com/bbbscxy/template/raw/master/img/user_edit_select.png)
3. 角色管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/role_list.png)
4. 角色管理 -- 编辑

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/role_edit.png)
5. 菜单管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/menu_list.png)
6. 菜单管理 -- 编辑

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/menu_edit.png)
7. 字典管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/dict_list.png)
8. 字典管理 -- 编辑

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/dict_edit.png)
9. 配置管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/config_list.png)
10. 代码生成

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/gen_list.png)
11. 代码生成 -- 编辑

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/gen_edit.png)
12. 部门管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/dept_list.png)
13. 区域管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/area_list.png)
14. 登录日志

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/log_list.png)
15. 缓存管理

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/cache_list.png)
16. 推送消息

![Image text](https://gitee.com/bbbscxy/template/raw/master/img/msg_list.png)

#### 安装教程
1. 使用项目 resources/scripts/website.sql 文件建立数据库
2. 根 pom 文件中,设置数据源 module.datasource
3. 根 pom 文件中, redis 以及 ftp 可暂时不设置,不影响项目效果
4. 根 pom 文件中, 根据环境设置 ehcache 和 log 目录
5. 按照 SpringBoot 方式启动 Application 即可