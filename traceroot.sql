-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: traceroot
-- ------------------------------------------------------
-- Server version	8.0.12

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
  CONSTRAINT `BOAT` FOREIGN KEY (`route_id`) REFERENCES `sea_route` (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat`
--

LOCK TABLES `boat` WRITE;
/*!40000 ALTER TABLE `boat` DISABLE KEYS */;
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
  `trace_serial_number` int(11) NOT NULL AUTO_INCREMENT,
  `boat_id` varchar(10) NOT NULL,
  `record_location` varchar(30) DEFAULT NULL COMMENT '船只当前位置',
  `status` varchar(10) DEFAULT '未出航' COMMENT '船只状态',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`trace_serial_number`,`trace_id`),
  KEY `BOAT_TRACE` (`boat_id`),
  CONSTRAINT `BOAT_TRACE` FOREIGN KEY (`boat_id`) REFERENCES `boat` (`boat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='船只轨迹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat_trace`
--

LOCK TABLES `boat_trace` WRITE;
/*!40000 ALTER TABLE `boat_trace` DISABLE KEYS */;
/*!40000 ALTER TABLE `boat_trace` ENABLE KEYS */;
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
INSERT INTO `pipeline` VALUES ('1904289','(+168.1563,-43.15636)','(-149.1563,+61.15636)','2019-07-21 03:44:32','2019-07-21 03:46:56'),('2044895','(+44.15636,+49.15636)','(+108.1563,-5.156368)','2019-07-21 03:43:48','2019-07-21 03:43:48'),('2692013','(+30.15636,+64.15636)','(-36.15636,-17.15636)','2019-07-21 03:43:00','2019-07-21 03:43:00'),('4109677','(+124.1563,+23.15636)','(-122.1563,-55.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4233032','(+101.1563,+83.15636)','(-5.156368,-88.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4342832','(-51.15636,+38.15636)','(+169.1563,+56.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4445842','(-73.15636,+4.156368)','(+17.15636,+18.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4557449','(+89.15636,-40.15636)','(-58.15636,+49.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4656655','(-74.15636,-36.15636)','(+12.15636,+63.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4745562','(+110.1563,-45.15636)','(+33.15636,+8.156368)','2019-07-21 03:44:32','2019-07-21 03:44:32'),('4826835','(+79.15636,+8.156368)','(-164.1563,-47.15636)','2019-07-21 03:44:32','2019-07-21 03:44:32');
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
  `start` varchar(30) NOT NULL,
  `end` varchar(30) NOT NULL,
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
INSERT INTO `pipeline_segment` VALUES ('1326574',3,'1904289','(-176.1563,+87.15636)','(+52.15636,+24.15636)','2019-07-21 04:58:00','2019-07-21 04:58:00'),('1944616',1,'1904289','(+128.1563,+72.15636)','(-145.1563,+33.15636)','2019-07-21 04:36:16','2019-07-21 04:36:16'),('4776664',1,'2044895','(-51.15638,-77.15638)','(-1.156380,+63.15638)','2019-07-22 13:43:42','2019-07-22 13:43:42'),('4802483',5,'1904289','(+162.1563,+26.15638)','(+66.15638,-80.15638)','2019-07-22 14:19:03','2019-07-22 14:19:03'),('6057815',2,'1904289','(+127.1563,+35.15636)','(+16.15636,+34.15636)','2019-07-21 04:57:37','2019-07-21 04:57:37'),('9374453',4,'1904289','(-108.1563,+43.15638)','(+72.15638,+67.15638)','2019-07-22 13:42:40','2019-07-22 13:42:40');
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
  `type` varchar(10) NOT NULL,
  `location` varchar(30) NOT NULL,
  `present_status` varchar(10) DEFAULT '状态正常' COMMENT '传感器状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sensor_id`),
  KEY `PIPELINE_SENSOR1` (`pipe_id`),
  KEY `PIPELINE_SENSOR2` (`segment_id`),
  CONSTRAINT `PIPELINE_SENSOR1` FOREIGN KEY (`pipe_id`) REFERENCES `pipeline` (`pipe_id`),
  CONSTRAINT `PIPELINE_SENSOR2` FOREIGN KEY (`sensor_id`) REFERENCES `pipeline_segment` (`segment_id`)
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
  `segment_serial_number` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` varchar(10) NOT NULL,
  `start` varchar(30) NOT NULL,
  `end` varchar(30) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`segment_serial_number`,`segment_id`),
  KEY `ROUTE_SEGMENT` (`route_id`),
  CONSTRAINT `ROUTE_SEGMENT` FOREIGN KEY (`route_id`) REFERENCES `sea_route` (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管道地理位置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_segment`
--

LOCK TABLES `route_segment` WRITE;
/*!40000 ALTER TABLE `route_segment` DISABLE KEYS */;
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
  `source` varchar(10) NOT NULL,
  `destination` varchar(10) NOT NULL,
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
  `status` varchar(10) DEFAULT '状态正常' COMMENT '传感器状态',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录时间',
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-23 15:55:33
