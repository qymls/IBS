/*
SQLyog Ultimate v8.8 
MySQL - 5.5.28 : Database - ibs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ibs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ibs`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (1,'IT部'),(2,'采购部'),(3,'销售部'),(4,'测试部'),(5,'财务部');

/*Table structure for table `depot` */

DROP TABLE IF EXISTS `depot`;

CREATE TABLE `depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `maxCapacity` decimal(19,2) DEFAULT NULL,
  `currentCapacity` decimal(19,2) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `depot` */

insert  into `depot`(`id`,`name`,`maxCapacity`,`currentCapacity`,`totalAmount`) values (1,'成都仓库','10000.00','100.00','10000.00');

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`id`,`name`) values (1,'部门一'),(2,'阿斯蒂芬'),(3,'2342134'),(4,'玩儿'),(6,'1111111111'),(7,'23t456'),(8,'4htry45y'),(10,'二医院 '),(12,'1234'),(13,'sdfa'),(14,'aaaaa'),(16,'asdfadf');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `headImage` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4AFD4ACE851EFECF` (`department_id`),
  CONSTRAINT `FK4AFD4ACE851EFECF` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`username`,`password`,`email`,`headImage`,`age`,`department_id`) values (1,'admin','123456','admin@itsource.com','/images/head/avatar.png',34,1),(2,'roleAdmin','d563ad18fb8e2067eaee76ca27f3e8a3','roleAdmin@itsource.cn','/images/head/avatar1.png',25,1),(3,'admin1','6a4876827226fb87ffeb78edb2b1e7ad','amdin1@itsource.cn','/images/head/avatar2.png',2500,1),(4,'989','89','89',NULL,25,2),(5,'admin3','89335a5f0fdd015113e9acf90727cdce','23323','/images/head/avatar3.png',9923,3),(6,'admin4','12e4dc60e1813184b3e4552dedd7bf9b','amdin4@itsource.cn','/images/head/avatar3.png',25,3),(7,'admin5','02f9b7f759b5654d421c0ce458d16c28','amdin5@itsource.cn','/images/head/avatar3.png',25,1),(8,'admin6','138e943e0987d1fff7a4c367deedd4e3','amdin6@itsource.cn','/images/head/avatar3.png',25,2),(9,'admin7','53bf9cd2cb250a9d82c3260b6d398d73','amdin7@itsource.cn',NULL,25,2),(10,'admin8','f6bd6e8ca007216fef1ddd9652e5d42f','amdin8@itsource.cn','/images/head/avatar3.png',250,1),(11,'admin9','bc9ee22de9d477a4dc21527fb5288e09','amdin9@itsource.cn','/images/head/avatar3.png',25,2),(12,'admin10','4af867a168f310ac9f542fb27d088412','amdin10@itsource.cn','/images/head/avatar3.png',25,2),(13,'admin11','c953909c87335d7fbc0f004c850d5899','amdin11@itsource.cn','/images/head/avatar3.png',25,1),(14,'admin12','57c15b685c731241ceb257436da984ce','amdin12@itsource.cn','/images/head/avatar3.png',10,3),(15,'admin13','72b5871658a55870f55c7cedcdbfc750','amdin13@itsource.cn','/images/head/avatar3.png',25,3),(16,'admin14','3aaae1c105814d0e3b6de8f4e7befe3f','amdin14@itsource.cn','/images/head/avatar3.png',25,3),(17,'admin15','ec466277d5e194ce58b4be14de342e0d','amdin15@itsource.cn','/images/head/avatar3.png',25,3),(18,'admin16','0f49d0f9f47fbdbe311b90b39d071389','amdin16@itsource.cn','/images/head/avatar3.png',25,1),(19,'admin17','684e22f740a05d25f63a16ed9b22bd3a','amdin17@itsource.cn','/images/head/avatar3.png',25,1),(20,'admin18','6b3f18873b8c1fa7948d2eefd236f0b0','amdin18@itsource.cn','/images/head/avatar3.png',25,1),(21,'admin19','6ed03e3efc7f62c780e5a5f05327c2dd','amdin19@itsource.cn','/images/head/avatar3.png',25,3),(22,'admin20','b19bb9f57188bb9017a5f1e334f6cad4','amdin20@itsource.cn','/images/head/avatar3.png',25,3),(23,'admin21','8d366e02c4373f215e5fae12ef04ecb2','amdin21@itsource.cn','/images/head/avatar3.png',25,3),(24,'admin22','8a27c7cc380d853babd2c04c9d72625f','amdin22@itsource.cn','/images/head/avatar3.png',25,3),(25,'admin23','1a9687e7f1d50e800b2ece459e654408','amdin23@itsource.cn','/images/head/avatar3.png',25,3),(26,'admin24','4f3d03f2d6eaa67ee678cb25ab6f30fd','amdin24@itsource.cn','/images/head/avatar3.png',25,3),(27,'admin25','8309757711ae53bf971ada0eb756fcad','amdin25@itsource.cn','/images/head/avatar3.png',25,1),(28,'admin26','65faa932744343a804d2644d6725c809','amdin26@itsource.cn','/images/head/avatar3.png',25,3),(29,'admin27','c37e2a159c4104b5efafeb48c4d3f8a8','amdin27@itsource.cn','/images/head/avatar3.png',25,1),(30,'admin28','b4d8369858ca62b1871f611a17ab6c85','amdin28@itsource.cn','/images/head/avatar3.png',25,3),(31,'admin29','32761f6f732cbd3a9b1395f3d0282361','amdin29@itsource.cn','/images/head/avatar3.png',25,1),(32,'admin30','2bde47bd1a502737c2bf7ef8bca461e7','amdin30@itsource.cn','/images/head/avatar3.png',25,1),(33,'admin31','1024d7721d427b5625ad0dd73a22d839','amdin31@itsource.cn','/images/head/avatar3.png',25,2),(34,'admin32','67b6c36942783249382c2871fbcc1cb5','amdin32@itsource.cn','/images/head/avatar3.png',25,3),(35,'admin33','8fda601d305b34e693f41b0f8cb4267e','amdin33@itsource.cn','/images/head/avatar3.png',25,2),(36,'admin34','fab9a4475f7ee7e49890cdf3b2f157bf','amdin34@itsource.cn','/images/head/avatar3.png',25,2),(37,'admin35','dd90df5259c45dab53823aa2b5ac5ab9','amdin35@itsource.cn','/images/head/avatar3.png',25,3),(38,'admin36','c265ec550b72583d14cf66f5874d3679','amdin36@itsource.cn','/images/head/avatar3.png',25,1),(39,'admin37','1dbb67fa18836c047c87fa1fbffacd6b','amdin37@itsource.cn','/images/head/avatar3.png',25,2),(40,'admin38','2bd08251c9aba4e1057bbe877164cbe1','amdin38@itsource.cn','/images/head/avatar3.png',25,3),(41,'admin39','4b56537a50ebe8e4814e7e684ec7e938','amdin39@itsource.cn','/images/head/avatar3.png',25,2),(42,'admin40','ab9d29a6001519fe0ae60064c350760a','amdin40@itsource.cn','/images/head/avatar3.png',25,3),(43,'admin41','36d035b690b5d06002f3e26cc8cc02aa','amdin41@itsource.cn','/images/head/avatar3.png',25,1),(44,'admin42','6189eeb659b66c0121d8e6c5865865a8','amdin42@itsource.cn','/images/head/avatar3.png',25,1),(45,'admin43','7c436b40cc462818481ba1ef64f4a4ca','amdin43@itsource.cn','/images/head/avatar3.png',25,3),(46,'admin44','97d22105abec0c9672d503723e136100','amdin44@itsource.cn','/images/head/avatar3.png',25,1),(47,'admin45','c8db7f4d490c177925c3c86f11ece83c','amdin45@itsource.cn','/images/head/avatar3.png',25,1),(48,'admin46','b78114c2399ae23a624b72de85d7cf51','amdin46@itsource.cn','/images/head/avatar3.png',25,1),(49,'admin47','c00cfd28ef690dc6920d1c9b251f42d8','amdin47@itsource.cn','/images/head/avatar3.png',25,1),(50,'admin48','214b22a99efeef099b1867874c337108','amdin48@itsource.cn','/images/head/avatar3.png',25,1),(51,'admin49','128dad1c555bf4ede2e26206dc92ce1b','amdin49@itsource.cn','/images/head/avatar3.png',25,3),(52,'admin50','062f6299b738b135a73e4c3a64a13a15','amdin50@itsource.cn','/images/head/avatar3.png',25,3),(53,'admin51','3d2f81419d5288c127ec815dfe9e4522','amdin51@itsource.cn','/images/head/avatar3.png',25,1),(54,'admin52','480870bc0920e59dde8998ceba258c03','amdin52@itsource.cn','/images/head/avatar3.png',25,1),(55,'admin53','b4dc6f1821a2a3332a4bcb3b89490997','amdin53@itsource.cn','/images/head/avatar3.png',25,2),(56,'admin54','9933c8cddd2577281c92b70aa9025f04','amdin54@itsource.cn','/images/head/avatar3.png',25,1),(57,'admin55','52d52dbf39f8459f5a716ce39aff4864','amdin55@itsource.cn','/images/head/avatar3.png',25,3),(58,'admin56','fcb2b6a672577d89f1227d07b78fcaef','amdin56@itsource.cn','/images/head/avatar3.png',25,2),(59,'admin57','f2e3452ad0c170f7c5dc307f02012007','amdin57@itsource.cn','/images/head/avatar3.png',25,3),(60,'admin58','bb8c525fa0dc184229ed2d99d9a6601e','amdin58@itsource.cn','/images/head/avatar3.png',25,3),(61,'admin59','ba8189906a9cc50bf156c62a8d55f835','amdin59@itsource.cn','/images/head/avatar3.png',25,2),(62,'admin60','a1cf16ba52327acee543ade45fe2a408','amdin60@itsource.cn','/images/head/avatar3.png',25,1),(63,'admin61','b38de8af165c14b7b68fb0dcdaea0e28','amdin61@itsource.cn','/images/head/avatar3.png',25,2),(64,'admin62','8f2531b74f008cd456696da6d6a909d7','amdin62@itsource.cn','/images/head/avatar3.png',25,3),(65,'admin63','bb16c7f9cbea20dafd0fe4f3e404c5fc','amdin63@itsource.cn','/images/head/avatar3.png',25,3),(66,'admin64','dfeab8b4b419cc4806545a3a1160023e','amdin64@itsource.cn','/images/head/avatar3.png',25,2),(67,'admin65','d7b78f460b9559557c13af5e637ea921','amdin65@itsource.cn','/images/head/avatar3.png',25,3),(68,'admin66','6b892f86125ed46422b582718bccbaa3','amdin66@itsource.cn','/images/head/avatar3.png',25,1),(69,'admin67','89186a2ef55c1e92160d40eac0358fa4','amdin67@itsource.cn','/images/head/avatar3.png',25,3),(70,'admin68','724beb114c3e0094974b4a495861534f','amdin68@itsource.cn','/images/head/avatar3.png',25,1),(71,'admin69','a2311a2b59adbb74ddaa8131993e8963','amdin69@itsource.cn','/images/head/avatar3.png',25,2),(72,'admin70','7b8a5667d10bb57744138f5cd6eb3411','amdin70@itsource.cn','/images/head/avatar3.png',25,1),(73,'admin71','927165e67ed3039642578e4f18a65f3c','amdin71@itsource.cn','/images/head/avatar3.png',25,1),(74,'admin72','028615d0a82f39b32ef8df6d7ec9e6d5','amdin72@itsource.cn','/images/head/avatar3.png',25,3),(75,'admin73','b561bc012063aaf336eddc132564b39b','amdin73@itsource.cn','/images/head/avatar3.png',25,2),(76,'admin74','fa33a53cf7b3ff49e31da3671d421ad4','amdin74@itsource.cn','/images/head/avatar3.png',25,2),(77,'admin75','cf4eb8cd0d65a61d0acf0212cb98719f','amdin75@itsource.cn','/images/head/avatar3.png',25,2),(78,'admin76','e7b06297d2c6fb7426c19a184006ea04','amdin76@itsource.cn','/images/head/avatar3.png',25,3),(79,'admin77','7cc040d1d56a399597a1ca1105a8f392','amdin77@itsource.cn','/images/head/avatar3.png',25,3),(80,'admin78','0afc812c6e7f064a0581c7ac8823e4e1','amdin78@itsource.cn','/images/head/avatar3.png',25,2),(81,'admin79','df2fc7fd6a88ab389a8997b4f979227f','amdin79@itsource.cn','/images/head/avatar3.png',25,2),(82,'admin80','917edbaba9dd839940f1ea6115b81fb4','amdin80@itsource.cn','/images/head/avatar3.png',25,2),(84,'admin82','23f556879c4d48b62685a4c74462ff20','amdin82@itsource.cn','/images/head/avatar3.png',25,3),(85,'admin83','278bb2a5264784023e10479286bf6ad1','amdin83@itsource.cn','/images/head/avatar3.png',25,1),(86,'admin84','4b646957feea88bd919786b53308e5fe','amdin84@itsource.cn','/images/head/avatar3.png',25,1),(87,'admin85','89e268a7194c186542a2462e9067803f','amdin85@itsource.cn','/images/head/avatar3.png',25,1),(88,'admin86','df286298bac9ae074263804dcc4b6746','amdin86@itsource.cn','/images/head/avatar3.png',25,2),(89,'admin87','efe3f582c67ad9c5990d684370b2091b','amdin87@itsource.cn','/images/head/avatar3.png',25,2),(90,'admin88','2442a32ec232ccdd526e1187ca8b8e63','amdin88@itsource.cn','/images/head/avatar3.png',25,2),(91,'admin89','9d02d47995b34e23466ea3f884123b92','amdin89@itsource.cn','/images/head/avatar3.png',25,1),(92,'admin90','361f59cad3911de5f5dc8c0eb0edc837','amdin90@itsource.cn','/images/head/avatar3.png',25,2),(94,'admin92','5a9e4a3970e5dfe918119678b219776f','amdin92@itsource.cn','/images/head/avatar3.png',25,1),(95,'admin93','0668938be4636952839cd7ac3327b710','amdin93@itsource.cn','/images/head/avatar3.png',25,1),(96,'admin94','b0515c6643d36d015d818bacabb3fa75','amdin94@itsource.cn','/images/head/avatar3.png',25,3),(97,'admin95','9fe8133f960ada5d89d04206e0d6cacf','amdin95@itsource.cn','/images/head/avatar3.png',25,1);

