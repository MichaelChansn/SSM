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
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '文章状态，审核or发表',
  `writetime` datetime NOT NULL COMMENT '第一次写文章的时间',
  `modifytime` datetime NOT NULL COMMENT '修改时间',
  `publishtime` datetime NOT NULL COMMENT '通过审核时间',
  `anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名发表？',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  FULLTEXT KEY `content` (`content`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `article_info` */

insert  into `article_info`(`id`,`userid`,`pic`,`content`,`up`,`status`,`writetime`,`modifytime`,`publishtime`,`anonymous`) values (33,9,'20160503233048.jpg','2016白宫段子手奥巴马封箱演讲最最精简版···\r\n奥巴马上台前，白宫记者协会播放了一段奥巴马的视频以纪念他8年总统任期，视频内容全是他出丑丢人和口误的合集，例如一次他称美国有57个州·其实美国共有50个州和1个直辖特区。\r\n开场后奥巴马说他的任期只剩下6个月。虽还没下台，但现在已经感觉没人待见了，访问英国时小王子都穿着睡衣接见他。\r\n他说自己无法理解支持率上涨，这时屏幕上出现共和党总统竞选人特朗普和克鲁兹两张拍得较尴尬的照片，全场大笑。\r\n奥巴马吐槽说特朗普竟然成为最有可能获得提名总统候选人，共和党对此表示难以置信…',6,0,'2016-05-03 23:30:48','2016-05-03 23:30:48','2016-05-03 23:30:48',0),(34,9,'20160503233120.jpg','&lt;script&gt;alert(\"测试测试\")&lt;/script&gt;',4,1,'2016-05-03 23:31:20','2016-05-03 23:31:20','2016-05-03 23:31:20',0),(35,9,'20160503233215.jpg','和一好朋友合租，有一天哥们叫我去帮他买飞机杯（当时楼主以为是喝水的杯子），就答应帮他买了，你们能想象我一个男人在超市的前台对收银妹子问有没有飞机杯的时候她是什么表情吗，后面在回家的路上在百度上搜索了飞机杯，呵呵～～老板，我就要那把菜刀。',4,1,'2016-05-03 23:32:16','2016-05-03 23:32:16','2016-05-03 23:32:16',0),(36,9,'20160503233246.jpg','发现一个重大秘密，当你分享微信好友的时候，能发现最近联系人，包括你已经拉黑名单，删除后的人，大家多注意，你问我咋知道的，跪着给大家发的，引以为戒',6,1,'2016-05-03 23:32:46','2016-05-03 23:32:46','2016-05-03 23:32:46',0),(37,9,'20160503233309.jpg','甲说：“我捡了一个大车轮胎～”\r\n乙相当惊讶加羡慕：“那你发了啊，能卖不少钱啊！”\r\n甲说：“卧槽别提了，晚上看不清，就看到一个东西滚过来，撞上了，住院花了五千，轮胎才卖了两千～”',2,1,'2016-05-03 23:33:10','2016-05-03 23:33:10','2016-05-03 23:33:10',0),(38,9,'20160503233345.jpg','前几天相亲，妹子问我近视度数，我说250度，妹子又问250可以看多远，我说白天可以看到太阳，晚上能看到星星那么远。妹子转身就走了……',4,1,'2016-05-03 23:33:46','2016-05-03 23:33:46','2016-05-03 23:33:46',0),(39,9,'20160503233400.jpg','小时候听人说村里人说，牛奶很好喝，大补。于是有一天晚上就去邻居那里牛圈里黑灯瞎火的，对着牛下面猛吸了几口，别说还真挺好喝。现在那头牛卖了，听村里人说那头公牛卖了不少钱呢。',2,1,'2016-05-03 23:34:01','2016-05-03 23:34:01','2016-05-03 23:34:01',0),(40,9,'20160503233422.jpg','同事跟来访客户电话沟通：\r\n同事：我们公司在B座楼，B座\r\n客户：B座还是D座？\r\n同事：B，ABCD的D。\r\n客户：…',3,1,'2016-05-03 23:34:23','2016-05-03 23:34:23','2016-05-03 23:34:23',0),(42,9,'20160504000630.jpg','甲说：“我捡了一个大车轮胎～”\r\n乙相当惊讶加羡慕：“那你发了啊，能卖不少钱啊！”\r\n甲说：“卧槽别提了，晚上看不清，就看到一个东西滚过来，撞上了，住院花了五千，轮胎才卖了两千～”<br><br><br><br>哈哈哈哈',4,1,'2016-05-04 00:06:30','2016-05-04 00:06:30','2016-05-04 00:06:30',0),(43,9,'20160504000851.jpg','甲说：“我捡了一个大车轮胎～”\r<br>乙相当惊讶加羡慕：“那你发了啊，能卖不少钱啊！”\r<br>甲说：“卧槽别提了，晚上看不清，就看到一个东西滚过来，撞上了，住院花了五千，轮胎才卖了两千～”',4,1,'2016-05-04 00:08:51','2016-05-04 00:08:51','2016-05-04 00:08:51',0),(44,9,'20160504002306.jpg','有一天，一个陌生的女子加了我的QQ。\r<br>她说：你喜欢你们隔壁班的郝帅吧。\r<br>我：我喜欢他很久了，怎么了？\r<br>她：我要公平竞争！\r<br>我：竞争就竞争，怕你啊！\r<br>她：其实我是les,我，，，喜欢你很久了。\r<br>我：这。。。老子长的那么像女的？',5,1,'2016-05-04 00:23:07','2016-05-04 00:23:07','2016-05-04 00:23:07',0),(45,9,'20160504002412.jpg','今天晚上干了一件特NB的事，下班回家路上看见个卖火烧的推着车子在走，我过去就说：老板来三个火烧 老板说：“不卖”，然后推着车往前走，哎我这暴脾气上去就拦住车问，老板你特么是嫌我买的太少还是怕我不给钱啊？你猜怎么着？老板停下车对我说，滚犊子老子是城管……\r<br>\r<br>ps：当我走到前面才知道城管把卖火烧老奶奶的车推走了，地点郑州金水政七街上面大概七点左右 绝对真事 文采不好见谅',6,1,'2016-05-04 00:24:13','2016-05-04 00:24:13','2016-05-04 00:24:13',0),(46,10,'20160504193948.jpg','我卖个小沙漏容易吗？有人要订做能漏一年的，有人说我30分钟的沙漏29分50秒就漏完了，这我都忍了，今天竟然有个人要订做用他自己骨灰做个沙漏，大哥，你最好现在就死，要不我怕自己活不了几天了，快被气死了。\r<br>',34,1,'2016-05-04 19:39:48','2016-05-04 19:39:48','2016-05-04 19:39:48',0),(47,10,'20160506204438.jpg','把我一个智商情商逼得相当不正常的人，我慢慢才发现本来我是一个女人，好嘛，现在被其他人逼得男人不可能做，女人也做够了，肿么办？纳尼，哪来这么多奇葩，我真想晕倒以后，重生一次，跟那游戏一样，可是，为嘛木有后悔药可以卖的，后悔贴也可以啊，如果有我一定穿越到前世，然后祈求上天把我变成一株植物！纠结性格烦死人，虐死人，多动症奇葩份子真想分分钟给我咔咔两刀，结果了！每天不是打瞌睡，就是吃东西，要不然就是奋笔疾书，跟个神婆一样，又哭又笑的，要不然就是多动症看到一个东西就拿到手里掰扯，然后直到它光荣牺牲，再拆其他的零件，跟着感觉走真的很累有木有，一会儿想吃什么必须要吃到，不然做梦都在吃，一会儿想出去，逼着自己不能出，好可怜的说，洁癖吃东西之前不洗手觉得一口都吃不下去还觉得慢慢反胃！喜欢香，爱美，人生都有克星，我的克星就是把我变肥变丑的人！一个丫头为嘛因为各种不知所谓的人把我变成又脏又胖又丑的人？不盯着我你们能屎吗？',1,1,'2016-05-06 20:44:39','2016-05-06 20:44:39','2016-05-06 20:44:39',0),(48,9,'20160509125709.jpg','test十五个字啊啊啊啊啊啊啊啊啊啊',1,1,'2016-05-09 12:57:10','2016-05-09 12:57:10','2016-05-09 12:57:10',0),(49,9,'20160510103304.jpg','哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈',1,1,'2016-05-10 10:33:05','2016-05-10 10:33:05','2016-05-10 10:33:05',1),(50,10,'20160510103930.jpg','哈哈哈哈哈哈哈哈哈哈哈哈匿名是是是',1,1,'2016-05-10 10:39:31','2016-05-10 10:39:31','2016-05-10 10:39:31',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `comment_info` */

insert  into `comment_info`(`id`,`articleid`,`fromuserid`,`content`,`commenttime`,`status`,`modifytime`,`anonymous`) values (1,1,1,'this is a comment. 这就是个评论。。。','0000-00-00 00:00:00',1,'0000-00-00 00:00:00',0),(2,1,2,'测试','0000-00-00 00:00:00',1,'0000-00-00 00:00:00',0),(3,46,9,'这就是个测试','2016-05-05 21:58:29',0,'2016-05-06 21:43:37',0),(4,46,10,'这也是个测试','2016-05-05 22:08:58',1,'2016-05-05 22:09:00',0),(5,46,10,'和就是个测试','2016-05-06 00:26:57',1,'2016-05-06 00:26:57',0),(6,46,10,'和就是个测试','2016-05-06 00:26:57',1,'2016-05-06 00:26:57',0),(7,46,10,'这是个测试','2016-05-06 00:32:28',1,'2016-05-06 00:32:28',0),(8,46,10,'侧测试','2016-05-06 00:33:51',1,'2016-05-06 00:33:51',0),(9,46,10,'水水水水','2016-05-06 00:34:19',1,'2016-05-06 00:34:19',0),(10,46,10,'水水水水','2016-05-06 00:38:11',1,'2016-05-06 00:38:11',0),(11,46,10,'按时发发算法','2016-05-06 00:39:52',1,'2016-05-06 00:39:52',0),(12,46,10,'阿萨法','2016-05-06 00:43:19',1,'2016-05-06 00:43:19',0),(13,45,10,'千万人完全','2016-05-06 00:46:01',0,'2016-05-06 21:44:59',0),(14,45,10,'啊发顺丰','2016-05-06 00:46:45',0,'2016-05-06 21:47:12',0),(15,45,10,'按时发放','2016-05-06 00:47:29',0,'2016-05-06 22:04:49',0),(16,45,10,'按时发放','2016-05-06 00:51:18',0,'2016-05-06 22:26:04',1),(17,45,10,'阿达','2016-05-06 00:51:50',0,'2016-05-10 10:32:33',0),(18,45,10,'按时发发发','2016-05-06 00:51:54',0,'2016-05-06 22:06:11',1),(19,45,10,'按时发放','2016-05-06 00:52:55',1,'2016-05-06 00:52:55',1),(20,45,10,'阿萨法','2016-05-06 00:53:00',1,'2016-05-06 00:53:00',0),(21,46,10,'啊发发','2016-05-06 00:54:03',0,'2016-05-06 22:25:49',1),(22,46,10,'暗示法法师','2016-05-06 00:54:08',1,'2016-05-06 00:54:08',0),(23,45,10,'啊发发','2016-05-06 00:55:30',1,'2016-05-06 00:55:30',0),(24,44,10,'阿达','2016-05-06 00:55:52',1,'2016-05-06 00:55:52',1),(25,44,10,'打发','2016-05-06 00:56:42',1,'2016-05-06 00:56:42',1),(27,46,10,'&lt;script&gt;alert(\"hello\")&lt;/script&gt;','2016-05-06 10:31:12',1,'2016-05-06 10:31:12',0),(28,45,10,'test','2016-05-06 10:38:24',1,'2016-05-06 10:38:24',0),(29,45,10,'asfafasf','2016-05-06 10:39:27',1,'2016-05-06 10:39:27',0),(30,45,10,'时间测试','2016-05-06 10:39:58',1,'2016-05-06 10:39:58',0),(31,45,10,'his is a test阿肯很舒服哈咖啡哈客户康师傅哈咖啡哈客户反馈还是饭卡还付款 赛后饭卡和饭卡好看 啊刷广发卡 u 根枯发嘎嘎发 u 啊还是哭发哈库和饭卡和开发好卡好kjasbajskjfjkafksafkaf asgfkagfkakhkfsahukfhagf asgfkagkfgakgfkasgfksagkfgkaa ashflahfahufhsaihifh this\r<br>ahkjahkfhkakhakh','2016-05-06 10:40:14',0,'2016-05-06 22:06:34',0),(32,45,10,'&lt;span style=\"float:right\"&gt;&nbsp;评论于$!date.format(\'yyyy-MM-dd HH:mm:ss\',$!{userAndcomment.comment.commenttime})&lt;/span&gt;','2016-05-06 10:43:28',0,'2016-05-06 22:06:38',0),(33,46,10,'test','2016-05-06 10:54:25',1,'2016-05-06 10:54:25',0),(34,45,10,'灌灌灌灌','2016-05-06 10:57:09',1,'2016-05-06 10:57:09',0),(35,44,10,'阿斯顿发发','2016-05-06 10:57:37',1,'2016-05-06 10:57:37',0),(36,45,10,'阿发啊','2016-05-06 11:03:30',1,'2016-05-06 11:03:30',0),(37,45,10,'按时发发','2016-05-06 11:04:47',1,'2016-05-06 11:04:47',0),(38,45,10,'爱傻瓜瓜是否噶暗示过覆盖','2016-05-06 11:06:41',1,'2016-05-06 11:06:41',0),(39,46,10,'测试平路书','2016-05-06 17:57:58',0,'2016-05-06 21:43:47',0),(40,39,10,'哈哈哈哈哈哈哈哈','2016-05-06 19:24:00',0,'2016-05-06 22:25:33',0),(41,47,10,'一楼','2016-05-06 20:47:40',0,'2016-05-06 21:36:26',0),(42,42,10,'test','2016-05-06 22:07:15',1,'2016-05-06 22:07:15',0),(43,45,10,'test','2016-05-06 22:09:51',0,'2016-05-06 22:11:49',0),(44,45,10,'test','2016-05-06 22:11:53',1,'2016-05-06 22:11:53',0),(45,44,10,'test','2016-05-06 22:12:32',0,'2016-05-06 22:12:35',0),(46,47,9,'一楼？？？？','2016-05-06 22:18:25',0,'2016-05-06 22:18:30',0),(47,46,9,'test','2016-05-09 12:49:34',1,'2016-05-09 12:49:34',1),(48,46,9,'test','2016-05-09 12:52:43',1,'2016-05-09 12:52:43',0),(49,46,9,'test','2016-05-09 12:52:55',0,'2016-05-10 13:20:31',0),(50,44,9,'啊发发','2016-05-09 12:54:01',1,'2016-05-09 12:54:01',0),(51,44,9,'水水水水','2016-05-09 12:54:55',1,'2016-05-09 12:54:55',0),(52,45,9,'阿萨法','2016-05-10 10:32:36',0,'2016-05-10 13:20:14',0),(53,49,9,'对对对','2016-05-10 10:33:34',0,'2016-05-10 10:33:37',0),(54,47,9,'柔柔弱弱','2016-05-10 10:34:58',0,'2016-05-10 10:35:04',0),(55,47,9,'发发发','2016-05-10 10:35:13',0,'2016-05-10 10:35:18',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`password`,`email`,`qq`,`phone`,`gender`,`birthday`,`city`,`mood`,`single`,`enrolltime`,`level`,`status`,`titlepic`,`job`,`logintime`,`loginip`,`token`,`modifytime`) values (9,'ks','7761ae9be00d3c31c38d5c468b69991b','13672025181@163.com',NULL,NULL,1,'1991-10-08',NULL,'用户未设置心情',NULL,'2016-05-01 21:48:40',1,1,'20160510100200.jpg',NULL,'2016-05-10 13:06:00','0:0:0:0:0:0:0:1','f6869b1659fa2de2d0455cbaf8718957','2016-05-10 13:01:03'),(10,'你猜','8394e5e267cffcda5fa8f06d21122ecd','564289319@qq.com',NULL,NULL,1,'2016-05-10',NULL,'用户未设置心情',NULL,'2016-05-03 22:14:08',1,1,'20160510103904.jpg',NULL,'2016-05-10 13:14:36','0:0:0:0:0:0:0:1','9beb297265eacd153d930560451d7b5f','2016-05-10 13:14:48');

/*Table structure for table `userlike_info` */

DROP TABLE IF EXISTS `userlike_info`;

CREATE TABLE `userlike_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '点赞或点踩ID',
  `fromuserid` bigint(20) DEFAULT NULL COMMENT '点赞的用户ID，可为空，未登录',
  `up` tinyint(1) NOT NULL DEFAULT '0' COMMENT '赞',
  `down` tinyint(1) NOT NULL DEFAULT '0' COMMENT '踩',
  `articleid` bigint(20) unsigned NOT NULL COMMENT '文章ID',
  `time` datetime NOT NULL COMMENT '点赞时间',
  `ip` varchar(50) NOT NULL COMMENT '点赞时的IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userlike_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
