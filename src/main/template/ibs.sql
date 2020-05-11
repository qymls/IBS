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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (1,'IT部'),(2,'采购部'),(3,'销售部'),(4,'测试部'),(5,'公关部'),(6,'仓管部');

/*Table structure for table `depot` */

DROP TABLE IF EXISTS `depot`;

CREATE TABLE `depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `maxCapacity` decimal(19,2) DEFAULT NULL,
  `currentCapacity` decimal(19,2) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `depot` */

insert  into `depot`(`id`,`name`,`maxCapacity`,`currentCapacity`,`totalAmount`) values (1,'成都仓库','10000.00','100.00','10000.00'),(2,'上海仓库','50.00','50.00','50.00'),(4,'四川仓库','10.00','10.00','10.00');

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
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`username`,`password`,`email`,`headImage`,`age`,`department_id`) values (1,'admin','7d4c3a269316a73ce7f3256cf5d550c4','admin@itsource.com','upload/1d8adf03-216b-46ac-abdd-8750b53c13dd.jpg',34,1),(4,'所得税的地方','d13c9ef1d689b13c0a371587237ff4a9','89','upload/44004f0e-233c-4204-aae4-ce39c4261be4.jpg',50,NULL),(8,'admin6','7d4c3a269316a73ce7f3256cf5d550c4','amdin6@itsource.cn','upload/47f6607a-01f2-471b-8e43-79d514f9b340.jpg',25,2),(12,'admin10','9f24c7b20df0acf018e33f4b1ff2c179','amdin10@itsource.cn','upload/4b4bd6a3-b645-4089-9db9-41dd7dbea4d9.png',25,2),(16,'admin14','78d1e23998d09bc7eea88eb133d7d883','amdin14@itsource.cn','upload/74cf631c-f3ec-4b78-b286-9cc95221ff16.jpg',30,6),(17,'admin15','2ed5fc96326e6e4498135120130054dd','amdin15@itsource.cn',NULL,25,3),(18,'admin16','a170379d4afd16c64ce3ec796e4fdc63','amdin16@itsource.cn','upload/923636a9-7378-4a5a-9aa4-3e428ec12bcc.jpg',25,6),(19,'admin17','70db381796b7996921169f3a4ee8314c','amdin17@itsource.cn','',25,6),(20,'admin18','bfdb4f55fd6c2fc5587e0b1e37e005c9','amdin18@itsource.cn','',25,1),(21,'admin19','3be505720e71fdf7d1aa0342dae23ebc','amdin19@itsource.cn','',25,6),(22,'admin20','16b4d935562ab32fc857a0e2283cbc0b','amdin20@itsource.cn',NULL,25,3),(23,'admin21','2ce747b365a0be43476b2d5d9354b749','amdin21@itsource.cn',NULL,25,3),(24,'admin22','d3b7616f5fc55cdd6b02cd8a9c1e0c49','amdin22@itsource.cn',NULL,25,3),(25,'admin23','b3dd756cbe90b3604558cdafdb8c1261','amdin23@itsource.cn',NULL,25,3),(26,'admin24','e3c2f8686d1b1c5d990085c5b90dbfe9','amdin24@itsource.cn','',25,6),(27,'admin25','7d0aa4f802a9b02900d2a8ded2675a9c','amdin25@itsource.cn',NULL,25,1),(28,'admin26','61b8da8bcb8de58a086c784c7558a805','amdin26@itsource.cn',NULL,25,3),(29,'admin27','706fa3c715eec3741909e282fe04ff25','amdin27@itsource.cn',NULL,25,1),(30,'admin28','76d3cc8844dd6ee1b1b8314eaab706e0','amdin28@itsource.cn',NULL,25,3),(31,'admin29','2703317cc9dd372b08097f9f18a0e0a4','amdin29@itsource.cn',NULL,25,1),(32,'admin30','c0ffecaddfa40beac7372a65be5d71bf','amdin30@itsource.cn',NULL,25,1),(33,'admin31','aa6901b6d24598dcc7946c9d6342bba5','amdin31@itsource.cn',NULL,25,2),(34,'admin32','ad4601f0f2fbe9fdddea7f1a71bc3471','amdin32@itsource.cn',NULL,25,3),(35,'admin33','b5ec639f66813c9f77b00983cae4d7fb','amdin33@itsource.cn',NULL,25,2),(36,'admin34','9909b8817e2f934d5deaebd6b4f40b64','amdin34@itsource.cn',NULL,25,2),(37,'admin35','2450fff7419973ffd2964315ed58a339','amdin35@itsource.cn',NULL,25,3),(38,'admin36','e60f5c4994c20149119b1d383f5be3bc','amdin36@itsource.cn',NULL,25,1),(39,'admin37','005e22d11fd7b5de965de1adeb06a914','amdin37@itsource.cn',NULL,25,2),(40,'admin38','b83b26789672dd5456866c9bac0c5002','amdin38@itsource.cn',NULL,25,3),(41,'admin39','3bc606eb57cbd97a21ce49a295f88e0b','amdin39@itsource.cn',NULL,25,2),(42,'admin40','699351895ec677822092144ee31107f6','amdin40@itsource.cn',NULL,25,3),(43,'admin41','b7bf9773c901be56d1b76b2f4a3b4437','amdin41@itsource.cn',NULL,25,1),(44,'admin42','6b5a4a649020e644b3736042459e8e66','amdin42@itsource.cn',NULL,25,1),(45,'admin43','ee8a7873dc6543f6279f40ab399e12c6','amdin43@itsource.cn',NULL,25,3),(46,'admin44','c485d70f82f1643000c4672159c4651a','amdin44@itsource.cn',NULL,25,1),(47,'admin45','70efbeea1267f49ede4b6c10de92dcc9','amdin45@itsource.cn',NULL,25,1),(48,'admin46','acac91e41e3f7c9449cac7b48cc121c6','amdin46@itsource.cn',NULL,25,1),(49,'admin47','6ca6f13355663f758f2f0aac5d6fa24c','amdin47@itsource.cn',NULL,25,1),(50,'admin48','755a2afbe8f44f103a9b5f7c0e364fd4','amdin48@itsource.cn',NULL,25,1),(51,'admin49','6dcbb4ef6755721c29619426434659f1','amdin49@itsource.cn',NULL,25,3),(52,'admin50','2518a553792f3b70f960586ab2528c65','amdin50@itsource.cn',NULL,25,3),(53,'admin51','da9e192aa38c72ea63a92d565cfbffe1','amdin51@itsource.cn',NULL,25,1),(54,'admin52','1322b1e1e896e181538f0c8f8cb3e525','amdin52@itsource.cn',NULL,25,1),(55,'admin53','d520a2a10cb50e76a0025da4bb1ad2f3','amdin53@itsource.cn',NULL,25,2),(56,'admin54','ff77972b4b347439c54f214983f5a8b6','amdin54@itsource.cn',NULL,25,1),(57,'admin55','7d1d7c377455611629f4ae5fcc0ea910','amdin55@itsource.cn',NULL,25,3),(58,'admin56','33e56f2e772c1102165b11f87010e1fb','amdin56@itsource.cn',NULL,25,2),(59,'admin57','e72ee8a1d066cdc1625684259aa7837a','amdin57@itsource.cn',NULL,25,3),(60,'admin58','a508d25a8b2fb7fa6385a277f05df4fb','amdin58@itsource.cn',NULL,25,3),(61,'admin59','36cd68572086b16086cb9ef1853f8b88','amdin59@itsource.cn',NULL,25,2),(62,'admin60','57fe862338f95503209a1ccc3fa02d59','amdin60@itsource.cn',NULL,25,1),(63,'admin61','961004a8c032768124199c7e3ecc1fc6','amdin61@itsource.cn',NULL,25,2),(64,'admin62','5927a58518e6f214bf70aa3298afa183','amdin62@itsource.cn',NULL,25,3),(65,'admin63','70a3529005913ea2cd9ad83248f03d8d','amdin63@itsource.cn',NULL,25,3),(66,'admin64','72bffcfe2efcf3206287dc529a1564c7','amdin64@itsource.cn',NULL,25,2),(67,'admin65','9ca9d74eca05335cab7d97a5cb61cccc','amdin65@itsource.cn',NULL,25,3),(68,'admin66','cfc4cdbb8e7b9cc8d480e57210571ebe','amdin66@itsource.cn',NULL,25,1),(69,'admin67','bd184c1fee3bcd92f44ec505c69cc1ee','amdin67@itsource.cn',NULL,25,3),(70,'admin68','d0453893baa69b7b70ddffa7da801633','amdin68@itsource.cn',NULL,25,1),(71,'admin69','978fce0383c707ade4d1c55b49081b12','amdin69@itsource.cn',NULL,25,2),(72,'admin70','166d99542d9967351ac46bf8edf3b827','amdin70@itsource.cn',NULL,25,1),(73,'admin71','d49f81b593dc4b0a53c2613fcfecd6a5','amdin71@itsource.cn',NULL,25,1),(74,'admin72','5c824b3ff6e34eaf899194f5432face4','amdin72@itsource.cn',NULL,25,3),(75,'admin73','3db19c39040280dea6197fcff49267e4','amdin73@itsource.cn',NULL,25,2),(76,'admin74','6d174c68b5f98375fc6562a20f3630c5','amdin74@itsource.cn',NULL,25,2),(77,'admin75','c98dfa5d077cabe15af93e7a3fd29084','amdin75@itsource.cn',NULL,25,2),(78,'admin76','ef1377f6dcae451b797778ef873347fa','amdin76@itsource.cn',NULL,25,3),(79,'admin77','fa75ba41bc620173a248be91a260ac22','amdin77@itsource.cn',NULL,25,3),(80,'admin78','0e2631ca77b189a0e8129f1492b6f3f0','amdin78@itsource.cn',NULL,25,2),(82,'admin80','9bceabf71a0f32ff741a4a89b18399d4','amdin80@itsource.cn',NULL,25,2),(84,'admin82','3dff6323a466105ac8bea59b0b19163c','amdin82@itsource.cn',NULL,25,2),(85,'烦得很','放到个','123',NULL,123,3),(86,'电饭锅','和规范化','规范好',NULL,20,NULL),(123,'admin999','1558f8e1db4aee13afb676e4d510fa02','admin@itsource.com','upload/e944fdf1-2d59-4c31-8f61-b62f388cf0e6.jpg',34,1),(124,'admin222','655073ae9d2fb73e4bf782f185a543cb','roleAdmin@itsource.cn',NULL,25,1),(125,'admin1aaaa2','5e634ab89c18785cf051152691d62e55','amdin1@itsource.cn',NULL,25,1),(126,'admin88','c3c5fa16e8403b0dcb4a0c3a5689e010','amdin2@itsource.cn',NULL,100,2),(127,'admin999','1558f8e1db4aee13afb676e4d510fa02','admin@itsource.com',NULL,34,1),(128,'admin222','655073ae9d2fb73e4bf782f185a543cb','roleAdmin@itsource.cn',NULL,25,1),(129,'admin1aaaa2','5e634ab89c18785cf051152691d62e55','amdin1@itsource.cn',NULL,25,1),(130,'admin88','c3c5fa16e8403b0dcb4a0c3a5689e010','amdin2@itsource.cn',NULL,100,2),(131,'admin999','1558f8e1db4aee13afb676e4d510fa02','admin@itsource.com',NULL,34,1),(132,'admin222','655073ae9d2fb73e4bf782f185a543cb','roleAdmin@itsource.cn',NULL,25,1),(133,'admin1aaaa2','5e634ab89c18785cf051152691d62e55','amdin1@itsource.cn',NULL,25,1),(134,'admin88','c3c5fa16e8403b0dcb4a0c3a5689e010','amdin2@itsource.cn','upload/0321e279-fd00-4e80-a61b-5e022df60d7c.jpg',100,2),(135,'admin999','1558f8e1db4aee13afb676e4d510fa02','admin@itsource.com',NULL,34,1),(136,'admin222','655073ae9d2fb73e4bf782f185a543cb','roleAdmin@itsource.cn','upload/664e4667-fb90-4363-b901-c386390d7d3c.png',25,1),(137,'admin1aaaa2','5e634ab89c18785cf051152691d62e55','amdin1@itsource.cn',NULL,25,1),(138,'admin88','c3c5fa16e8403b0dcb4a0c3a5689e010','amdin2@itsource.cn','upload/cc69c088-5be0-4ab9-95d3-d5a6b8ccc01b.png',100,2),(139,'admin999','1558f8e1db4aee13afb676e4d510fa02','admin@itsource.com',NULL,34,1),(140,'admin222','655073ae9d2fb73e4bf782f185a543cb','roleAdmin@itsource.cn',NULL,25,1),(141,'admin1aaaa2','5e634ab89c18785cf051152691d62e55','amdin1@itsource.cn',NULL,25,1),(142,'admin88','c3c5fa16e8403b0dcb4a0c3a5689e010','amdin2@itsource.cn',NULL,100,2),(143,'admin222256','082e4679b006602101edc8476a48d244','amdin1@itsource.cn',NULL,25,NULL),(144,'admin2222569999','e48934506f9e42c782abe6cd69cf50bd','amdin1@itsource.cn',NULL,25,NULL),(145,'admin222123322','1864b61a43ecb902940c8ed2481a1dfe','roleAdmin@itsource.cn',NULL,25,1),(146,'admin22212ll','6877406d56162f228c88b5a65e3206f8','roleAdmin@itsource.cn',NULL,25,1),(147,'admin2222oo','0bc1ab4d4fe9356f6e95a7a3274dce74','amdin1@itsource.cn',NULL,25,NULL),(148,'admin999p','77ee0eb601ac33ec13682f11a9ed4d06','admin@itsource.com',NULL,34,1),(149,'admin22212llp','ce8d93567f2f58694d175d81446d83a5','roleAdmin@itsource.cn',NULL,25,1),(150,'admin2222oop','ebef67204f3c18e8b82600e431c38e63','amdin1@itsource.cn',NULL,25,NULL),(151,'admin8899p','c5c14dd4ec43b5d57975d494da9ef8a5','amdin2@itsource.cn',NULL,30,2),(152,'admin999p89','61bfb7dbc54c16c453eb97475f569202','admin@itsource.com',NULL,34,1),(153,'admin22212llpo','0bb61429ee6cc325a02b5fc0bf338e64','roleAdmin@itsource.cn',NULL,25,1),(154,'admin2222oop89','98d96ef48c1eb67e602eb74969fb1303','amdin1@itsource.cn',NULL,25,NULL),(155,'admin8899p88','b7370ff020c16f97e853548ae0206c65','amdin2@itsource.cn',NULL,30,2),(156,'admin2222oop89kk','6c44f1907d25cd284bec589c05845edd','amdin1@itsource.cn',NULL,25,NULL),(157,'admin8899p8ll8','1c1034781dd4026ab6642ca499e5466c','amdin2@itsource.cn',NULL,30,2),(158,'admin22228oop89kk','79ee96c285546c2a1d7d5e685d88b4a0','amdin1@itsource.cn',NULL,25,NULL),(159,'admin88899p8ll8','5d2705b620f81d4679857a0eef481088','amdin2@itsource.cn',NULL,30,2),(160,'admin2222方法8oop89kk','0a277dd658388360bd23e1373a924e4d','amdin1@itsource.cn',NULL,25,NULL),(161,'admin88方法899p8ll8','249689b298764d06e82237965dfdeb5f','amdin2@itsource.cn',NULL,30,2),(162,'admin22得到22方法8oop89kk','f43ed4f02ece68ce0fb18d250b3defb3','amdin1@itsource.cn',NULL,25,NULL),(163,'覆盖','b4654e106a6ecb4bda8d6b3ef2331b9f','amdin2@itsource.cn',NULL,30,2),(164,'admin地方22得到22方法8oop89kk','475dfb50e7f5c68eb1f443a1a84e26ce','amdin1@itsource.cn',NULL,25,NULL),(165,'覆盖地方','27d341871521f4cb370b0e874fd237c6','amdin2@itsource.cn',NULL,30,2),(166,'admin地覆盖方22得到22方法8oop89kk','b699a6e6eca92e8e78c924e805f4a9ad','amdin1@itsource.cn',NULL,25,NULL),(167,'是的','0b2d41dfa0ab0a67f099262ecbcb9c89','amdin2@itsource.cn',NULL,30,2),(168,'admi非n地覆盖方22得到22方法8oop89kk','de2e1522eaa001543c8900e83fad2f4f','amdin1@itsource.cn',NULL,25,NULL),(169,'是的非','40fb2c67499aee3cfae87ea5e10883df','amdin2@itsource.cn',NULL,30,2),(170,'admin999p1','9116541fe22e4c8478f1a4399f02e40e','admin@itsource.com',NULL,34,1),(171,'admin22212ll1p','093436db0e8e222877f93a23a9a62694','roleAdmin@itsource.cn',NULL,25,1),(172,'admin2222oop1','d8e5827e978880a9cb8251334b980a97','amdin1@itsource.cn',NULL,25,NULL),(173,'admin8899p1','08a6df0fe38bdf33ecfebd65f92a4d7d','amdin2@itsource.cn',NULL,30,2),(174,'admin999p非1','b22cf843c582b9c2d87c7baf3e573cb8','admin@itsource.com',NULL,34,1),(175,'admin2非2212ll1p','5e5008e28452085a3e55a8089d495209','roleAdmin@itsource.cn',NULL,25,1),(176,'admin22非22oop1','e960fd60f468851212b8fd3fd6d15634','amdin1@itsource.cn',NULL,25,NULL),(177,'非','0cead9c82876dbef139793e66c2ca4a5','amdin2@itsource.cn',NULL,30,2),(178,'admin1999p非1','c9ec4c166587334f08ea47f1eddbe19b','admin@itsource.com',NULL,34,1),(179,'admin2非12212ll1p','9e224efd0636cfdb04c50c7b5f012c13','roleAdmin@itsource.cn',NULL,25,1),(180,'admin22非122oop1','bc21380e4c56f438f4f353d428d654f7','amdin1@itsource.cn',NULL,25,NULL),(181,'非辅导费','bdb0efad00fdd7d2a15b86b7353e4975','amdin2@itsource.cn',NULL,30,2),(182,'admin22212llp2','90f67255364ba3e3a86dd56e68a60975','roleAdmin@itsource.cn',NULL,25,1),(183,'admin2222oop3','a9e7d3969fb7b4945f965b53aa24f936','amdin1@itsource.cn',NULL,25,NULL),(184,'admin8899p2','108816f5dfc70dcc70bab4d69f3486a7','amdin2@itsource.cn',NULL,30,2),(185,'admin999p1就','2eb934a737680d82fdb991eb1cafc7a4','admin@itsource.com',NULL,34,1),(186,'admin999pj','ba3710e3dc630af40474649c673703c9','admin@itsource.com',NULL,34,1),(187,'admin22212llpg','a77633cd578bed2589a95ab2bd125665','roleAdmin@itsource.cn',NULL,25,1),(188,'admin2222oopg','e58dc9c16d81b33dc273b39c6b86c80c','amdin1@itsource.cn',NULL,25,NULL),(189,'admin8899ph','6cda818017d267bd4595a24c4311b858','amdin2@itsource.cn',NULL,30,2),(190,'admin999pu','080b765d3e84f0471d9bd0eae8ca509c','admin@itsource.com',NULL,34,1),(191,'admin22212lulp','b59638b82ea3caffd8a7c8e8703bc5b1','roleAdmin@itsource.cn',NULL,25,1),(192,'admin8899pu','dd90ab112d21684cf4878c5cef800030','amdin2@itsource.cn',NULL,30,2),(193,'admin999p是','82bfdf4fd9741ba0c0d7f18de9c26390','admin@itsource.com',NULL,34,1),(194,'admin22212llp是','5b2b977370183a5c7f1e3e4648bcae13','roleAdmin@itsource.cn',NULL,25,1),(195,'admin2222oop是','5876486a67c284489d8efab50d4b8ea1','amdin1@itsource.cn',NULL,25,NULL),(196,'admin999p1好','ae819e3af078a2054d695086b060b75b','admin@itsource.com',NULL,34,1),(197,'admin2222oop地方','8fe9ab3b5c68a213c2f4c1b8fb31ad80','amdin1@itsource.cn',NULL,25,NULL),(198,'admin8899p地方','794c4a2c740042c6ab7a1cd59aa81850','amdin2@itsource.cn',NULL,50,2),(199,'admin22212llpsds','afff0f4229c7b3da63586a04f88136ec','roleAdmin@itsource.cn',NULL,25,1),(200,'admin2222oopsdsd','7ca1acf0e4f89ef72973878c25020292','amdin1@itsource.cn',NULL,25,NULL),(201,'admin8899psds','71ec77fb11d6a428c77f37af47be46f7','amdin2@itsource.cn',NULL,50,2),(202,'admin999p放到','846faeb97ec43d7f5f4c9f7215329301','admin@itsource.com',NULL,34,1),(203,'admin2222oo地方p','cf7666e60caa038f28d5276430bf8ac3','amdin1@itsource.cn',NULL,25,NULL),(204,'admin99的9p','851ed462d555cd3bcbde7bf59b22484f','admin@itsource.com',NULL,34,1),(205,'admin222第三方2oop','03a14dc8766dd71f73b03b5f18b913e4','amdin1@itsource.cn',NULL,25,1),(206,'admin222水电费12llp','1248698d563d208ad5c63dc58d723abd','roleAdmin@itsource.cn',NULL,25,2),(207,'admin889的方式9p','bfae0305c5d10ffc2ec8625a840246c6','amdin2@itsource.cn',NULL,50,2),(208,'admin2222oop的','4a2edceb1a42387825f231dfa9ccdb52','amdin1@itsource.cn',NULL,25,NULL),(209,'admin8899p的','403a8f7712eefce104024f26bf25197f','amdin2@itsource.cn',NULL,30,2),(210,'admin999p的','51bd50d04f0711d4572fb49e83a7a97e','admin@itsource.com',NULL,30,1),(211,'admin22212ll的p','4cb536580d4cdc9dee221a872fb0e843','roleAdmin@itsource.cn',NULL,30,1),(212,'admin99第三方9p','81ee0f7e58ddfd61895c4572337b5008','admin@itsource.com',NULL,34,1),(213,'admin2地方2212llp','2914c25659e352a60fec836db18d9552','roleAdmin@itsource.cn',NULL,25,1),(214,'admin2地方222oop','1b2b557c7edebabdbc6dec887f579106','amdin1@itsource.cn',NULL,25,NULL),(215,'admin8的非899p','ffd0f50d2ab0d9c20856248051cc1c1c','amdin2@itsource.cn',NULL,30,2),(216,'admin2221萨达2llp','efccd9afc64bc53717110f581fd682e6','roleAdmin@itsource.cn',NULL,25,1),(217,'admin222萨达2oop','79e0719e0d5864046c0a6704310f20ae','amdin1@itsource.cn',NULL,25,NULL),(218,'admin999p萨达','296cab8904435b57452089f1dd137b4a','admin@itsource.com',NULL,34,2),(219,'admin889水电费9p','fd8834d2837149c43502267deada54ec','amdin2@itsource.cn',NULL,50,2);

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

insert  into `employee_role`(`employee_id`,`role_id`) values (1,1),(16,1),(8,4),(8,6);

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
  `firstmenuid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24897F76799055` (`parent_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`name`,`url`,`icon`,`parent_id`,`label`,`english_name`,`create_time`,`description`,`operator`,`firstmenuid`) values (-1,'首页','Admin/Index/index','ios-home-outline',NULL,'首页','Home page',NULL,NULL,NULL,0),(1,'系统管理','','ios-leaf-outline',NULL,'系统管理','System management',NULL,'',NULL,0),(2,'角色管理','Admin/Role/index','md-alarm',1,'角色管理','Role management',NULL,'角色菜单',NULL,NULL),(3,'菜单管理','Admin/Menu/index','ios-menu-outline',1,'菜单管理','Menu management',NULL,NULL,NULL,NULL),(4,'权限管理','Admin/Permission/index','ios-baseball-outline',1,'权限管理','Rights management',NULL,NULL,NULL,NULL),(5,'导入管理','Admin/Import/index','ios-alarm-outline',1,'导入管理','Import management',NULL,'',NULL,NULL),(6,'组织机构','','ios-baseball',NULL,'组织机构','Organization',NULL,'',NULL,0),(7,'部门管理','Admin/Department/index','ios-cog-outline',6,'部门管理','Department management',NULL,NULL,NULL,NULL),(8,'员工管理','Admin/Employee/index','logo-designernews',6,'员工管理','Employee management',NULL,NULL,NULL,NULL),(9,'基础数据',NULL,'md-albums',NULL,'基础数据','Basic data',NULL,NULL,NULL,0),(10,'数据字典类型','Admin/Systemdictionarytype/index','logo-android',9,'数据字典类型','Data dictionary type',NULL,'',NULL,NULL),(11,'数据字典明细','Admin/Systemdictionarydetail/index','md-archive',9,'数据字典明细','Data dictionary details',NULL,NULL,NULL,NULL),(12,'产品类型','Admin/Producttype/index','ios-boat',9,'产品类型','Product type',NULL,NULL,NULL,NULL),(13,'产品管理','Admin/Product/index','ios-cloudy-night',9,'产品管理','Product management ',NULL,NULL,NULL,NULL),(14,'供应商管理','Admin/Supplier/index','md-hand',9,'供应商管理','Supplier management',NULL,'',NULL,NULL),(15,'采购模块','','ios-american-football-outline',NULL,'采购模块','Purchasing module',NULL,'',NULL,0),(16,'采购管理','Admin/Purchasebill/index','ios-baseball',15,'采购管理','Purchasing management ',NULL,NULL,NULL,NULL),(17,'采购报表','Admin/Purchasebillitem/index','md-albums',15,'采购报表','Purchase report',NULL,NULL,NULL,NULL),(49,'测试管理','','ios-alarm-outline',NULL,'测试管理','Test management','2020-05-05 00:54:40','','admin',0),(50,'测试1-1','','ios-american-football-outline',49,'测试1-1','Test 1-1','2020-05-05 00:54:53','','admin',NULL),(51,'测试1-2','','md-american-football',49,'测试1-2','Tests 1-2','2020-05-05 00:55:04','','admin',NULL),(52,'测试1-3','','ios-alert',49,'测试1-3','Tests 1-3','2020-05-05 00:55:16','','admin',NULL),(53,'测试1-2-1','','ios-american-football',51,'测试1-2-1','Test 1-2-1','2020-05-05 00:55:32','','admin',NULL),(54,'测试1-2-2','','md-analytics',51,'测试1-2-2','Test 1-2-2','2020-05-05 00:55:40','','admin',NULL),(55,'测试1-2-3','','ios-archive',51,'测试1-2-3','Test 1-2-3','2020-05-05 00:55:53','','admin',NULL),(56,'测试1-2-2-1','','ios-analytics',54,'测试1-2-2-1','Test 1-2-2-1','2020-05-05 00:56:06','','admin',NULL),(57,'测试-1-2-2-1-1','989898989','logo-angular',56,'测试-1-2-2-1-1','Test-1-2-2-1-1','2020-05-05 00:56:22','','admin',NULL),(58,'测试1-2-2-1-2','999999','ios-basketball-outline',56,'测试1-2-2-1-2','Test 1-2-2-1-2','2020-05-05 00:58:08','','admin',NULL),(59,'入库管理','','ios-bonfire',NULL,'入库管理','Warehousing management','2020-05-09 14:26:19','','admin',0),(60,'仓库管理','Admin/Depot/index','md-filing',59,'仓库管理','Warehouse management','2020-05-09 14:27:05','','admin',NULL),(61,'库存管理','Admin/Productstock/index','md-sunny',59,'库存管理','Inventory management','2020-05-09 14:29:38','','admin',NULL),(62,'入库单管理','Admin/Stockincomebill/index','md-star-outline',59,'入库单管理','Warehouse entry management','2020-05-09 14:32:01','','admin',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`url`,`descs`,`sn`,`menu_id`) values (1,'首页页面权限','/Admin/Index/index',NULL,'index:index',-1),(2,'角色管理页面权限','/Admin/Role/index',NULL,'role:index',2),(3,'菜单管理页面权限','/Admin/Menu/index',NULL,'menu:index',3),(4,'权限管理页面权限','/Admin/Permission/index',NULL,'permission:index',4),(5,'导入管理页面权限','/Admin/Import/index',NULL,'import:index',5),(6,'部门管理页面权限','/Admin/Department/index',NULL,'department:index',7),(7,'员工管理页面权限','/Admin/Employee/index',NULL,'employee:index',8),(8,'数据字典类型页面权限','/Admin/Systemdictionarytype/index',NULL,'systemdictionarytype:index',10),(9,'数据字典明细页面权限','/Admin/Systemdictionarydetail/index',NULL,'systemdictionarydetail:index',11),(10,'产品类型页面权限','/Admin/Producttype/index',NULL,'producttype:index',12),(11,'产品管理页面权限','/Admin/Product/index',NULL,'product:index',13),(12,'供应商管理页面权限','/Admin/Supplier/index',NULL,'supplier:index',14),(13,'采购管理页面权限','/Admin/Purchasebill/index',NULL,'purchasebill:index',16),(14,'采购报表页面权限','/Admin/Purchasebillitem/index',NULL,'purchasebillitem:index',17),(15,'仓库管理页面权限','/Admin/Depot/index',NULL,'depot:index',60),(16,'库存管理页面权限','/Admin/Productstock/index',NULL,'productstock:index',61),(17,'入库单管理页面权限','/Admin/Stockincomebill/index',NULL,'stockincomebill:index',62),(22,'角色管理列表权限','/Admin/Role/findAll',NULL,'role:findAll',2),(23,'角色管理修改权限','/Admin/Role/update',NULL,'role:update',2),(24,'角色管理删除权限','/Admin/Role/delete',NULL,'role:delete',2),(25,'角色管理保存权限','/Admin/Role/save',NULL,'role:save',2),(26,'菜单管理列表权限','/Admin/Menu/findAll',NULL,'menu:findAll',3),(27,'菜单管理修改权限','/Admin/Menu/update',NULL,'menu:update',3),(28,'菜单管理保存权限','/Admin/Menu/save',NULL,'menu:save',3),(29,'菜单管理删除权限','/Admin/Menu/delete',NULL,'menu:delete',3),(34,'权限管理列表权限','/Admin/Permission/findAll',NULL,'permission:findAll',4),(35,'权限管理保存权限','/Admin/Permission/save',NULL,'permission:save',4),(36,'权限管理修改权限','/Admin/Permission/update',NULL,'permission:update',4),(37,'权限管理删除权限','/Admin/Permission/delete',NULL,'permission:delete',4),(38,'导入管理修改权限','/Admin/Import/update',NULL,'import:update',5),(39,'导入管理删除权限','/Admin/Import/delete',NULL,'import:delete',5),(40,'导入管理列表权限','/Admin/Import/findAll',NULL,'import:findAll',5),(41,'导入管理保存权限','/Admin/Import/save',NULL,'import:save',5),(42,'部门管理列表权限','/Admin/Department/findAll',NULL,'department:findAll',7),(43,'部门管理删除权限','/Admin/Department/delete',NULL,'department:delete',7),(44,'部门管理保存权限','/Admin/Department/save',NULL,'department:save',7),(45,'部门管理修改权限','/Admin/Department/update',NULL,'department:update',7),(50,'员工管理列表权限','/Admin/Employee/findAll',NULL,'employee:findAll',8),(51,'员工管理修改权限','/Admin/Employee/update',NULL,'employee:update',8),(52,'员工管理删除权限','/Admin/Employee/delete',NULL,'employee:delete',8),(53,'员工管理保存权限','/Admin/Employee/save',NULL,'employee:save',8),(74,'数据字典类型列表权限','/Admin/Systemdictionarytype/findAll',NULL,'systemdictionarytype:findAll',10),(75,'数据字典类型修改权限','/Admin/Systemdictionarytype/update',NULL,'systemdictionarytype:update',10),(76,'数据字典类型删除权限','/Admin/Systemdictionarytype/delete',NULL,'systemdictionarytype:delete',10),(77,'数据字典类型保存权限','/Admin/Systemdictionarytype/save',NULL,'systemdictionarytype:save',10),(78,'数据字典明细列表权限','/Admin/Systemdictionarydetail/findAll',NULL,'systemdictionarydetail:findAll',11),(79,'数据字典明细修改权限','/Admin/Systemdictionarydetail/update',NULL,'systemdictionarydetail:update',11),(80,'数据字典明细删除权限','/Admin/Systemdictionarydetail/delete',NULL,'systemdictionarydetail:delete',11),(81,'数据字典明细保存权限','/Admin/Systemdictionarydetail/save',NULL,'systemdictionarydetail:save',11),(82,'产品类型修改权限','/Admin/Producttype/update',NULL,'producttype:update',12),(83,'产品类型删除权限','/Admin/Producttype/delete',NULL,'producttype:delete',12),(84,'产品类型保存权限','/Admin/Producttype/save',NULL,'producttype:save',12),(85,'产品类型列表权限','/Admin/Producttype/findAll',NULL,'producttype:findAll',12),(86,'产品管理列表权限','/Admin/Product/findAll',NULL,'product:findAll',13),(87,'产品管理修改权限','/Admin/Product/update',NULL,'product:update',13),(88,'产品管理删除权限','/Admin/Product/delete',NULL,'product:delete',13),(89,'产品管理保存权限','/Admin/Product/save',NULL,'product:save',13),(90,'供应商管理列表权限','/Admin/Supplier/findAll',NULL,'supplier:findAll',14),(91,'供应商管理修改权限','/Admin/Supplier/update',NULL,'supplier:update',14),(92,'供应商管理删除权限','/Admin/Supplier/delete',NULL,'supplier:delete',14),(93,'供应商管理保存权限','/Admin/Supplier/save',NULL,'supplier:save',14),(94,'采购管理修改权限','/Admin/Purchasebill/update',NULL,'purchasebill:update',16),(95,'采购管理删除权限','/Admin/Purchasebill/delete',NULL,'purchasebill:delete',16),(96,'采购管理列表权限','/Admin/Purchasebill/findAll',NULL,'purchasebill:findAll',16),(97,'采购管理保存权限','/Admin/Purchasebill/save',NULL,'purchasebill:save',16),(98,'采购报表列表权限','/Admin/Purchasebillitem/findAll',NULL,'purchasebillitem:findAll',17),(99,'采购报表修改权限','/Admin/Purchasebillitem/update',NULL,'purchasebillitem:update',17),(100,'采购报表删除权限','/Admin/Purchasebillitem/delete',NULL,'purchasebillitem:delete',17),(101,'采购报表保存权限','/Admin/Purchasebillitem/save',NULL,'purchasebillitem:save',17),(145,'仓库管理列表权限','/Admin/Depot/findAll',NULL,'depot:findAll',60),(146,'仓库管理删除权限','/Admin/Depot/delete',NULL,'depot:delete',60),(147,'仓库管理保存权限','/Admin/Depot/save',NULL,'depot:save',60),(148,'仓库管理修改权限','/Admin/Depot/update',NULL,'depot:update',60),(149,'库存管理列表权限','/Admin/Productstock/findAll',NULL,'productstock:findAll',61),(150,'库存管理修改权限','/Admin/Productstock/update',NULL,'productstock:update',61),(151,'库存管理删除权限','/Admin/Productstock/delete',NULL,'productstock:delete',61),(152,'库存管理保存权限','/Admin/Productstock/save',NULL,'productstock:save',61),(153,'入库单管理列表权限','/Admin/Stockincomebill/findAll',NULL,'stockincomebill:findAll',62),(154,'入库单管理保存权限','/Admin/Stockincomebill/save',NULL,'stockincomebill:save',62),(155,'入库单管理修改权限','/Admin/Stockincomebill/update',NULL,'stockincomebill:update',62),(156,'入库单管理删除权限','/Admin/Stockincomebill/delete',NULL,'stockincomebill:delete',62),(157,'菜单管理列表权限(2)','/Admin/Menu/findAllMenu','','menu:findAllMenu',3),(158,'产品类型列表权限(2)','/Admin/Producttype/findAllByPage','','producttype:findAllByPage',12);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`color`,`pic`,`smallPic`,`costPrice`,`salePrice`,`types_id`,`unit_id`,`brand_id`) values (1,'产品1','#5D125A','','/upload/20150327-21433174_small.png','1.00','1.00',3,3,1),(2,'产品2','#E63518','upload/6fcee951-17b5-4608-a999-45cf8772df01.jpg','/upload/20150327-214430762_small.png','2.00','2.00',16,4,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `productstock` */

insert  into `productstock`(`id`,`num`,`amount`,`price`,`incomeDate`,`warning`,`topNum`,`bottomNum`,`product_id`,`depot_id`) values (1,'99.88','8888.32','88.99','2020-05-10 09:32:10','\0','100.00','50.00',1,2),(10,'800.00','160000.00','200.00','2020-05-10 09:49:35','','1000.00','5000.00',2,2),(13,'600.00','300000.00','500.00','2020-05-10 09:50:07','','100.00','50.00',2,4),(14,'800.00','360000.00','450.00','2020-05-10 10:02:49','\0','100.00','50.00',1,4),(15,'600.00','210000.00','350.00','2020-05-10 10:02:37','','100.00','50.00',2,1),(16,'500.00','310000.00','620.00','2020-05-09 23:29:02','','100.00','50.00',1,1);

/*Table structure for table `producttype` */

DROP TABLE IF EXISTS `producttype`;

CREATE TABLE `producttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `firstid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA8168A931A015E4` (`parent_id`),
  CONSTRAINT `FKA8168A931A015E4` FOREIGN KEY (`parent_id`) REFERENCES `producttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `producttype` */

insert  into `producttype`(`id`,`name`,`descs`,`parent_id`,`firstid`) values (1,'汽车','汽车',NULL,0),(2,'奥迪','奥迪',1,NULL),(3,'奔驰','奔驰',1,NULL),(4,'大众','大众',1,NULL),(5,'电视','电视',NULL,0),(6,'3D电视','3D电视',5,NULL),(7,'液晶电视','液晶电视',5,NULL),(16,'36k液晶','36k液晶',7,NULL),(19,'88k液晶','88k液晶',7,NULL),(21,'测试','车市',7,NULL),(22,'和规范地方','水电费',7,NULL),(24,'丰富的个','是',7,NULL),(27,'特有他人员表','玩儿',4,NULL),(28,'法国恢复','电饭锅',21,NULL),(37,'飞机','飞机',NULL,0),(38,'大炮','大炮',NULL,0),(39,'航母','航母',NULL,0),(40,'测试','测试',NULL,0),(41,'测试1-1','测试1-1',40,NULL),(42,'测试1-2','测试1-2',40,NULL),(43,'测试1-3','测试1-3',40,NULL),(44,'测试1-2-1','测试1-2-1',42,NULL),(45,'测试1-2-2','测试1-2-2',42,NULL),(46,'测试1-2-3','测试1-2-3',42,NULL),(47,'测试1-2-2-1','测试1-2-2-1',45,NULL),(48,'测试1-2-2-1-1','测试1-2-2-1-1',47,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `purchasebill` */

insert  into `purchasebill`(`id`,`vdate`,`totalAmount`,`totalNum`,`inputTime`,`auditorTime`,`status`,`supplier_id`,`auditor_id`,`inputUser_id`,`buyer_id`) values (1,'2020-04-25 00:00:00','0.05','0.22','2020-05-09 23:09:35',NULL,0,1,1,1,33),(2,'2020-04-22 00:00:00','2500.00','50.00','2020-05-09 21:00:02','2020-05-09 19:08:14',1,1,1,1,12),(3,'2020-04-21 12:00:00','4.00','2.00','2020-05-09 20:59:59',NULL,2,2,NULL,1,39),(11,'2018-06-28 08:14:07','16.00','8.00','2020-05-09 19:08:22','2020-05-09 19:08:22',1,1,1,1,8),(12,'2020-05-29 00:00:00','3393.09','98.06','2020-05-09 21:03:28','2020-05-09 21:03:28',1,1,1,1,8),(16,'2018-07-02 00:00:00','990.00','30.00','2020-05-09 21:04:23','2020-05-09 21:04:23',1,2,1,1,12),(19,'2018-07-04 00:00:00','266.00','33.00','2020-05-09 19:10:36','2020-05-09 19:10:36',1,1,1,1,12),(22,'2018-10-18 00:00:00','34.00','1.00','2020-05-09 19:10:34','2020-05-09 19:10:31',1,2,1,1,12),(23,'2020-05-27 00:00:00','0.18','0.60','2020-05-06 23:34:41',NULL,0,1,NULL,1,8),(24,'2020-05-05 00:00:00','600.20','30.60','2020-05-09 19:08:32','2020-05-09 19:08:26',1,1,1,1,8),(33,'2020-05-28 07:07:07','0.00','0.00','2020-05-09 21:01:26','2020-05-09 21:01:26',1,1,1,1,8),(34,'2010-10-23 00:00:00','2500.00','50.00','2020-05-09 21:01:28','2020-05-09 21:01:28',1,1,1,1,8),(38,'2020-04-07 07:07:07','0.00','0.00','2020-05-09 21:05:47','2020-05-09 21:05:47',1,1,1,1,8),(39,'2020-05-09 00:00:00','0.00','0.00','2020-05-09 21:07:24',NULL,0,2,NULL,1,12),(41,'2019-05-09 00:00:00','50.30','19.00','2020-05-09 21:01:20','2020-05-09 21:01:20',1,2,1,1,8);

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
) ENGINE=InnoDB AUTO_INCREMENT=1159 DEFAULT CHARSET=utf8;

/*Data for the table `purchasebillitem` */

insert  into `purchasebillitem`(`id`,`price`,`num`,`amount`,`descs`,`product_id`,`bill_id`) values (204,'0.30','0.30','0.09',NULL,2,23),(205,'0.30','0.30','0.09',NULL,1,23),(1020,'2.00','3.00','6.00','',1,11),(1021,'2.00','5.00','10.00','',2,11),(1025,'0.20','0.20','0.04',NULL,2,24),(1026,'0.40','0.40','0.16',NULL,1,24),(1027,'20.00','30.00','600.00',NULL,2,24),(1040,'34.00','1.00','34.00','',1,22),(1041,'2.00','23.00','46.00','fsdfsdf',2,19),(1042,'22.00','10.00','220.00','sdfaf',1,19),(1043,'2.00','2.00','4.00','',2,3),(1044,'50.00','50.00','2500.00',NULL,2,2),(1096,'3.70','6.50','24.05',NULL,2,41),(1097,'2.10','12.50','26.25',NULL,1,41),(1098,'0.00','0.00','0.00',NULL,2,33),(1099,'50.00','50.00','2500.00',NULL,1,34),(1100,'2.66','3.76','10.00','',2,12),(1101,'23.00','88.00','2024.00','',1,12),(1102,'0.00','0.00','0.00',NULL,1,12),(1103,'0.00','0.00','0.00',NULL,1,12),(1104,'0.00','0.00','0.00',NULL,2,12),(1105,'2.60','3.60','9.36',NULL,1,12),(1106,'499.90','2.70','1349.73',NULL,2,12),(1107,'0.00','0.00','0.00',NULL,1,12),(1108,'33.00','30.00','990.00','',2,16),(1114,'0.00','0.00','0.00',NULL,1,38),(1115,'0.00','0.00','0.00',NULL,2,38),(1116,'0.00','0.00','0.00',NULL,1,38),(1138,'0.23','0.22','0.05',NULL,1,1),(1139,'0.00','0.00','0.00',NULL,1,1),(1140,'0.00','0.00','0.00',NULL,2,1),(1141,'0.00','0.00','0.00',NULL,1,1),(1142,'0.00','0.00','0.00',NULL,1,1),(1143,'0.00','0.00','0.00',NULL,1,1),(1144,'0.00','0.00','0.00',NULL,2,1),(1145,'0.00','0.00','0.00',NULL,1,1),(1146,'0.00','0.00','0.00',NULL,1,1),(1147,'0.00','0.00','0.00',NULL,2,1),(1148,'0.00','0.00','0.00',NULL,1,1),(1149,'0.00','0.00','0.00',NULL,2,1),(1150,'0.00','0.00','0.00',NULL,1,1),(1151,'0.00','0.00','0.00',NULL,2,1),(1152,'0.00','0.00','0.00',NULL,1,1),(1153,'0.00','0.00','0.00',NULL,1,1),(1154,'0.00','0.00','0.00',NULL,2,1),(1155,'0.00','0.00','0.00',NULL,2,1),(1156,'0.00','0.00','0.00',NULL,2,1),(1157,'0.00','0.00','0.00',NULL,1,1),(1158,'0.00','0.00','0.00',NULL,1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`sn`) values (1,'超级管理员','admin'),(2,'角色管理员','guest'),(4,'人事部','renShiBu'),(5,'普通用户','primary'),(6,'访客','cusiom');

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

insert  into `role_permission`(`role_id`,`permission_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,50),(1,51),(1,52),(1,53),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,80),(1,81),(1,82),(1,83),(1,84),(1,85),(1,86),(1,87),(1,88),(1,89),(1,90),(1,91),(1,92),(1,93),(1,94),(1,95),(1,96),(1,97),(1,98),(1,99),(1,100),(1,101),(1,145),(1,146),(1,147),(1,148),(1,149),(1,150),(1,151),(1,152),(1,153),(1,154),(1,155),(1,156),(1,157),(1,158),(2,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `stockincomebill` */

insert  into `stockincomebill`(`id`,`vdate`,`totalAmount`,`totalNum`,`inputTime`,`auditorTime`,`status`,`supplier_id`,`auditor_id`,`inputUser_id`,`keeper_id`,`depot_id`) values (3,'2020-05-28 15:10:34','0.61','1.30','2020-05-09 23:11:02','2020-05-09 18:18:01',0,2,1,1,16,2),(4,'2020-05-31 00:00:00','0.00','0.00','2020-05-09 22:33:18','2020-05-09 18:40:07',1,2,1,1,19,2),(5,'2020-05-20 00:00:00','210000.00','600.00','2020-05-09 23:29:02','2020-05-09 23:29:02',1,2,1,1,26,1),(7,'2020-04-29 00:00:00','52000.00','200.00','2020-05-09 23:17:29','2020-05-09 23:17:29',0,2,1,1,18,2),(8,'2020-05-09 00:00:00','0.00','0.00','2020-05-09 22:20:58','2020-05-09 21:23:41',1,2,1,1,18,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

/*Data for the table `stockincomebillitem` */

insert  into `stockincomebillitem`(`id`,`price`,`num`,`amount`,`descs`,`product_id`,`bill_id`) values (118,'0.00','0.00','0.00',NULL,2,8),(121,'0.00','0.00','0.00',NULL,1,4),(128,'0.40','0.40','0.16',NULL,1,3),(129,'0.50','0.50','0.25',NULL,2,3),(130,'0.50','0.40','0.20',NULL,2,3),(137,'500.00','100.00','50000.00',NULL,2,7),(138,'20.00','100.00','2000.00',NULL,1,7),(169,'200.00','300.00','60000.00',NULL,2,5),(170,'500.00','300.00','150000.00',NULL,1,5);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `systemdictionarydetail` */

insert  into `systemdictionarydetail`(`id`,`name`,`types_id`) values (1,'七匹狼',1),(2,'耐克',1),(3,'条',2),(4,'斤',2),(5,'桶',2);

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