/*Table structure for table `employee_role` */

DROP TABLE IF EXISTS `employee_role`;

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  KEY `FK87184F674D26E00F` (`role_id`),
  KEY `FK87184F6710B1828F` (`employee_id`),
  CONSTRAINT `FK87184F6710B1828F` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK87184F674D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee_role` */

insert  into `employee_role`(`employee_id`,`role_id`) values (1,1),(2,2),(3,4);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`name`,`url`,`icon`,`parent_id`,`label`,`english_name`,`create_time`,`description`,`operator`) values (-1,'首页','employee/employee','ios-home-outline',0,'首页','Home',NULL,NULL,NULL),(1,'系统管理',NULL,'6.png',0,'系统管理',NULL,NULL,NULL,NULL),(2,'角色管理','person','md-alarm',1,'角色管理','person',NULL,NULL,NULL),(3,'菜单管理','menulist/menu_data','ios-menu-outline',1,'菜单管理','123',NULL,NULL,NULL),(4,'权限管理','/permission/index','6.png',1,'权限管理',NULL,NULL,NULL,NULL),(5,'导入管理','/import/index','ios-alarm-outline',1,'导入管理','english',NULL,'阿萨斯',NULL),(6,'组织机构',NULL,'ios-baseball',0,'组织机构','123',NULL,NULL,NULL),(7,'部门管理','/department/index','6.png',6,'部门管理',NULL,NULL,NULL,NULL),(8,'员工管理','/employee/index','friendgroup.png',6,'员工管理',NULL,NULL,NULL,NULL),(9,'基础数据',NULL,'md-albums',0,'基础数据','123',NULL,NULL,NULL),(10,'数据字典类型','/systemDictionaryType/index','6.png',9,'数据字典类型',NULL,NULL,NULL,NULL),(11,'数据字典明细','/systemDictionaryDetail/index','6.png',9,'数据字典明细',NULL,NULL,NULL,NULL),(12,'产品类型','/productType/index','6.png',9,'产品类型',NULL,NULL,NULL,NULL),(13,'产品管理','/product/index','6.png',9,'产品管理',NULL,NULL,NULL,NULL),(14,'供应商管理','/supplier/index','6.png',9,'供应商管理',NULL,NULL,NULL,NULL),(15,'采购模块',NULL,'ios-american-football-outline',0,'采购模块','123',NULL,NULL,NULL),(16,'采购管理','/purchaseBill/index','ios-baseball',15,'采购管理','13',NULL,NULL,NULL),(17,'采购报表','/purchaseBillItem/index','md-albums',15,'采购报表','englisg',NULL,NULL,NULL),(22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'权限设置','components/role/role','logo-android',0,'权限设置','english',NULL,'个会',NULL);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`url`,`descs`,`sn`,`menu_id`) values (1,'添加用户','/employee/save',NULL,'employee:save',NULL),(2,'删除用户','/employee/delete',NULL,'employee:delete',NULL),(3,'修改用户','/employee/update',NULL,'employee:update',NULL),(4,'员工管理','/employee/index',NULL,'employee:index',8),(5,'用户列表','/employee/page',NULL,'employee:page',NULL),(10,'角色管理','/role/index',NULL,'role:index',2),(11,'菜单管理','/menu/index',NULL,'menu:index',3),(12,'权限管理','/permission/index',NULL,'permission:index',4),(13,'导入管理','/import',NULL,'import:*',5),(14,'部门管理','/department/index',NULL,'department:index',7),(15,'数据字典类型','/systemDictionaryType/index',NULL,'systemDictionaryType:index',10),(16,'数据字典明细','/systemDictionaryDetail/index',NULL,'systemDictionaryDetail:index',11),(17,'产品类型','/productType/index',NULL,'productType:index',12),(18,'产品管理','/product/index',NULL,'product:index',13),(19,'供应商管理','/supplier/index',NULL,'supplier:index',14),(20,'采购管理','/purchaseBill/index',NULL,'purchaseBill:index',16),(21,'采购报表','/purchaseBillItem/index',NULL,'purchaseBillItem:index',17);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `smallPic` varchar(255) DEFAULT NULL,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `salePrice` decimal(19,2) DEFAULT NULL,
  `types_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  `brand_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C664CF8DF77FB5` (`types_id`),
  KEY `FK50C664CF422B987E` (`brand_id`),
  KEY `FK50C664CF329AED61` (`unit_id`),
  CONSTRAINT `FK50C664CF329AED61` FOREIGN KEY (`unit_id`) REFERENCES `systemdictionarydetail` (`id`),
  CONSTRAINT `FK50C664CF422B987E` FOREIGN KEY (`brand_id`) REFERENCES `systemdictionarydetail` (`id`),
  CONSTRAINT `FK50C664CF8DF77FB5` FOREIGN KEY (`types_id`) REFERENCES `producttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`color`,`pic`,`smallPic`,`costPrice`,`salePrice`,`types_id`,`unit_id`,`brand_id`) values (1,'产品1','red','/upload/20150327-21433174.png','/upload/20150327-21433174_small.png','1.00','1.00',2,3,1),(2,'产品2','green','/upload/20150327-214430762.png','/upload/20150327-214430762_small.png','2.00','2.00',7,4,2),(12,'sdf','red','/upload/20180605-155607227.png','/upload/20180605-155607227_small.png','23.00','34.00',3,3,1);

/*Table structure for table `productstock` */

DROP TABLE IF EXISTS `productstock`;

CREATE TABLE `productstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `incomeDate` datetime DEFAULT NULL,
  `warning` bit(1) DEFAULT NULL,
  `topNum` decimal(19,2) DEFAULT NULL,
  `bottomNum` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `depot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK459B4B879F064DC5` (`depot_id`),
  KEY `FK459B4B87D6A81925` (`product_id`),
  CONSTRAINT `FK459B4B879F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK459B4B87D6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `productstock` */

/*Table structure for table `producttype` */

DROP TABLE IF EXISTS `producttype`;

CREATE TABLE `producttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA8168A931A015E4` (`parent_id`),
  CONSTRAINT `FKA8168A931A015E4` FOREIGN KEY (`parent_id`) REFERENCES `producttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `producttype` */

