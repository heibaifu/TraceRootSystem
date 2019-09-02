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
## 3.后端程序说明

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
│  traceroot.sql  //本项目的mysql数据库
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
   │  │          │          PipelineSegmentMultiKeys.java   //无用
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
   │  │          │  │      BoatPredictService.java    //用于提取大数据预测结果数据的serice
   │  │          │  │      BoatService.java           //船只本体数据处理
   │  │          │  │      BoatTraceService.java      //船只轨迹记录数据处理
   │  │          │  │      BoatTypeService.java       //船只种类查询
   │  │          │  │      CrossService.java          //交叉数据处理中心
   │  │          │  │      PipelineSegmentService.java   //管道段数据处理
   │  │          │  │      PipelineSensorService.java    //管道传感器数据处理
   │  │          │  │      PipelineService.java       //管道数据处理
   │  │          │  │      RouteSegmentService.java   //航线段数据处理
   │  │          │  │      SeaRouteService.java       //航线数据处理
   │  │          │  │      SensorStatusService.java   //管道传感器状态记录数据处理
   │  │          │  │      SensorTypeService.java     //传感器种类查询
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
   │  │          │      DateToLongSerializer.java  //将时间转换为long型的数的工具
   │  │          │      DoubleLocation.java        //用于经纬度坐标表示的类
   │  │          │      GeographyUtil.java         //地理相关工具，如坐标转换、距离测定、速度测定、方向测定、经纬度模糊匹配表达式构建等
   │  │          │      MathUtil.java     //数学相关工具，如判断条线段是否相交
   │  │          │      RandomUtil.java   //随机序列、随机地理位置生成工具
   │  │          │      ResultVOUtil.java //返回前端的数据的构造工具
   │  │          │      TimeUtil.java     //时间相关工具
   │  │          │      
   │  │          └─vo
   │  │                  BoatVO.java
   │  │                  ResultVO.java
   │  │                  RouteVO.java
   │  │                  SensorVO.java
   │  │                  ThreateningBoatVO.java
   │  │                  UpdateBoatVO.java         //更新数据后，websocket用VO
   │  │                  UpdatePipeSegmentVO.java  //更新数据后，websocket用VO
   │  │                  UpdateSensorVO.java       //更新数据后，websocket用VO
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
```
----
###

## 4.前端程序说明
### 功能说明
1.跳转功能（在下方的输入框输入经纬度可以定位到指定地方）  
2.查找路径（点击船只后可以使用查找路径功能查找指定船只的路径和航线）  
3.可疑船只（点击管道后可以使用可疑船只功能，输入时间区间以及查询精度可以查找与指定管道相关的船只，并显示相应的图表）  
4.隐藏/显示航线（可以一键显示或隐藏船只航线）  
5.隐藏/显示图表（可以一键显示或隐藏图表）  
6.右下角的界面刷新功能（可以选择手动刷新数据）  
7.通过websocket实时将数据库的更新显示到界面上  
8.点击地图界面上的标注可以弹出对应的信息窗口，点击管道还可以显示出对应传感器的值  
9.点击管道线路管理和船只线路管理可以切换显示图表  
10.鼠标悬浮在管道线路管理、船只线路管理和告警管道列表可以显示当前显示在地图界面上的管道、船只、告警管道id，点击对应的id可以跳转到相应的位置 

### 代码结构
前端开发的成果都在resources目录下，其中static目录里面有css、image（网页使用的图标）、js，templates目录里面是html文件，其中nav为导航界面，bmaptest1文件为实际的功能界面，datatable文件是用来存储后端发送的数据，并用来初始化功能界面

### 具体分析
其中nav界面是简单的静态界面，只涉及链接的跳转。  
datatable界面是在尝试使用thyme leaf框架进行前后端数据交互的过程中出现的产物，实际成形的项目不应出现这一界面。  
Bmaptest1界面是主功能界面，其初始数据由datatable界面提供（使用的是父子页面的方法，datatable界面为父页面）。该界面在使用的过程中与后端的通信是使用Ajax方法和websocket方法。使用websocket方法可以高效地监听后台数据变化，并显示在前端界面；  
使用Ajax方法可以根据用户的需求来向后台获取相应的数据。（具体的有查找路径、可疑船只、点击管道显示相应传感器数值的功能）  
Bmaptest1界面的代码行数过多不利于后续使用者维护代码（这前端写的***）  
从Bmaptest1的源码中可以看到有好多功能是重写了一次，是因为我不能熟练使用thyme leaf这一框架来直接传值给js，只能通过先传值给html，再把值传给js的方法实现（直接导致datatable界面的出现）；而使用Ajax方法可以直接传值给js，所以在很多的功能插件里面针对这两种不同的工具都编写了对应的代码。（这对于开发一个项目来说应该是无法接受的，但是当作一次课程设计的话应该是鼓励学生去使用新的东西的吧。）

### 常见问题说明
1.	后台数据库数据更新。如果是由传感器更新引起的管道状态的变化，在前端上是可以显示出来的，但是管道的颜色会比其他未变化数据的管道的颜色更加鲜艳一些（这都是什么神奇的bug）。不过好像是歪打正着的将数据出现变化的管道给高亮显示了。（呵呵…）  
2.	查找可疑船只的输入框需要输入的时间区间确实不利于使用者输入，应该设计一个更智能的方式，但是限于前端开发的同学（我）的捉急技术和已经没有的时间还是先鸽了吧。（好在提供了一个默认值，勉强能用）。  
3.	界面被缩放后可能导致一些显示组件无法正常显示（特指传感器显示模块（界面下方的那几个圆球）），原因是有一些模块是没有在界面加载好就进行显示的，当界面缩放的时候打开它，相当于是对它进行初始化，而之后再放大到正常大小后它还是会使用缩放时的大小。（刷新一下就好了嘛，再说谁没事天天缩放这个界面啊）  
实在不行可以用终极大法（右下角的刷新按钮，刷新可以更新datatable界面的数据（也是使用父子页面的原理），从而更新操作界面的数据）  

### 前端下一步规划
1.预测管道寿命（给出该管道下次出现故障的时间、原因）  
2.历史管道故障统计（画出每条管道历史出故障曲线）  
3.预测危险船只可能会在哪个时间对管道造成破坏  
4.完善传感器模块（目前的传感器显示模块只能最多显示4种传感器）  
5.开发更加高效的使用界面  
6.增加其他功能模块，如维修维护平台、海上钻井平台管理等  

  
