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
  `direction` varchar(20) DEFAULT NULL COMMENT '航行方向',
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
INSERT INTO `boat` VALUES ('9001','002','121.12636,38.822983','4787.867',NULL,'1.7892456727498558','150','802','2019-09-02 08:51:11','2019-09-02 08:51:59'),('9002','002','119.592485,39.905986','4203.801',NULL,'5.6110209377233025','150',NULL,'2019-09-02 08:52:58','2019-09-02 08:54:37'),('903','001','120.456727,37.752791','3564.606',NULL,'2.018139547974648','150','803','2019-09-02 08:56:06','2019-09-02 08:56:45');
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
  `direction` varchar(20) DEFAULT NULL COMMENT '航行方向',
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
INSERT INTO `boat_trace` VALUES ('0634783',2,'9002','119.370568,39.103412','1439.410',NULL,'1.6051057173313503','150','2019-09-02 08:53:45'),('2252414',4,'9002','119.592485,39.905986','4203.801',NULL,'5.6110209377233025','150','2019-09-02 08:54:37'),('4678521',1,'9002','118.587533,39.11685',NULL,NULL,NULL,'150','2019-09-02 08:52:58'),('6106961',2,'9001','121.12636,38.822983','4787.867',NULL,'1.7892456727498558','150','2019-09-02 08:51:59'),('7186311',3,'9002','120.086913,39.595331','2170.239',NULL,'0.6293454926722051','150','2019-09-02 08:54:24'),('7651494',1,'9001','118.552463,39.108675',NULL,NULL,NULL,'150','2019-09-02 08:51:11'),('9497445',2,'903','120.456727,37.752791','3564.606',NULL,'2.018139547974648','150','2019-09-02 08:56:45'),('9531511',1,'903','118.980775,38.106859',NULL,NULL,NULL,'150','2019-09-02 08:56:06');
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
INSERT INTO `pipeline` VALUES ('00001','117.958593,38.379074','122.006286,40.683372','2019-09-02 07:12:47','2019-09-02 07:12:47'),('00002','119.12966,37.219835','121.126593,38.81029','2019-09-02 08:11:05','2019-09-02 08:11:05');
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
INSERT INTO `pipeline_segment` VALUES ('10001',1,'00001','117.958593,38.379074','122.006286,40.683372','2019-09-02 07:18:18','2019-09-02 07:18:18'),('10002',2,'00001','117.958593,38.379074','117.829237,38.307532','2019-09-02 07:34:24','2019-09-02 07:34:24'),('10003',3,'00001','117.829237,38.307532','117.774692,38.312288','2019-09-02 07:45:16','2019-09-02 07:45:16'),('10004',4,'00001','117.829237,38.307532','117.804229,38.290201','2019-09-02 07:46:09','2019-09-02 07:46:50'),('10005',5,'00001','122.006286,40.683372','122.078582,40.753309','2019-09-02 07:56:24','2019-09-02 07:56:24'),('20001',1,'00002','119.12966,37.219835','121.126593,38.81029','2019-09-02 08:11:21','2019-09-02 08:11:21'),('20002',2,'00002','121.126593,38.81029','121.128264,38.832806','2019-09-02 08:14:01','2019-09-02 08:14:01'),('20003',3,'00002','121.128264,38.832806','121.148782,38.833439','2019-09-02 08:16:01','2019-09-02 08:16:01'),('20004',4,'00002','119.12966,37.219835','119.068809,37.149295','2019-09-02 08:17:20','2019-09-02 08:17:20');
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
INSERT INTO `pipeline_sensor` VALUES ('1001','10001','00001','2926378','117.958593,38.379074','100','0.23','2019-09-02 07:25:53','2019-09-02 07:31:23'),('1002','10001','00001','7752109','122.006286,40.683372','100','0.57','2019-09-02 07:30:01','2019-09-02 07:30:01'),('1003','10002','00001','7752109','117.829237,38.307532','102','0.01','2019-09-02 07:43:24','2019-09-02 07:43:24'),('1004','10005','00001','7752109','122.078582,40.753309','102','0.78','2019-09-02 07:57:41','2019-09-02 07:57:41'),('2001','20001','00002','3333333','121.126593,38.81029','100','0.48','2019-09-02 08:24:48','2019-09-02 08:25:39'),('2002','20001','00002','7752109','119.12966,37.219835','102','0.67','2019-09-02 08:30:20','2019-09-02 08:30:20');
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
INSERT INTO `sensor_status` VALUES ('0535048','1001','100','0.23','2019-09-02 07:25:53'),('1443648','2001','100','0.48','2019-09-02 08:25:39'),('1541060','1004','102','0.78','2019-09-02 07:57:41'),('1839070','2002','102','0.67','2019-09-02 08:30:20'),('2565046','1002','100','0.57','2019-09-02 07:30:01'),('7079201','1003','102','0.01','2019-09-02 07:43:24');
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

-- Dump completed on 2019-09-02 17:00:12
