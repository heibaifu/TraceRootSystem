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
INSERT INTO `boat` VALUES ('0641976','001','(+116.371777,+39.965718)','150','1188454','2019-07-28 10:13:08','2019-07-28 10:34:00'),('2506573','002','(+116.398834,+39.95369)','150','8211997','2019-07-27 10:57:35','2019-07-27 12:45:18'),('5562266','003','(+116.366746,+39.967792)','150','6479668','2019-07-28 10:47:44','2019-07-28 10:49:25');
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
INSERT INTO `boat_trace` VALUES ('1064553',2,'2506573','(+116.365938,+39.970226)','150','2019-07-27 12:28:24'),('1348319',1,'0641976','(+116.362983,+39.967599)','150','2019-07-28 10:15:18'),('1398215',2,'5562266','(+116.363854,+39.970695)','150','2019-07-28 10:48:29'),('1701029',3,'5562266','(+116.367196,+39.97035)','150','2019-07-28 10:49:01'),('2004773',5,'2506573','(+116.398834,+39.95369)','150','2019-07-27 12:45:18'),('2066442',4,'2506573','(+116.373592,+39.965256)','150','2019-07-27 12:34:43'),('2215600',5,'0641976','(+116.371777,+39.965718)','150','2019-07-28 10:34:00'),('4765146',3,'2506573','(+116.367906,+39.967392)','150','2019-07-27 12:33:05'),('5577276',1,'5562266','(+116.36565,+39.967115)','150','2019-07-28 10:47:44'),('5825681',1,'2506573','(+116.362983,+39.967599)','150','2019-07-27 11:17:18'),('5955624',3,'0641976','(+116.367906,+39.967392)','150','2019-07-28 10:20:55'),('7149727',4,'5562266','(+116.366746,+39.967792)','150','2019-07-28 10:49:25'),('7791709',4,'0641976','(+116.367986,+39.970156)','150','2019-07-28 10:31:33'),('9248461',2,'0641976','(+116.365938,+39.970226)','150','2019-07-28 10:20:11');
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
INSERT INTO `pipeline` VALUES ('1053733','(+162.1563,-73.15639)','(+82.15639,-81.15639)','2019-07-24 08:56:14','2019-07-24 08:56:14'),('1904289','(+168.1563,-43.15636)','(-149.1563,+61.15636)','2019-07-21 03:44:32','2019-07-21 03:46:56'),('2044895','(+44.15636,+49.15636)','(+108.1563,-5.156368)','2019-07-21 03:43:48','2019-07-21 03:43:48'),('2692013','(+30.15636,+64.15636)','(-36.15636,-17.15636)','2019-07-21 03:43:00','2019-07-21 03:43:00'),('2877158','(+14.15639,+75.15639)','(-116.1563,+72.15639)','2019-07-24 08:56:14','2019-07-24 08:56:14'),('4047638','(+80.15639,+21.15639)','(+28.15639,+75.15639)','2019-07-24 08:56:14','2019-07-24 08:56:14'),('4109677','(+124.1563,+23.15636)','(-122.1563,-55.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4233032','(+101.1563,+83.15636)','(-5.156368,-88.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4342832','(-51.15636,+38.15636)','(+169.1563,+56.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4445842','(-73.15636,+4.156368)','(+17.15636,+18.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4557449','(+89.15636,-40.15636)','(-58.15636,+49.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4656655','(-74.15636,-36.15636)','(+12.15636,+63.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4745562','(+110.1563,-45.15636)','(+33.15636,+8.156368)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4826835','(+79.15636,+8.156368)','(-164.1563,-47.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('5042176','(-26.15639,-32.15639)','(-179.1563,+5.156395)','2019-07-24 08:56:15','2019-07-24 08:56:15'),('5941015','(+160.1563,-17.15639)','(+69.15639,-56.15639)','2019-07-24 08:56:15','2019-07-24 08:56:15'),('6718218','(+51.15639,+33.15639)','(+82.15639,-29.15639)','2019-07-24 08:56:15','2019-07-24 08:56:15'),('7594071','(+132.1563,-44.15639)','(+83.15639,+12.15639)','2019-07-24 08:56:15','2019-07-24 08:56:15'),('8269112','(-156.1563,-15.15639)','(-50.15639,+60.15639)','2019-07-24 08:56:15','2019-07-24 08:56:15'),('8663739','(+122.1563,+71.15639)','(+142.1563,+29.15639)','2019-07-24 08:56:14','2019-07-24 08:56:14'),('9039050','(+92.15639,+88.15639)','(-107.1563,+50.15639)','2019-07-24 08:56:15','2019-07-24 08:56:15');
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
INSERT INTO `pipeline_segment` VALUES ('123',1,'2044895','(+116.362003,+39.968636)','(+116.370771,+39.96847)','2019-07-27 10:49:14','2019-07-27 10:49:14'),('124',2,'2044895','(+116.370771,+39.96847)','(+116.369945,+39.964302)','2019-07-27 10:54:36','2019-07-28 13:07:19'),('8784854',3,'2044895','(-101.1564,+22.15643)','(-136.1564,-16.15643)','2019-07-29 09:49:49','2019-07-29 10:31:46'),('9095553',4,'2044895','(+96.15643,-67.15643)','(-36.15643,-58.15643)','2019-07-29 10:28:09','2019-07-29 10:31:46');
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
INSERT INTO `route_segment` VALUES ('200',1,'1188454','(+90.15644,-59.15644)','(+114.1564,+68.15644)','2019-07-29 11:33:35','2019-07-29 11:33:35'),('202',2,'1188454','(+69.15644,-70.15644)','(-171.1564,-20.15644)','2019-07-29 11:34:43','2019-07-29 11:36:11'),('203',3,'1188454','(-136.1564,+8.156440)','(+47.15644,-86.15644)','2019-07-29 11:35:04','2019-07-29 11:36:11'),('204',4,'1188454','(+99.15644,+84.15644)','(-87.15644,+4.156440)','2019-07-29 11:35:35','2019-07-29 11:36:11');
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
INSERT INTO `sea_route` VALUES ('1188454','110','(-72.15640,-54.15640)','(+0.156406,+23.15640)','2019-07-25 13:29:36','2019-07-28 10:11:57'),('2472838','110','(-161.1564,-26.15640)','(+88.15640,-79.15640)','2019-07-25 12:54:55','2019-07-25 12:58:20'),('6479668','110','(-118.1564,-37.15640)','(+51.15640,-69.15640)','2019-07-25 13:29:26','2019-07-28 10:43:05'),('8211997','111','(+41.15640,+50.15640)','(+71.15640,+26.15640)','2019-07-25 12:56:24','2019-07-25 12:58:20'),('8959983','112','(-114.1564,-1.156405)','(+22.15640,-74.15640)','2019-07-25 12:55:11','2019-07-25 12:55:11');
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
INSERT INTO `sensor_type` VALUES ('2926155','猪猪传感器','2019-07-24 09:15:43','2019-07-24 09:15:43'),('2926378','质量传感器','2019-07-24 09:16:47','2019-07-24 09:16:47'),('7752109','管压传感器','2019-07-24 09:16:17','2019-07-24 09:16:17');
/*!40000 ALTER TABLE `sensor_type` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2019-08-01 11:08:12
