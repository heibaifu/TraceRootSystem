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
INSERT INTO `boat` VALUES ('0001','001','120.972548,38.58983','150',NULL,'2019-08-15 08:30:56','2019-08-15 08:37:41'),('0002','001','121.276103,39.37958','150',NULL,'2019-08-15 08:41:00','2019-08-15 08:41:44'),('0003','002','120.517214,38.169847','150',NULL,'2019-08-15 08:43:19','2019-08-15 08:48:27'),('0004','002','120.287248,37.692604','150',NULL,'2019-08-15 08:49:16','2019-08-15 08:55:51'),('0005','003','120.199861,38.249687','150',NULL,'2019-08-15 08:56:48','2019-08-15 08:59:04'),('0006','001','120.558608,39.079088','150',NULL,'2019-08-15 09:01:25','2019-08-15 09:02:42'),('0007','001','119.797133,37.83008','150',NULL,'2019-08-15 09:03:09','2019-08-15 09:05:10'),('0641976','001','116.371777,39.965718','150','1188454','2019-07-28 10:13:08','2019-08-06 10:06:04'),('2506573','002','116.398834,39.95369','150','8211997','2019-07-27 10:57:35','2019-08-06 10:06:04'),('5562266','003','116.366746,39.967792','150','6479668','2019-07-28 10:47:44','2019-08-06 10:06:04');
/*!40000 ALTER TABLE `boat` ENABLE KEYS */;
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
INSERT INTO `boat_trace` VALUES ('0257105',3,'0001','119.629545,38.416342','150','2019-08-15 08:35:26'),('0706747',2,'0004','121.56586,37.856895','150','2019-08-15 08:49:41'),('0992900',5,'0007','119.797133,37.83008','150','2019-08-15 09:05:10'),('1064553',2,'2506573','116.365938,39.970226','150','2019-07-27 12:28:24'),('1348319',1,'0641976','116.362983,39.967599','150','2019-07-28 10:15:18'),('1398215',2,'5562266','116.363854,39.970695','150','2019-07-28 10:48:29'),('1701029',3,'5562266','116.367196,39.97035','150','2019-07-28 10:49:01'),('1748359',1,'0007','119.234003,37.97714','150','2019-08-15 09:03:09'),('1785220',1,'0003','118.98104,37.612151','150','2019-08-15 08:43:19'),('2004773',5,'2506573','116.398834,39.95369','150','2019-07-27 12:45:18'),('2066442',4,'2506573','116.373592,39.965256','150','2019-07-27 12:34:43'),('2215600',5,'0641976','116.371777,39.965718','150','2019-07-28 10:34:00'),('3009148',5,'0004','120.287248,37.692604','150','2019-08-15 08:55:51'),('3808513',4,'0007','120.15588,38.161307','150','2019-08-15 09:04:39'),('3928949',1,'0004','121.266904,37.582873','150','2019-08-15 08:49:16'),('4275375',3,'0003','119.827316,37.472978','150','2019-08-15 08:45:42'),('4288190',5,'0003','120.370036,37.867834','150','2019-08-15 08:47:07'),('4586597',2,'0007','119.231416,38.266546','150','2019-08-15 09:03:51'),('4765146',3,'2506573','116.367906,39.967392','150','2019-07-27 12:33:05'),('4781816',5,'0001','120.972548,38.58983','150','2019-08-15 08:37:41'),('4963178',2,'0001','118.383128,38.470602','150','2019-08-15 08:33:45'),('5138239',2,'0003','119.220205,37.597513','150','2019-08-15 08:43:42'),('5161856',1,'0005','119.882508,37.373934','150','2019-08-15 08:56:49'),('5577276',1,'5562266','116.36565,39.967115','150','2019-07-28 10:47:44'),('5746412',2,'0002','119.385781,39.150751','150','2019-08-15 08:41:12'),('5825681',1,'2506573','116.362983,39.967599','150','2019-07-27 11:17:18'),('5913428',4,'0005','119.174212,38.737684','150','2019-08-15 08:58:09'),('5955624',3,'0641976','116.367906,39.967392','150','2019-07-28 10:20:55'),('6032089',1,'0006','120.797773,37.81312','150','2019-08-15 09:01:26'),('6509596',3,'0007','119.741941,38.34628','150','2019-08-15 09:04:16'),('6939414',5,'0005','119.234003,38.173478','150','2019-08-15 08:58:41'),('7099941',3,'0004','120.967948,38.051738','150','2019-08-15 08:53:45'),('7149727',4,'5562266','116.366746,39.967792','150','2019-07-28 10:49:25'),('7791709',4,'0641976','116.367986,39.970156','150','2019-07-28 10:31:33'),('7856628',4,'0002','121.276103,39.37958','150','2019-08-15 08:41:44'),('7913668',1,'0001','118.240549,38.924763','150','2019-08-15 08:30:57'),('8101805',4,'0003','119.836514,37.765668','150','2019-08-15 08:46:26'),('8451881',2,'0006','121.02314,38.166215','150','2019-08-15 09:01:50'),('8548188',4,'0004','120.48042,38.057194','150','2019-08-15 08:55:13'),('8561985',6,'0003','120.517214,38.169847','150','2019-08-15 08:48:27'),('8763842',3,'0002','120.475821,39.64678','150','2019-08-15 08:41:25'),('8772196',3,'0006','120.558608,39.079088','150','2019-08-15 09:02:42'),('9143970',1,'0002','118.525707,38.924763','150','2019-08-15 08:41:00'),('9248461',2,'0641976','116.365938,39.970226','150','2019-07-28 10:20:11'),('9316229',6,'0005','120.199861,38.249687','150','2019-08-15 08:59:04'),('9614790',4,'0001','120.485019,38.730478','150','2019-08-15 08:37:12'),('9755076',2,'0005','119.951498,38.100822','150','2019-08-15 08:57:25'),('9809247',3,'0005','119.703134,38.755694','150','2019-08-15 08:57:49');
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
INSERT INTO `pipeline` VALUES ('10001','117.854206,38.906347','121.144591,38.746098','2019-08-14 03:05:04','2019-08-14 03:23:20'),('10002','119.106219,37.910437','121.465672,37.53779','2019-08-14 03:26:16','2019-08-14 03:26:16'),('1053733','162.1563,-73.15639','82.15639,-81.15639','2019-07-24 08:56:14','2019-08-06 10:27:26'),('1111111','111.1563,-11.157836','115.1564,78.265489','2019-08-05 05:25:17','2019-08-06 10:27:26'),('1111112','111.1563,-11.157836','115.1564,78.265489','2019-08-06 10:26:37','2019-08-06 10:26:37'),('1904289','168.1563,-43.15636','-149.1563,61.15636','2019-07-21 03:44:32','2019-08-06 10:27:26'),('2044895','44.15636,49.15636','108.1563,-5.156368','2019-07-21 03:43:48','2019-08-06 10:27:26');
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
INSERT INTO `pipeline_segment` VALUES ('1001',1,'10001','117.854206,38.906347','119.004037,38.343478','2019-08-14 03:14:40','2019-08-14 03:14:40'),('1002',2,'10001','119.004037,38.343478','120.034285,38.560493','2019-08-14 03:17:32','2019-08-14 03:17:32'),('1003',3,'10001','120.034285,38.560493','121.144591,38.746098','2019-08-14 03:20:11','2019-08-14 03:20:11'),('123',1,'2044895','116.362003,39.968636','116.370771,39.96847','2019-07-27 10:49:14','2019-08-06 10:05:34'),('124',2,'2044895','116.370771,39.96847','116.369945,39.964302','2019-07-27 10:54:36','2019-08-06 10:05:34'),('125',3,'2044895','101.1564,-22.15643','136.1564,-16.15643','2019-07-29 09:49:49','2019-08-06 10:05:34'),('126',4,'2044895','96.15643,67.15643','36.15643,-58.15643','2019-07-29 10:28:09','2019-08-06 10:05:34'),('127',5,'2044895','142.15643,17.15643','-16.15643,-18.15643','2019-08-05 08:39:56','2019-08-06 10:05:34'),('128',1,'1111111','112.15643,-57.15643','-162.15643,-48.15643','2019-08-05 08:40:58','2019-08-06 10:05:34'),('2001',1,'10002','119.106219,37.910437','119.50636,37.387468','2019-08-14 03:27:33','2019-08-14 03:27:33'),('2002',2,'10002','119.50636,37.387468','120.242252,37.943227','2019-08-14 03:29:36','2019-08-14 03:29:36'),('2003',3,'10002','120.242252,37.943227','121.267183,38.062677','2019-08-14 03:31:53','2019-08-14 03:31:53'),('2004',4,'10002','121.267183,38.062677','121.465672,37.53779','2019-08-14 03:32:52','2019-08-14 03:32:52');
/*!40000 ALTER TABLE `pipeline_segment` ENABLE KEYS */;
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
INSERT INTO `pipeline_sensor` VALUES ('101','1001','10001','2926155','117.854206,38.906347','100',NULL,'2019-08-14 03:43:54','2019-08-14 04:02:25'),('102','1001','10001','7752109','118.374424,38.615659','102',NULL,'2019-08-14 03:46:50','2019-08-14 03:46:50'),('103','1002','10001','7752109','119.69443,38.496474','101',NULL,'2019-08-14 03:48:03','2019-08-14 04:02:25'),('104','1003','10001','2926378','120.388928,38.648129','100',NULL,'2019-08-14 03:51:44','2019-08-14 04:02:25'),('201','2001','10002','2926378','119.28509,37.788143','100',NULL,'2019-08-14 03:53:25','2019-08-14 04:02:25'),('202','2002','10002','7752109','119.970389,37.711448','102',NULL,'2019-08-14 03:54:59','2019-08-14 03:54:59'),('203','2003','10002','7752109','121.014436,38.061399','101',NULL,'2019-08-14 03:56:23','2019-08-14 04:02:25'),('204','2004','10002','2926155','121.373183,37.766238','100',NULL,'2019-08-14 03:57:45','2019-08-14 04:02:25'),('7337065','123','2044895','2926155','116.362003,39.968636','102','0.56','2019-08-06 08:13:21','2019-08-06 10:04:03');
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
INSERT INTO `route_segment` VALUES ('200',1,'1188454','90.15644,-59.15644','114.1564,+68.15644','2019-07-29 11:33:35','2019-08-06 10:28:13'),('202',2,'1188454','69.15644,-70.15644','-171.1564,-20.15644','2019-07-29 11:34:43','2019-08-06 10:28:13'),('203',3,'1188454','-136.1564,8.156440','47.15644,-86.15644','2019-07-29 11:35:04','2019-08-06 10:28:13'),('204',4,'1188454','99.15644,84.15644','-87.15644,+4.156440','2019-07-29 11:35:35','2019-08-06 10:28:13');
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
INSERT INTO `sea_route` VALUES ('1188454','110','-72.15640,-54.15640','0.156406,23.15640','2019-07-25 13:29:36','2019-08-06 10:07:54'),('2472838','110','-161.1564,-26.15640','88.15640,-79.15640','2019-07-25 12:54:55','2019-08-06 10:07:54'),('6479668','110','-118.1564,-37.15640','51.15640,-69.15640','2019-07-25 13:29:26','2019-08-06 10:07:54'),('8211997','111','41.15640,+50.15640','71.15640,+26.15640','2019-07-25 12:56:24','2019-08-06 10:07:54'),('8959983','112','-114.1564,-1.156405','22.15640,-74.15640','2019-07-25 12:55:11','2019-08-06 10:07:54');
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
INSERT INTO `sensor_status` VALUES ('0152242','104','102',NULL,'2019-08-14 03:51:44'),('1152426','7337065','100','0.3','2019-08-16 09:17:05'),('1998190','203','102',NULL,'2019-08-14 03:56:23'),('2328085','204','102',NULL,'2019-08-14 03:57:45'),('3588156','103','102',NULL,'2019-08-14 03:48:03'),('3739974','202','102',NULL,'2019-08-14 03:54:59'),('5455199','201','102',NULL,'2019-08-14 03:53:26'),('6007060','7337065','102','1','2019-08-16 09:18:09'),('7969273','7337065','102','0','2019-08-16 09:12:31'),('8347180','101','102',NULL,'2019-08-14 03:43:54'),('8981246','102','102',NULL,'2019-08-14 03:46:50'),('9151626','7337065','102',NULL,'2019-08-06 08:13:21');
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
INSERT INTO `sensor_type` VALUES ('2926155','温度传感器','0.1','0.8','2019-07-24 09:15:43','2019-08-16 08:48:04'),('2926378','杂质传感器','0','0.7','2019-07-24 09:16:47','2019-08-16 08:48:04'),('7752109','管压传感器','0.2','0.6','2019-07-24 09:16:17','2019-08-16 08:48:04');
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

-- Dump completed on 2019-08-16 17:32:10
