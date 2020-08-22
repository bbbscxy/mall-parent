-- --------------------------------------------------------
-- 主机:                           47.93.185.126
-- 服务器版本:                        5.7.31 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mall 的数据库结构
CREATE DATABASE IF NOT EXISTS `mall` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mall`;

-- 导出  表 mall.mall_goods 结构
CREATE TABLE IF NOT EXISTS `mall_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `sub_name` varchar(100) DEFAULT NULL COMMENT '商品简介',
  `pic` varchar(150) NOT NULL COMMENT '商品图片',
  `price` bigint(20) NOT NULL COMMENT '商品价格（单位分）',
  `goods_type_id` bigint(20) NOT NULL COMMENT '归属分类',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0下架 1上架）',
  `on_sale_time` datetime DEFAULT NULL COMMENT '上架时间',
  `off_sale_time` datetime DEFAULT NULL COMMENT '下架时间',
  `sale_count` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_goods_detail 结构
CREATE TABLE IF NOT EXISTS `mall_goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `detail` text NOT NULL COMMENT '商品详情',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_goods_resource 结构
CREATE TABLE IF NOT EXISTS `mall_goods_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `url` varchar(150) NOT NULL COMMENT '资源地址',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '资源类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_goods_type 结构
CREATE TABLE IF NOT EXISTS `mall_goods_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级分裂',
  `logo` varchar(150) DEFAULT NULL COMMENT '分类图标',
  `desc` varchar(200) DEFAULT NULL COMMENT '分类描述',
  `path` varchar(100) DEFAULT NULL COMMENT '路径',
  `goods_count` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_member 结构
CREATE TABLE IF NOT EXISTS `mall_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `head_url` varchar(150) DEFAULT NULL COMMENT '头像',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '账户余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_member_address 结构
CREATE TABLE IF NOT EXISTS `mall_member_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货人',
  `address` varchar(100) NOT NULL COMMENT '收货地址',
  `phone` varchar(11) NOT NULL COMMENT '联系方式',
  `default_flag` char(1) NOT NULL DEFAULT '0' COMMENT '默认地址（0否 1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_member_login_account 结构
CREATE TABLE IF NOT EXISTS `mall_member_login_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `login_account` varchar(50) NOT NULL COMMENT '登录账号',
  `login_password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `account_type` char(1) NOT NULL COMMENT '账号类型（0手机号 1邮箱 2微信）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_order 结构
CREATE TABLE IF NOT EXISTS `mall_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_sn` varchar(20) NOT NULL COMMENT '订单编号',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `total_money` bigint(20) NOT NULL COMMENT '订单总价',
  `discount_money` bigint(20) NOT NULL COMMENT '优惠金额',
  `freight_money` bigint(20) NOT NULL COMMENT '运费',
  `real_money` bigint(20) NOT NULL COMMENT '实际金额',
  `pay_money` bigint(20) NOT NULL COMMENT '实付金额',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '订单状态（0待支付 1待发货 2待收货 3已完成）',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_order_address 结构
CREATE TABLE IF NOT EXISTS `mall_order_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货人',
  `address` varchar(100) NOT NULL COMMENT '收货地址',
  `phone` varchar(11) NOT NULL COMMENT '联系方式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_order_item 结构
CREATE TABLE IF NOT EXISTS `mall_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
  `goods_price` bigint(20) NOT NULL COMMENT '商品价格',
  `goods_num` int(11) NOT NULL COMMENT '商品数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_store 结构
CREATE TABLE IF NOT EXISTS `mall_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `address` varchar(150) DEFAULT NULL COMMENT '地址',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

-- 导出  表 mall.mall_store_account 结构
CREATE TABLE IF NOT EXISTS `mall_store_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `login_account` varchar(50) NOT NULL COMMENT '登录账号',
  `login_password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `account_type` char(1) NOT NULL DEFAULT '0' COMMENT '账号类型（0手机号 1邮箱 2微信）',
  `admin_flag` char(1) NOT NULL DEFAULT '0' COMMENT '管理员（0否 1是）',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1禁止登录 2注销）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0未删除  1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
