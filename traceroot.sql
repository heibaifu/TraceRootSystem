CREATE DATABASE  IF NOT EXISTS `traceroot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `traceroot`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: traceroot
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `boat`
--

DROP TABLE IF EXISTS `boat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `boat` (
  `boat_id` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `present_location` varchar(30) DEFAULT NULL COMMENT '船只当前位置',
  `speed` varchar(10) DEFAULT NULL COMMENT '船速',
  `overspeed_judging` int(11) DEFAULT NULL COMMENT '超速判断，正常为0，超速为1',
  `direction` varchar(10) DEFAULT NULL COMMENT '航行方向',
  `status` varchar(10) DEFAULT '未出航' COMMENT '船只状态',
  `route_id` varchar(10) DEFAULT NULL COMMENT '船只航线',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`boat_id`),
  KEY `BOAT` (`route_id`),
  KEY `BOAT_TYPE_idx` (`type`),
  CONSTRAINT `BOAT` FOREIGN KEY (`route_id`) REFERENCES `sea_route` (`route_id`),
  CONSTRAINT `BOAT_TYPE` FOREIGN KEY (`type`) REFERENCES `boat_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat`
--

LOCK TABLES `boat` WRITE;
/*!40000 ALTER TABLE `boat` DISABLE KEYS */;
INSERT INTO `boat` VALUES ('0001','001','120.972548,38.58983',NULL,NULL,NULL,'150','801','2019-08-15 08:30:56','2019-08-18 04:00:37'),('0002','001','121.276103,39.37958',NULL,NULL,NULL,'150','802','2019-08-15 08:41:00','2019-08-18 04:00:37'),('0003','002','120.517214,38.169847',NULL,NULL,NULL,'150','803','2019-08-15 08:43:19','2019-08-18 04:00:37'),('0004','002','120.287248,37.692604',NULL,NULL,NULL,'150','804','2019-08-15 08:49:16','2019-08-18 04:00:37'),('0005','003','120.199861,38.249687',NULL,NULL,NULL,'150','803','2019-08-15 08:56:48','2019-08-18 04:00:37'),('0006','001','120.558608,39.079088',NULL,NULL,NULL,'150','801','2019-08-15 09:01:25','2019-08-18 04:00:37'),('0007','001','119.797133,37.83008',NULL,NULL,NULL,'150','802','2019-08-15 09:03:09','2019-08-18 04:00:37'),('0009','003','121.006468,39.317085',NULL,NULL,NULL,'150',NULL,'2019-08-19 10:49:53','2019-08-19 10:55:45'),('0010','003','122.373183,38.766238',NULL,NULL,NULL,'150',NULL,'2019-08-19 11:22:41','2019-08-19 11:22:49');
/*!40000 ALTER TABLE `boat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boat_predict`
--

DROP TABLE IF EXISTS `boat_predict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `boat_predict` (
  `predict_id` varchar(20) NOT NULL COMMENT '预测编号',
  `boat_id` varchar(10) NOT NULL COMMENT '船只ID',
  `predict_location` varchar(30) DEFAULT NULL COMMENT '到达预测的地点',
  `arrive_time` varchar(30) DEFAULT NULL COMMENT '到达预测地点的时间',
  `probability` varchar(10) DEFAULT NULL COMMENT '到达预测地点的概率',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`predict_id`),
  KEY `FOREIGN_ID_idx` (`boat_id`),
  CONSTRAINT `FOREIGN_ID` FOREIGN KEY (`boat_id`) REFERENCES `boat` (`boat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只下一个位置预测表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat_predict`
--

LOCK TABLES `boat_predict` WRITE;
/*!40000 ALTER TABLE `boat_predict` DISABLE KEYS */;
/*!40000 ALTER TABLE `boat_predict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boat_trace`
--

DROP TABLE IF EXISTS `boat_trace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `boat_trace` (
  `trace_id` varchar(30) NOT NULL,
  `trace_serial_number` int(11) NOT NULL,
  `boat_id` varchar(10) NOT NULL,
  `record_location` varchar(30) DEFAULT NULL COMMENT '船只当前位置',
  `speed` varchar(10) DEFAULT NULL COMMENT '船速',
  `overspeed_judging` int(11) DEFAULT NULL COMMENT '超速判断，正常为0，超速为1',
  `direction` varchar(10) DEFAULT NULL COMMENT '航行方向',
  `status` varchar(10) DEFAULT '未出航' COMMENT '船只状态',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`trace_id`),
  KEY `BOAT_TRACE` (`boat_id`),
  CONSTRAINT `BOAT_TRACE` FOREIGN KEY (`boat_id`) REFERENCES `boat` (`boat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只轨迹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat_trace`
--

LOCK TABLES `boat_trace` WRITE;
/*!40000 ALTER TABLE `boat_trace` DISABLE KEYS */;
INSERT INTO `boat_trace` VALUES ('0257105',3,'0001','119.629545,38.416342',NULL,NULL,NULL,'150','2019-08-15 08:35:26'),('0562300',10,'0009','120.132596,39.502621',NULL,NULL,NULL,'150','2019-08-19 10:52:50'),('0605324',6,'0009','120.491343,38.338497',NULL,NULL,NULL,'150','2019-08-19 10:51:59'),('0706747',2,'0004','121.56586,37.856895',NULL,NULL,NULL,'150','2019-08-15 08:49:41'),('0992900',5,'0007','119.797133,37.83008',NULL,NULL,NULL,'150','2019-08-15 09:05:10'),('1748359',1,'0007','119.234003,37.97714',NULL,NULL,NULL,'150','2019-08-15 09:03:09'),('1785220',1,'0003','118.98104,37.612151',NULL,NULL,NULL,'150','2019-08-15 08:43:19'),('2938005',7,'0009','119.737054,37.866011',NULL,NULL,NULL,'150','2019-08-19 10:52:16'),('3009148',5,'0004','120.287248,37.692604',NULL,NULL,NULL,'150','2019-08-15 08:55:51'),('3808513',4,'0007','120.15588,38.161307',NULL,NULL,NULL,'150','2019-08-15 09:04:39'),('3928949',1,'0004','121.266904,37.582873',NULL,NULL,NULL,'150','2019-08-15 08:49:16'),('4275375',3,'0003','119.827316,37.472978',NULL,NULL,NULL,'150','2019-08-15 08:45:42'),('4288190',5,'0003','120.370036,37.867834',NULL,NULL,NULL,'150','2019-08-15 08:47:07'),('4586597',2,'0007','119.231416,38.266546',NULL,NULL,NULL,'150','2019-08-15 09:03:51'),('4781816',5,'0001','120.972548,38.58983',NULL,NULL,NULL,'150','2019-08-15 08:37:41'),('4818286',5,'0009','121.493996,37.953471',NULL,NULL,NULL,'150','2019-08-19 10:51:27'),('4963178',2,'0001','118.383128,38.470602',NULL,NULL,NULL,'150','2019-08-15 08:33:45'),('5138239',2,'0003','119.220205,37.597513',NULL,NULL,NULL,'150','2019-08-15 08:43:42'),('5161856',1,'0005','119.882508,37.373934',NULL,NULL,NULL,'150','2019-08-15 08:56:49'),('5591768',3,'0009','124.042022,38.338497',NULL,NULL,NULL,'150','2019-08-19 10:50:59'),('5716102',2,'0010','122.373183,38.766238',NULL,NULL,NULL,'150','2019-08-19 11:22:49'),('5746412',2,'0002','119.385781,39.150751',NULL,NULL,NULL,'150','2019-08-15 08:41:12'),('5913428',4,'0005','119.174212,38.737684',NULL,NULL,NULL,'150','2019-08-15 08:58:09'),('6032089',1,'0006','120.797773,37.81312',NULL,NULL,NULL,'150','2019-08-15 09:01:26'),('6193953',1,'0009','122.671423,37.412467',NULL,NULL,NULL,'150','2019-08-19 10:49:53'),('6509596',3,'0007','119.741941,38.34628',NULL,NULL,NULL,'150','2019-08-15 09:04:16'),('6733165',1,'0010','122.373183,37.766238',NULL,NULL,NULL,'150','2019-08-19 11:22:41'),('6939414',5,'0005','119.234003,38.173478',NULL,NULL,NULL,'150','2019-08-15 08:58:41'),('7099941',3,'0004','120.967948,38.051738',NULL,NULL,NULL,'150','2019-08-15 08:53:45'),('7466967',2,'0009','123.98683,37.177288',NULL,NULL,NULL,'150','2019-08-19 10:50:19'),('7856628',4,'0002','121.276103,39.37958',NULL,NULL,NULL,'150','2019-08-15 08:41:44'),('7913668',1,'0001','118.240549,38.924763',NULL,NULL,NULL,'150','2019-08-15 08:30:57'),('7986838',9,'0009','119.396704,38.958887',NULL,NULL,NULL,'150','2019-08-19 10:52:45'),('8101805',4,'0003','119.836514,37.765668',NULL,NULL,NULL,'150','2019-08-15 08:46:26'),('8321192',4,'0009','122.450655,38.865455',NULL,NULL,NULL,'150','2019-08-19 10:51:11'),('8451881',2,'0006','121.02314,38.166215',NULL,NULL,NULL,'150','2019-08-15 09:01:50'),('8515372',8,'0009','118.973566,38.468794',NULL,NULL,NULL,'150','2019-08-19 10:52:32'),('8548188',4,'0004','120.48042,38.057194',NULL,NULL,NULL,'150','2019-08-15 08:55:13'),('8561985',6,'0003','120.517214,38.169847',NULL,NULL,NULL,'150','2019-08-15 08:48:27'),('8763842',3,'0002','120.475821,39.64678',NULL,NULL,NULL,'150','2019-08-15 08:41:25'),('8772196',3,'0006','120.558608,39.079088',NULL,NULL,NULL,'150','2019-08-15 09:02:42'),('9111125',11,'0009','121.006468,39.317085',NULL,NULL,NULL,'150','2019-08-19 10:53:10'),('9143970',1,'0002','118.525707,38.924763',NULL,NULL,NULL,'150','2019-08-15 08:41:00'),('9316229',6,'0005','120.199861,38.249687',NULL,NULL,NULL,'150','2019-08-15 08:59:04'),('9614790',4,'0001','120.485019,38.730478',NULL,NULL,NULL,'150','2019-08-15 08:37:12'),('9755076',2,'0005','119.951498,38.100822',NULL,NULL,NULL,'150','2019-08-15 08:57:25'),('9809247',3,'0005','119.703134,38.755694',NULL,NULL,NULL,'150','2019-08-15 08:57:49');
/*!40000 ALTER TABLE `boat_trace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boat_type`
--

DROP TABLE IF EXISTS `boat_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `boat_type` (
  `type_id` varchar(10) NOT NULL,
  `descibe` varchar(10) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只种类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat_type`
--

LOCK TABLES `boat_type` WRITE;
/*!40000 ALTER TABLE `boat_type` DISABLE KEYS */;
INSERT INTO `boat_type` VALUES ('001','小渔船','2019-07-23 14:13:31','2019-07-23 14:13:31'),('002','大渔船','2019-07-23 14:13:31','2019-07-23 14:13:31'),('003','航母','2019-07-23 14:16:16','2019-07-23 14:16:16');
/*!40000 ALTER TABLE `boat_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pipeline`
--

DROP TABLE IF EXISTS `pipeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pipeline` (
  `pipe_id` varchar(10) NOT NULL,
  `source` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管道信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline`
--

LOCK TABLES `pipeline` WRITE;
/*!40000 ALTER TABLE `pipeline` DISABLE KEYS */;
INSERT INTO `pipeline` VALUES ('10001','117.854206,38.906347','121.144591,38.746098','2019-08-14 03:05:04','2019-08-14 03:23:20'),('10002','119.106219,37.910437','121.465672,37.53779','2019-08-14 03:26:16','2019-08-14 03:26:16');
/*!40000 ALTER TABLE `pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pipeline_segment`
--

DROP TABLE IF EXISTS `pipeline_segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pipeline_segment` (
  `segment_id` varchar(20) NOT NULL,
  `segment_serial_number` int(11) unsigned NOT NULL,
  `pipe_id` varchar(10) NOT NULL,
  `start` varchar(45) NOT NULL,
  `end` varchar(45) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`segment_id`),
  KEY `PIPELINE_SEGMENT` (`pipe_id`),
  CONSTRAINT `PIPELINE_SEGMENT` FOREIGN KEY (`pipe_id`) REFERENCES `pipeline` (`pipe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管道地理位置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline_segment`
--

LOCK TABLES `pipeline_segment` WRITE;
/*!40000 ALTER TABLE `pipeline_segment` DISABLE KEYS */;
INSERT INTO `pipeline_segment` VALUES ('1001',1,'10001','117.854206,38.906347','119.004037,38.343478','2019-08-14 03:14:40','2019-08-14 03:14:40'),('1002',2,'10001','119.004037,38.343478','120.034285,38.560493','2019-08-14 03:17:32','2019-08-14 03:17:32'),('1003',3,'10001','120.034285,38.560493','121.144591,38.746098','2019-08-14 03:20:11','2019-08-14 03:20:11'),('2001',1,'10002','119.106219,37.910437','119.50636,37.387468','2019-08-14 03:27:33','2019-08-14 03:27:33'),('2002',2,'10002','119.50636,37.387468','120.242252,37.943227','2019-08-14 03:29:36','2019-08-14 03:29:36'),('2003',3,'10002','120.242252,37.943227','121.267183,38.062677','2019-08-14 03:31:53','2019-08-14 03:31:53'),('2004',4,'10002','121.267183,38.062677','121.465672,37.53779','2019-08-14 03:32:52','2019-08-14 03:32:52');
/*!40000 ALTER TABLE `pipeline_segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pipeline_segment_predict`
--

DROP TABLE IF EXISTS `pipeline_segment_predict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pipeline_segment_predict` (
  `predict_id` varchar(20) NOT NULL,
  `segment_id` varchar(20) NOT NULL,
  `predict_life` varchar(10) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`predict_id`),
  KEY `SEGMENT_FOREIGN_idx` (`segment_id`),
  CONSTRAINT `SEGMENT_FOREIGN` FOREIGN KEY (`segment_id`) REFERENCES `pipeline_segment` (`segment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline_segment_predict`
--

LOCK TABLES `pipeline_segment_predict` WRITE;
/*!40000 ALTER TABLE `pipeline_segment_predict` DISABLE KEYS */;
/*!40000 ALTER TABLE `pipeline_segment_predict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pipeline_sensor`
--

DROP TABLE IF EXISTS `pipeline_sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pipeline_sensor` (
  `sensor_id` varchar(20) NOT NULL,
  `segment_id` varchar(20) NOT NULL,
  `pipe_id` varchar(10) NOT NULL,
  `type_id` varchar(10) NOT NULL,
  `location` varchar(30) NOT NULL,
  `present_status` varchar(10) NOT NULL DEFAULT '状态正常' COMMENT '传感器状态',
  `present_value` varchar(10) DEFAULT NULL COMMENT '当前传感器值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sensor_id`),
  KEY `PIPELINE_SENSOR1` (`pipe_id`),
  KEY `PIPELINE_SENSOR2` (`segment_id`),
  KEY `PIPELINE_SENSOR3_idx` (`type_id`),
  CONSTRAINT `PIPELINE_SENSOR1` FOREIGN KEY (`pipe_id`) REFERENCES `pipeline` (`pipe_id`),
  CONSTRAINT `PIPELINE_SENSOR2` FOREIGN KEY (`segment_id`) REFERENCES `pipeline_segment` (`segment_id`),
  CONSTRAINT `PIPELINE_SENSOR3` FOREIGN KEY (`type_id`) REFERENCES `sensor_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管道传感器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pipeline_sensor`
--

LOCK TABLES `pipeline_sensor` WRITE;
/*!40000 ALTER TABLE `pipeline_sensor` DISABLE KEYS */;
INSERT INTO `pipeline_sensor` VALUES ('101','1001','10001','2926155','117.854206,38.906347','100','0.57','2019-08-14 03:43:54','2019-08-18 08:46:04'),('102','1001','10001','7752109','118.374424,38.615659','102','0.9','2019-08-14 03:46:50','2019-08-18 08:46:04'),('103','1002','10001','7752109','119.69443,38.496474','101','','2019-08-14 03:48:03','2019-08-18 08:46:04'),('104','1003','10001','2926378','120.388928,38.648129','100','0.45','2019-08-14 03:51:44','2019-08-18 08:46:04'),('132','1001','10001','3333333','118.874424,38.415659','102','0.22','2019-08-18 12:49:26','2019-08-18 13:01:49'),('133','1001','10001','2926378','118.574424,38.515659','100','0.22','2019-08-18 13:02:23','2019-08-18 13:02:23'),('201','2001','10002','2926378','119.28509,37.788143','100','0.34','2019-08-14 03:53:25','2019-08-18 08:46:04'),('202','2002','10002','7752109','119.970389,37.711448','102','0.98','2019-08-14 03:54:59','2019-08-18 08:46:04'),('203','2003','10002','7752109','121.014436,38.061399','100','0.23','2019-08-14 03:56:23','2019-08-19 11:21:38'),('204','2004','10002','2926155','121.373183,37.766238','100','0.22','2019-08-14 03:57:45','2019-08-18 08:46:04');
/*!40000 ALTER TABLE `pipeline_sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_segment`
--

DROP TABLE IF EXISTS `route_segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `route_segment` (
  `segment_id` varchar(20) NOT NULL,
  `segment_serial_number` int(11) NOT NULL,
  `route_id` varchar(10) NOT NULL,
  `start` varchar(30) NOT NULL,
  `end` varchar(30) NOT NULL,
  `limiting_speed` varchar(10) DEFAULT NULL COMMENT '航道限速',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`segment_id`),
  KEY `ROUTE_SEGMENT` (`route_id`),
  CONSTRAINT `ROUTE_SEGMENT` FOREIGN KEY (`route_id`) REFERENCES `sea_route` (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管道地理位置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_segment`
--

LOCK TABLES `route_segment` WRITE;
/*!40000 ALTER TABLE `route_segment` DISABLE KEYS */;
INSERT INTO `route_segment` VALUES ('2349590',3,'805','121.416095,38.418688  ','121.627664,38.883965',NULL,'2019-08-18 03:57:53','2019-08-18 03:57:53'),('3003797',2,'804','119.488978,37.368972  ','120.252466,37.694974',NULL,'2019-08-18 03:57:26','2019-08-18 03:57:26'),('3405678',2,'802','119.75114,38.934277  ','121.130937,38.815628',NULL,'2019-08-18 03:56:46','2019-08-18 03:56:46'),('4401049',2,'803','119.74654,37.961295  ','120.459435,37.749777',NULL,'2019-08-18 03:57:08','2019-08-18 03:57:08'),('4848195',1,'803','119.015248,37.979501 ','119.74654,37.961295  ',NULL,'2019-08-18 03:56:59','2019-08-18 03:56:59'),('4952096',1,'801','117.828622,39.067071','119.130231,38.574119',NULL,'2019-08-18 03:55:09','2019-08-18 03:55:09'),('8708214',1,'802','118.628904,39.034793  ','119.75114,38.934277  ',NULL,'2019-08-18 03:56:37','2019-08-18 03:56:37'),('8752677',2,'801','119.130231,38.574119 ','120.615812,38.765231  ',NULL,'2019-08-18 03:55:44','2019-08-18 03:55:44'),('9399852',2,'805','121.190728,38.095909  ','121.416095,38.418688  ',NULL,'2019-08-18 03:57:47','2019-08-18 03:57:47'),('9476798',1,'804','119.006049,37.647446  ','119.488978,37.368972  ',NULL,'2019-08-18 03:57:20','2019-08-18 03:57:20'),('9851274',3,'801','120.615812,38.765231  ','121.365502,39.381895',NULL,'2019-08-18 03:55:58','2019-08-18 03:55:58'),('9874949',1,'805','121.618465,37.479021','121.190728,38.095909  ',NULL,'2019-08-18 03:57:41','2019-08-18 03:57:41');
/*!40000 ALTER TABLE `route_segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sea_route`
--

DROP TABLE IF EXISTS `sea_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sea_route` (
  `route_id` varchar(10) NOT NULL,
  `status` varchar(10) DEFAULT '启用' COMMENT '航线状态',
  `source` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='航线表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sea_route`
--

LOCK TABLES `sea_route` WRITE;
/*!40000 ALTER TABLE `sea_route` DISABLE KEYS */;
INSERT INTO `sea_route` VALUES ('801','110','117.828622,39.067071','121.365502,39.381895','2019-08-18 03:45:18','2019-08-18 03:45:18'),('802','110','118.628904,39.034793','121.130937,38.815628','2019-08-18 03:45:47','2019-08-18 03:45:47'),('803','110','119.015248,37.979501','120.459435,37.749777','2019-08-18 03:46:04','2019-08-18 03:46:04'),('804','110','119.006049,37.647446','120.252466,37.694974','2019-08-18 03:46:24','2019-08-18 03:46:24'),('805','110','121.618465,37.479021','121.627664,38.883965','2019-08-18 03:46:45','2019-08-18 03:46:45');
/*!40000 ALTER TABLE `sea_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor_status`
--

DROP TABLE IF EXISTS `sensor_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor_status` (
  `status_id` varchar(30) NOT NULL,
  `sensor_id` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '100' COMMENT '传感器状态',
  `value` varchar(10) DEFAULT NULL COMMENT '传感器值',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`status_id`),
  KEY `SENSOR_STATUS` (`sensor_id`),
  CONSTRAINT `SENSOR_STATUS` FOREIGN KEY (`sensor_id`) REFERENCES `pipeline_sensor` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='传感器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor_status`
--

LOCK TABLES `sensor_status` WRITE;
/*!40000 ALTER TABLE `sensor_status` DISABLE KEYS */;
INSERT INTO `sensor_status` VALUES ('0152242','104','102',NULL,'2019-08-14 03:51:44'),('0696194','132','102','0.22','2019-08-18 13:01:49'),('1232146','132','102','0.18','2019-08-18 12:49:26'),('1998190','203','102',NULL,'2019-08-14 03:56:23'),('2328085','204','102',NULL,'2019-08-14 03:57:45'),('3119667','203','100','0.23','2019-08-19 11:21:38'),('3588156','103','102',NULL,'2019-08-14 03:48:03'),('3739974','202','102',NULL,'2019-08-14 03:54:59'),('4341411','132','100','0.88','2019-08-18 12:52:48'),('5455199','201','102',NULL,'2019-08-14 03:53:26'),('5524622','132','102','0.18','2019-08-18 12:51:50'),('7581949','132','100','0.88','2019-08-18 12:52:14'),('7812540','203','102','0.11','2019-08-19 10:48:26'),('8347180','101','102',NULL,'2019-08-14 03:43:54'),('8889978','133','100','0.22','2019-08-18 13:02:23'),('8981246','102','102',NULL,'2019-08-14 03:46:50'),('9577291','132','102','0.18','2019-08-18 12:51:13');
/*!40000 ALTER TABLE `sensor_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor_type`
--

DROP TABLE IF EXISTS `sensor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sensor_type` (
  `type_id` varchar(10) NOT NULL COMMENT '种类id',
  `type_name` varchar(30) NOT NULL COMMENT '传感器种类名字',
  `lowest_value` varchar(10) DEFAULT NULL COMMENT '最低值',
  `highest_value` varchar(10) DEFAULT NULL COMMENT '最高值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='传感器种类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor_type`
--

LOCK TABLES `sensor_type` WRITE;
/*!40000 ALTER TABLE `sensor_type` DISABLE KEYS */;
INSERT INTO `sensor_type` VALUES ('2926155','温度传感器','0.1','0.8','2019-07-24 09:15:43','2019-08-16 08:48:04'),('2926378','杂质传感器','0','0.7','2019-07-24 09:16:47','2019-08-16 08:48:04'),('3333333','二氧化碳传感器','0.3','0.9','2019-08-18 12:47:37','2019-08-18 12:47:37'),('7752109','管压传感器','0.2','0.6','2019-07-24 09:16:17','2019-08-16 08:48:04');
/*!40000 ALTER TABLE `sensor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'traceroot'
--

--
-- Dumping routines for database 'traceroot'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-22 19:45:12
