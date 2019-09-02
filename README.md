# TraceRootSystem 中海油溯源系统v0.3

## 1.软件功能
本项目旨在提供一个综合的海洋输油管道监测和预警平台，实现对石油管道及周边船只的状态监控，并用大数据分析实现对可能触发的故障的预测。
### 粗略来看，项目目前实现的功能有：
#### (1).船只显示、状态检测及记录
包括但不限于，船只状态查询、动态更新，船只种类、航线等相关信息的记录，船只位置实时显示，速度、方向测量，超速判断，历史轨迹及实时状态记录
#### (2).管道显示、状态检测及记录
包括但不限于，管道传感器状态实时更新、传感器状态及种类记录、传感器历史查询，正常管道与损坏管道统计，管道损坏记录时间序列(可以用来拟合成曲线)，按时间区间和地理范围查询统计范围内穿过管道的船只的频次、列举最有可能造成破坏的船只
#### (3).航线显示及相关信息存储
包括航线状态、航线不同区段速度限制记录和显示
### 待开发的功能：
#### (1).危险船只预测
通过历史大数据，预测可能未来某个时刻该船只将会对哪条管道造成破坏
#### (2).历史管道故障统计
画出每条管道历史故障曲线（历史故障记录时间序列的查询在service层中已经提供）
#### (3).管道未来故障预测
拟合上述曲线，给出该管道下一个出故障时间点

----
## 2.软件开发工具及环境
#### 后端：Java开发，SpringBoot框架+Jpa  
#### 前端：用HTML、CSS及JS，框架部分使用了jquary -3.3.1，thyme leaf，辅以调用百度地图的api和echart的api  
#### 主要开发工具：IDE采用IDEA，数据库采用MySQL，其他辅助工具Dreamweaver、postman、谷歌浏览器  
#### 操作系统：Windows10  

----
## 3.代码目录结构

