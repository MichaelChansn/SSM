/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.17 : Database - ksssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ksssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ksssm`;

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `adminname` varchar(50) NOT NULL COMMENT '管理员用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(100) DEFAULT NULL COMMENT 'email',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `mood` varchar(200) DEFAULT NULL COMMENT '心情',
  `single` tinyint(1) DEFAULT NULL COMMENT '单身or not',
  `enrolltime` datetime NOT NULL COMMENT '注册时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账户状态',
  `titlepic` varchar(100) DEFAULT NULL COMMENT '头像地址',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `logintime` datetime NOT NULL COMMENT '登录时间',
  `loginip` varchar(50) DEFAULT NULL COMMENT '最近登录ip',
  `token` varchar(100) DEFAULT NULL COMMENT 'token',
  `modifytime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminname` (`adminname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_info` */

/*Table structure for table `article_info` */

DROP TABLE IF EXISTS `article_info`;

CREATE TABLE `article_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `userid` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `pic` varchar(100) DEFAULT NULL COMMENT '文章图片路径',
  `content` text NOT NULL COMMENT '文章内容',
  `up` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点赞个数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章状态，审核or发表',
  `writetime` datetime NOT NULL COMMENT '第一次写文章的时间',
  `modifytime` datetime NOT NULL COMMENT '修改时间',
  `publishtime` datetime NOT NULL COMMENT '通过审核时间',
  `anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名发表？',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  FULLTEXT KEY `content` (`content`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `article_info` */

insert  into `article_info`(`id`,`userid`,`pic`,`content`,`up`,`status`,`writetime`,`modifytime`,`publishtime`,`anonymous`) values (1,1,'no','this is a test. 这就是个测试。。。',0,0,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',0);

/*Table structure for table `comment_info` */

DROP TABLE IF EXISTS `comment_info`;

CREATE TABLE `comment_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `articleid` bigint(20) unsigned NOT NULL COMMENT '文章id',
  `fromuserid` bigint(20) NOT NULL COMMENT '评论人id',
  `content` text NOT NULL COMMENT '评论内容',
  `commenttime` datetime NOT NULL COMMENT '评论时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '评论状态 通过or未通过，默认通过，举报后不通过',
  `modifytime` datetime NOT NULL COMMENT '评论修改时间（管理员修改）',
  `anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名评论？',
  PRIMARY KEY (`id`),
  KEY `articleid` (`articleid`),
  KEY `fromuserid` (`fromuserid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `comment_info` */

insert  into `comment_info`(`id`,`articleid`,`fromuserid`,`content`,`commenttime`,`status`,`modifytime`,`anonymous`) values (1,1,1,'this is a comment. 这就是个评论。。。','0000-00-00 00:00:00',1,'0000-00-00 00:00:00',0),(2,1,2,'测试','0000-00-00 00:00:00',1,'0000-00-00 00:00:00',0);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户信息表主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT 'email',
  `qq` varchar(50) DEFAULT NULL COMMENT 'QQ',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `gender` tinyint(1) DEFAULT NULL COMMENT '用户性别',
  `birthday` date DEFAULT NULL COMMENT '用户生日',
  `city` varchar(50) DEFAULT NULL COMMENT '用户城市',
  `mood` varchar(300) DEFAULT NULL COMMENT '用户心情',
  `single` tinyint(1) DEFAULT NULL COMMENT '用户情感状况',
  `enrolltime` datetime NOT NULL COMMENT '注册时间',
  `level` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '用户等级',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户账户状态启用or禁用',
  `titlepic` varchar(100) DEFAULT NULL COMMENT '用户头像图片地址',
  `job` varchar(100) DEFAULT NULL COMMENT '用户职业',
  `logintime` datetime NOT NULL COMMENT '登录时间',
  `loginip` varchar(50) DEFAULT NULL COMMENT '登录IP地址',
  `token` varchar(100) DEFAULT NULL COMMENT '用户token',
  `modifytime` datetime NOT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`password`,`email`,`qq`,`phone`,`gender`,`birthday`,`city`,`mood`,`single`,`enrolltime`,`level`,`status`,`titlepic`,`job`,`logintime`,`loginip`,`token`,`modifytime`) values (1,'ks','ks','ks','sk',NULL,NULL,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00',1,1,NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,'0000-00-00 00:00:00'),(6,'ks1','ks','ks',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00',1,1,NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,'0000-00-00 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
