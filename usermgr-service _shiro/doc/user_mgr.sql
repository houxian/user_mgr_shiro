/*
SQLyog Community v9.10 
MySQL - 8.0.19 : Database - user_mgr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user_mgr` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `user_mgr`;

/*Table structure for table `u_company` */

DROP TABLE IF EXISTS `u_company`;

CREATE TABLE `u_company` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库自增id',
  `company_id` bigint NOT NULL COMMENT '公司id',
  `company_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
  `company_des` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '公司描述',
  `crate_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`,`company_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_company` */

insert  into `u_company`(`id`,`company_id`,`company_name`,`company_des`,`crate_date`,`update_date`,`status`) values (1,1,'奇酷软件深圳有限公司','360集团',NULL,NULL,'N');

/*Table structure for table `u_department` */

DROP TABLE IF EXISTS `u_department`;

CREATE TABLE `u_department` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `department_id` bigint DEFAULT NULL COMMENT '部门id',
  `department_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint DEFAULT NULL COMMENT '上级部门',
  `grade` int DEFAULT NULL COMMENT '部门等级',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_department` */

insert  into `u_department`(`id`,`department_id`,`department_name`,`parent_id`,`grade`,`create_date`,`update_date`,`status`) values (1,1,'360集团',NULL,NULL,NULL,NULL,'N'),(2,2,'360OS',1,NULL,NULL,NULL,'N'),(3,3,'技术部',2,NULL,NULL,NULL,'N'),(4,4,'设计部',2,NULL,NULL,NULL,'N'),(5,5,'行政部',2,NULL,NULL,NULL,'N');

/*Table structure for table `u_menu` */

DROP TABLE IF EXISTS `u_menu`;

CREATE TABLE `u_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单id',
  `m_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `m_parent_id` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `m_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URL,类型：1.普通页面（如用户管理， /mgr/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)',
  `m_perms` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `m_type` int DEFAULT NULL COMMENT '0：目录 1：菜单 2：按钮',
  `m_order_num` int DEFAULT NULL COMMENT '排序',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_menu` */

insert  into `u_menu`(`id`,`menu_id`,`m_name`,`m_parent_id`,`m_url`,`m_perms`,`m_type`,`m_order_num`,`create_date`,`update_date`,`status`) values (1,1,'系统管理',0,NULL,NULL,0,0,NULL,NULL,'N'),(2,2,'用户管理',1,'/sys/user',NULL,1,1,NULL,NULL,'N'),(3,3,'查看',2,NULL,'sys:user:view',2,0,NULL,NULL,'N'),(4,4,'添加',2,NULL,'sys:user:add',2,0,NULL,NULL,'N'),(5,5,'编辑',2,NULL,'sys:user:edit',2,0,NULL,NULL,'N'),(6,6,'删除',2,NULL,'sys:user:delete',2,0,NULL,NULL,'N'),(7,7,'机构管理',1,'/sys/dept',NULL,1,2,NULL,NULL,'N'),(8,8,'查看',7,NULL,'sys:dept:view',2,0,NULL,NULL,'N'),(9,9,'添加',7,NULL,'sys:dept:add',2,0,NULL,NULL,'N'),(10,10,'编辑',7,NULL,'sys:dept:edit',2,0,NULL,NULL,'N'),(11,11,'删除',7,NULL,'sys:dept:delete',2,0,NULL,NULL,'N');

/*Table structure for table `u_position` */

DROP TABLE IF EXISTS `u_position`;

CREATE TABLE `u_position` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `position_id` bigint DEFAULT NULL COMMENT '职位id',
  `position_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '职位名称',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_position` */

insert  into `u_position`(`id`,`position_id`,`position_name`,`create_date`,`update_date`,`status`) values (1,1,'CEO',NULL,NULL,'N'),(2,2,'董事长',NULL,NULL,'N'),(3,3,'技术总监',NULL,NULL,'N'),(4,4,'部门经理',NULL,NULL,'N'),(5,5,'普通员工',NULL,NULL,'N');

/*Table structure for table `u_position_role` */

DROP TABLE IF EXISTS `u_position_role`;

CREATE TABLE `u_position_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `position_id` bigint DEFAULT NULL COMMENT '职位id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_position_role` */

insert  into `u_position_role`(`id`,`position_id`,`role_id`,`create_date`,`update_date`,`status`) values (1,1,1,NULL,NULL,NULL),(2,1,2,NULL,NULL,NULL),(3,2,2,NULL,NULL,NULL);

/*Table structure for table `u_role` */

DROP TABLE IF EXISTS `u_role`;

CREATE TABLE `u_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `crate_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_role` */

insert  into `u_role`(`id`,`role_id`,`role_name`,`crate_date`,`update_date`,`status`) values (1,1,'超级管理员',NULL,NULL,'N'),(2,2,'管理员',NULL,NULL,'N'),(3,3,'普通员工',NULL,NULL,'N');

/*Table structure for table `u_role_menu` */

DROP TABLE IF EXISTS `u_role_menu`;

CREATE TABLE `u_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单id',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_role_menu` */

insert  into `u_role_menu`(`id`,`role_id`,`menu_id`,`create_date`,`update_date`,`status`) values (1,1,1,NULL,NULL,'N'),(2,1,2,NULL,NULL,'N'),(3,1,3,NULL,NULL,'N'),(4,1,4,NULL,NULL,'N'),(5,1,5,NULL,NULL,'N'),(6,1,6,NULL,NULL,'N'),(7,1,7,NULL,NULL,'N'),(8,1,8,NULL,NULL,'N'),(9,1,9,NULL,NULL,'N');

/*Table structure for table `u_user` */

DROP TABLE IF EXISTS `u_user`;

CREATE TABLE `u_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `u_id` bigint NOT NULL COMMENT '用户id',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `sex` int DEFAULT '0' COMMENT '性别 0男 1女',
  `employee_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '员工编号',
  `birthday` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '生日',
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `position_id` bigint DEFAULT NULL COMMENT '职位id',
  `leader_id` bigint DEFAULT NULL COMMENT '领导id',
  `department_id` bigint DEFAULT NULL COMMENT '部门id',
  `pwd` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '密码盐',
  `role_id` bigint DEFAULT NULL COMMENT '用户角色',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`,`u_id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_user` */

insert  into `u_user`(`id`,`u_id`,`name`,`sex`,`employee_id`,`birthday`,`email`,`phone`,`position_id`,`leader_id`,`department_id`,`pwd`,`salt`,`role_id`,`create_date`,`update_date`,`status`) values (1,1,'admin',0,'001',NULL,NULL,'18049442389',5,0,3,'a976bec3fd6f0e314b37eb15e3113d5e5831267d543e9f9a6a1b899f5b487c67','1y3u4r',1,NULL,NULL,'N');

/*Table structure for table `u_user_role` */

DROP TABLE IF EXISTS `u_user_role`;

CREATE TABLE `u_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据库自增id',
  `u_id` bigint DEFAULT NULL COMMENT '用户id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `create_date` bigint DEFAULT NULL COMMENT '创建时间',
  `update_date` bigint DEFAULT NULL COMMENT '更新时间',
  `status` char(1) COLLATE utf8_bin DEFAULT 'N' COMMENT '数据状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `u_user_role` */

insert  into `u_user_role`(`id`,`u_id`,`role_id`,`create_date`,`update_date`,`status`) values (1,1,1,NULL,NULL,'N');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