### 后端代码目录结构描述
```
.
│  .gitignore
│  HELP.md
│  list.txt
│  mvnw
│  mvnw.cmd
│  pom.xml
│  TraceRoot.iml
│  traceroot.sql
│          
├─src
   ├─main
   │  ├─java
   │  │  └─com
   │  │      └─traceroot
   │  │          │  TraceRootApplication.java
   │  │          │  
   │  │          ├─aspect
   │  │          │      UpdateAspect.java
   │  │          │      
   │  │          ├─config
   │  │          │      WebsocketConfig.java
   │  │          │      
   │  │          ├─controller
   │  │          │      BoatManageController.java
   │  │          │      DisplayOfPipeAndBoatController.java
   │  │          │      IndexController.java
   │  │          │      PipeManagerController.java
   │  │          │      SeaRouteController.java
   │  │          │       
   │  │          ├─converter  //转换工具
   │  │          │  ├─dao2dto
   │  │          │  │      Boat2BoatDTOConverter.java
   │  │          │  │      BoatPredict2BoatPredictDTOConverter.java
   │  │          │  │      BoatTrace2BoatTraceDTOConverter.java
   │  │          │  │      Pipeline2PipeDTOConverter.java
   │  │          │  │      PipelineSegment2PipeSegmentDTOConverter.java
   │  │          │  │      PipelineSensor2SensorDTOConverter.java
   │  │          │  │      RouteSegment2RouteSegmentDTO.java
   │  │          │  │      SeaRoute2SeaRouteDTO.java
   │  │          │  │      SensorType2SensorTypeDTOConverter.java
   │  │          │  │      
   │  │          │  └─form2dto
   │  │          │          BoatForm2BoatDTOConverter.java
   │  │          │          PipeForm2PipeDTOConverter.java
   │  │          │          PipelineSensorForm2SensorDTOConverter.java
   │  │          │          PipeSegmentForm2PipeSegmentDTOConverter.java
   │  │          │          RouteSegmentForm2RouteSegmentDTO.java
   │  │          │          SeaRouteForm2SeaRouteDTO.java
   │  │          │          
   │  │          ├─dataobject //对应数据库的12张表
   │  │          │  │  Boat.java
   │  │          │  │  BoatPredict.java
   │  │          │  │  BoatTrace.java
   │  │          │  │  BoatType.java
   │  │          │  │  Pipeline.java
   │  │          │  │  PipelineSegment.java
   │  │          │  │  PipelineSegmentPredict.java
   │  │          │  │  PipelineSensor.java
   │  │          │  │  RouteSegment.java
   │  │          │  │  SeaRoute.java
   │  │          │  │  SensorStatus.java
   │  │          │  │  SensorType.java
   │  │          │  │  
   │  │          │  └─multikeysclass
   │  │          │          PipelineSegmentMultiKeys.java
   │  │          │          
   │  │          ├─dto
   │  │          │      BoatDTO.java
   │  │          │      BoatPredictDTO.java
   │  │          │      BoatTraceDTO.java
   │  │          │      PipeDTO.java
   │  │          │      PipelineSensorDTO.java
   │  │          │      PipeSegmentDTO.java
   │  │          │      RouteSegmentDTO.java
   │  │          │      SeaRouteDTO.java
   │  │          │      SensorTypeDTO.java
   │  │          │      
   │  │          ├─enums
   │  │          │      BoatStatusEnum.java
   │  │          │      ResultEnum.java
   │  │          │      SeaRouteStatusEnum.java
   │  │          │      SensorStatusEnum.java
   │  │          │      
   │  │          ├─exception
   │  │          │      BoatException.java
   │  │          │      PipeException.java
   │  │          │      RouteException.java
   │  │          │      TraceRootException.java
   │  │          │      
   │  │          ├─form
   │  │          │      BoatForm.java
   │  │          │      BoatPredictForm.java
   │  │          │      PipeForm.java
   │  │          │      PipelineSensorForm.java
   │  │          │      PipeSegmentForm.java
   │  │          │      RouteSegmentForm.java
   │  │          │      SeaRouteForm.java
   │  │          │      
   │  │          ├─repository
   │  │          │      BoatPredictRepository.java
   │  │          │      BoatRepository.java
   │  │          │      BoatTraceRepository.java
   │  │          │      BoatTypeRepository.java
   │  │          │      PipelineRepository.java
   │  │          │      PipelineSegmentRepository.java
   │  │          │      PipelineSensorRepository.java
   │  │          │      RouteSegmentRepository.java
   │  │          │      SeaRouteRepository.java
   │  │          │      SensorStatusRepository.java
   │  │          │      SensorTypeRepository.java
   │  │          │      
   │  │          ├─service
   │  │          │  │  FaultHistory.java
   │  │          │  │  ServerEncoder.java
   │  │          │  │  Websocket.java
   │  │          │  │  
   │  │          │  ├─ifs
   │  │          │  │      BoatPredictService.java
   │  │          │  │      BoatService.java
   │  │          │  │      BoatTraceService.java
   │  │          │  │      BoatTypeService.java
   │  │          │  │      CrossService.java
   │  │          │  │      PipelineSegmentService.java
   │  │          │  │      PipelineSensorService.java
   │  │          │  │      PipelineService.java
   │  │          │  │      RouteSegmentService.java
   │  │          │  │      SeaRouteService.java
   │  │          │  │      SensorStatusService.java
   │  │          │  │      SensorTypeService.java
   │  │          │  │      
   │  │          │  └─impl
   │  │          │          BoatPredictServiceImpl.java
   │  │          │          BoatServiceImpl.java
   │  │          │          BoatTraceServiceImpl.java
   │  │          │          BoatTypeServiceImpl.java
   │  │          │          CrossServiceImpl.java
   │  │          │          PipelineSegmentServiceImpl.java
   │  │          │          PipelineSensorServiceImpl.java
   │  │          │          PipelineServiceImpl.java
   │  │          │          RouteSegmentServiceImpl.java
   │  │          │          SeaRouteServiceImpl.java
   │  │          │          SensorStatusServiceImpl.java
   │  │          │          SensorTypeServiceImpl.java
   │  │          │          
   │  │          ├─utils
   │  │          │      DateToLongSerializer.java
   │  │          │      DoubleLocation.java
   │  │          │      GeographyUtil.java
   │  │          │      MathUtil.java
   │  │          │      RandomUtil.java
   │  │          │      ResultVOUtil.java
   │  │          │      TimeUtil.java
   │  │          │      
   │  │          └─vo
   │  │                  BoatVO.java
   │  │                  ResultVO.java
   │  │                  RouteVO.java
   │  │                  SensorVO.java
   │  │                  ThreateningBoatVO.java
   │  │                  UpdateBoatVO.java
   │  │                  UpdatePipeSegmentVO.java
   │  │                  UpdateSensorVO.java
   │  │                  
   │  └─resources
   │      │  application-dev.yml
   │      │  application.yml
   │      │  
   │      ├─static
   │      │  ├─css
   │      │  │      style.css
   │      │  │      
   │      │  ├─image
   │      │  │      bg.jpg
   │      │  │      boat.png
   │      │  │      boatd.png
   │      │  │      boate.png
   │      │  │      boatw.png
   │      │  │      chuanganqic.png
   │      │  │      chuanganqie.png
   │      │  │      chuanganqiw.png
   │      │  │      jiantou.png
   │      │  │      marker.png
   │      │  │      
   │      │  └─js
   │      │          bmapset.js
   │      │          changec.js
   │      │          echarts-liquidfill.min.js
   │      │          echarts.min.js
   │      │          jquery-3.3.1.js
   │      │          transdata.js
   │      │          
   │      └─templates
   │              bmaptest1.html
   │              datatable.html
   │              nav.html
   │              
   └─test                        
   
----
##
  