insert  into `producttype`(`id`,`name`,`descs`,`parent_id`) values (1,'汽车','汽车',NULL),(2,'奥迪','奥迪',1),(3,'奔驰','奔驰',1),(4,'大众','大众',1),(5,'电视','电视',NULL),(6,'3D电视','3D电视',5),(7,'液晶电视','液晶电视',5);

/*Table structure for table `purchasebill` */

DROP TABLE IF EXISTS `purchasebill`;

CREATE TABLE `purchasebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNum` decimal(19,2) DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `auditorTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `inputUser_id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BD788C89FE0CD6A` (`buyer_id`),
  KEY `FK9BD788C83FF7A83F` (`auditor_id`),
  KEY `FK9BD788C8A902BD48` (`inputUser_id`),
  KEY `FK9BD788C812C245CF` (`supplier_id`),
  CONSTRAINT `FK9BD788C812C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK9BD788C83FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK9BD788C89FE0CD6A` FOREIGN KEY (`buyer_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK9BD788C8A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `purchasebill` */

insert  into `purchasebill`(`id`,`vdate`,`totalAmount`,`totalNum`,`inputTime`,`auditorTime`,`status`,`supplier_id`,`auditor_id`,`inputUser_id`,`buyer_id`) values (1,'2015-04-21 00:00:00','5.00','3.00','2015-04-21 21:01:12',NULL,0,1,NULL,1,1),(2,'2015-04-23 00:00:00','22.00','22.00','2015-04-21 21:01:27',NULL,1,1,NULL,1,1),(3,'2015-04-21 12:00:00','4.00','2.00','2015-04-21 21:01:39',NULL,-1,2,NULL,1,1),(11,'2018-06-08 00:00:00','16.00','8.00','2018-06-08 19:03:36',NULL,0,1,NULL,1,4),(12,'2018-06-07 00:00:00','98.00','7.00','2018-06-12 09:38:11',NULL,0,1,NULL,1,8),(16,'2018-07-02 00:00:00','990.00','30.00','2018-07-09 09:44:08',NULL,0,2,NULL,1,12),(19,'2018-07-04 00:00:00','266.00','33.00','2018-07-09 14:09:39',NULL,0,1,NULL,1,12),(22,'2018-10-18 00:00:00','74.00','3.00','2018-10-18 12:45:11',NULL,0,2,NULL,1,4);

/*Table structure for table `purchasebillitem` */

DROP TABLE IF EXISTS `purchasebillitem`;

CREATE TABLE `purchasebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5FF8D3FBD6A81925` (`product_id`),
  KEY `FK5FF8D3FB60931610` (`bill_id`),
  CONSTRAINT `FK5FF8D3FB60931610` FOREIGN KEY (`bill_id`) REFERENCES `purchasebill` (`id`),
  CONSTRAINT `FK5FF8D3FBD6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `purchasebillitem` */

insert  into `purchasebillitem`(`id`,`price`,`num`,`amount`,`descs`,`product_id`,`bill_id`) values (1,'1.00','1.00','1.00','1',1,1),(2,'2.00','2.00','4.00','',2,1),(3,'1.00','11.00','11.00','',1,2),(4,'1.00','11.00','11.00','',1,2),(5,'2.00','2.00','4.00','',2,3),(7,'2.00','3.00','6.00','',2,11),(8,'2.00','5.00','10.00','',2,11),(37,'2.00','3.00','6.00','',2,12),(38,'23.00','4.00','92.00','',12,12),(41,'33.00','30.00','990.00','',2,16),(45,'2.00','23.00','46.00','fsdfsdf',2,19),(46,'22.00','10.00','220.00','sdfaf',1,19),(53,'34.00','1.00','34.00','',1,22),(54,'20.00','2.00','40.00','',2,22);

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`url`,`descs`) values (1,'用户添加','/employee/save','用户添加'),(2,'用户页面访问','/employee/index','是否能够进入访问用户的页面'),(3,'用户分页数据','/employee/page','查询分页的数据'),(4,'用户修改','/employee/update','用户修改'),(5,'用户删除','/employee/delete','用户删除'),(6,'检查用户名','/employee/checkUsername','检查用户名');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sn` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`sn`) values (1,'超级管理员','admin'),(2,'角色管理员','guest'),(4,'人事部','renShiBu');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK1404278833B84B6F` (`menu_id`),
  KEY `FK140427884D26E00F` (`role_id`),
  CONSTRAINT `FK1404278833B84B6F` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK140427884D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`role_id`,`menu_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17);

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FKAEE599B74D26E00F` (`role_id`),
  KEY `FKAEE599B7C854068F` (`permission_id`),
  CONSTRAINT `FKAEE599B74D26E00F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKAEE599B7C854068F` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(2,10),(4,1),(4,3),(4,4),(4,5);

/*Table structure for table `stockincomebill` */

DROP TABLE IF EXISTS `stockincomebill`;

CREATE TABLE `stockincomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNum` decimal(19,2) DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `auditorTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `inputUser_id` bigint(20) NOT NULL,
  `keeper_id` bigint(20) NOT NULL,
  `depot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK54AC64E69F064DC5` (`depot_id`),
  KEY `FK54AC64E63FF7A83F` (`auditor_id`),
  KEY `FK54AC64E6A902BD48` (`inputUser_id`),
  KEY `FK54AC64E6725F67CB` (`keeper_id`),
  KEY `FK54AC64E612C245CF` (`supplier_id`),
  CONSTRAINT `FK54AC64E612C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK54AC64E63FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK54AC64E6725F67CB` FOREIGN KEY (`keeper_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK54AC64E69F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK54AC64E6A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stockincomebill` */

/*Table structure for table `stockincomebillitem` */

DROP TABLE IF EXISTS `stockincomebillitem`;

CREATE TABLE `stockincomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `num` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKACA67119D6A81925` (`product_id`),
  KEY `FKACA671192B5E3024` (`bill_id`),
  CONSTRAINT `FKACA671192B5E3024` FOREIGN KEY (`bill_id`) REFERENCES `stockincomebill` (`id`),
  CONSTRAINT `FKACA67119D6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stockincomebillitem` */

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`id`,`name`) values (1,'成都供应商'),(2,'东莞供应商');

/*Table structure for table `systemdictionarydetail` */

DROP TABLE IF EXISTS `systemdictionarydetail`;

CREATE TABLE `systemdictionarydetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `types_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81BC50F6718C93B5` (`types_id`),
  CONSTRAINT `FK81BC50F6718C93B5` FOREIGN KEY (`types_id`) REFERENCES `systemdictionarytype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `systemdictionarydetail` */

insert  into `systemdictionarydetail`(`id`,`name`,`types_id`) values (1,'七匹狼',1),(2,'耐克',1),(3,'条',2),(4,'斤',2);

/*Table structure for table `systemdictionarytype` */

DROP TABLE IF EXISTS `systemdictionarytype`;

CREATE TABLE `systemdictionarytype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sn` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `systemdictionarytype` */

insert  into `systemdictionarytype`(`id`,`sn`,`name`) values (1,'productBrand','产品品牌'),(2,'productUnit','产品单位');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
