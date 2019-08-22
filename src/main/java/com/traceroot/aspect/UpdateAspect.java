package com.traceroot.aspect;

import com.traceroot.dataobject.SensorStatus;
import com.traceroot.dto.*;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.form.*;
import com.traceroot.service.Websocket;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.ifs.PipelineSegmentService;
import com.traceroot.service.ifs.PipelineSensorService;
import com.traceroot.service.ifs.RouteSegmentService;
import com.traceroot.vo.UpdateBoatVO;
import com.traceroot.vo.UpdatePipeSegmentVO;
import com.traceroot.vo.UpdateSensorVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Aspect
@Slf4j
@Component
public class UpdateAspect {

    @Autowired
    private Websocket websocket;

    @Autowired
    BoatService boatService;

    @Autowired
    PipelineSegmentService segmentService;

    @Autowired
    PipelineSensorService sensorService;

    @Autowired
    RouteSegmentService routeSegmentService;

    private Date createTime;

    /**
     * 绕过缓存机制拿取createTime
     * @param joinPoint
     */
    @Before("execution(public * com.traceroot.controller.*.save*(..))")
    public void takeCreateTime(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        Object result = null;

        if (BoatForm.class.isInstance(args[0])){
            BoatForm arg = (BoatForm) args[0];
            BoatDTO boatDTO = boatService.selectByBoatId(arg.getBoatId());
            if (boatDTO != null){
                boatDTO.setCreateTime(new Date(boatDTO.getCreateTime().getTime())); //转换时间格式
                createTime = boatDTO.getCreateTime();
            }
        } else if (PipeSegmentForm.class.isInstance(args[0])){
            PipeSegmentForm arg = (PipeSegmentForm) args[0];
            PipeSegmentDTO pipeSegmentDTO = segmentService.selectBySegmentId(arg.getSegmentId());
            if (pipeSegmentDTO != null){
                pipeSegmentDTO.setCreateTime(new Date(pipeSegmentDTO.getCreateTime().getTime()));   //转换时间格式
                createTime = pipeSegmentDTO.getCreateTime();
            }
        } else if (PipelineSensorForm.class.isInstance(args[0])) {
            PipelineSensorForm arg = (PipelineSensorForm) args[0];
            PipelineSensorDTO pipelineSensorDTO = sensorService.selectBySensorId(arg.getSensorId());
            if (pipelineSensorDTO != null){
                pipelineSensorDTO.setCreateTime(new Date(pipelineSensorDTO.getCreateTime().getTime()));   //转换时间格式
                createTime = pipelineSensorDTO.getCreateTime();
            }
        } else if(RouteSegmentForm.class.isInstance(args[0])){
            RouteSegmentForm arg = (RouteSegmentForm) args[0];
            RouteSegmentDTO routeSegmentDTO = routeSegmentService.selectBySegmentId(arg.getSegmentId());
            if (routeSegmentDTO != null){
                routeSegmentDTO.setCreateTime(new Date(routeSegmentDTO.getCreateTime().getTime()));   //转换时间格式
                createTime = routeSegmentDTO.getCreateTime();
            }
        }
    }

    /**
     * 插入新数据则发送新数据给前端
     * @param joinPoint
     */
    @AfterReturning(pointcut="execution(public * com.traceroot.controller.*.save*(..))")
    public void updateData(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        Object result = null;

        if (BoatForm.class.isInstance(args[0])){
            BoatForm arg = (BoatForm) args[0];
            String code = "1";    //区分是新船(1)还是旧船(0)
            BoatDTO boatDTO = boatService.selectByBoatId(arg.getBoatId());
            //todo 这里可能是因为Jpa一级缓存机制的原因，查询结果的createTime为null
            if (boatDTO.getCreateTime() == null){
                boatDTO.setCreateTime(createTime);
                code = "0";
            }
            List<BoatTraceDTO> boatTraces = boatDTO.getBoatTraces();

            UpdateBoatVO updateBoatVO = new UpdateBoatVO();
            BeanUtils.copyProperties(boatDTO,updateBoatVO);
            updateBoatVO.setCode(code);
            if (boatTraces.size()>1){
                updateBoatVO.setLastLocation(boatTraces.get(boatTraces.size()-2).getRecordLocation());
            }
            result = updateBoatVO;
        } else if (PipeSegmentForm.class.isInstance(args[0])){
            PipeSegmentForm arg = (PipeSegmentForm) args[0];
            PipeSegmentDTO pipeSegmentDTO = segmentService.selectBySegmentId(arg.getSegmentId());
            if (pipeSegmentDTO.getCreateTime() == null){
                pipeSegmentDTO.setCreateTime(createTime);
            }

            UpdatePipeSegmentVO updatePipeSegmentVO = new UpdatePipeSegmentVO();
            BeanUtils.copyProperties(pipeSegmentDTO,updatePipeSegmentVO);
            result = updatePipeSegmentVO;
        } else if (PipelineSensorForm.class.isInstance(args[0])) {
            PipelineSensorForm arg = (PipelineSensorForm) args[0];
            PipelineSensorDTO pipelineSensorDTO = sensorService.selectBySensorId(arg.getSensorId());
            if (pipelineSensorDTO.getCreateTime() == null){
                pipelineSensorDTO.setCreateTime(createTime);
            }
            UpdateSensorVO sensorVO = new UpdateSensorVO();
            BeanUtils.copyProperties(pipelineSensorDTO,sensorVO);
            //判断管道状态，满足前端的各种奇怪的需求
            List<SensorStatus> statusList = pipelineSensorDTO.getStatusList();
            if (pipelineSensorDTO.getPresentStatus().equals(SensorStatusEnum.BROKEN.getCode())){
                sensorVO.setFlag("2");
            } else if (statusList.size() == 1){
                sensorVO.setFlag(segmentService.judgeStatus(sensorVO.getSegmentId()).toString());
            } else {
                String lastStatus = statusList.get(1).getStatus();
                log.warn("lastStatus :" + lastStatus);
                log.warn("presentStatus :" + sensorVO.getPresentStatus());
                if (lastStatus.equals(sensorVO.getPresentStatus())){
                    sensorVO.setFlag("3");
                }
                else {
                    sensorVO.setFlag(segmentService.judgeStatus(sensorVO.getSegmentId()).toString());
                }
            }
            PipeSegmentDTO pipeSegmentDTO = segmentService.selectBySegmentId(sensorVO.getSegmentId());
            sensorVO.setSegmentStart(pipeSegmentDTO.getStart());
            sensorVO.setSegmentEnd(pipeSegmentDTO.getEnd());
            sensorVO.setSensorLocation(pipelineSensorDTO.getLocation());
            result = sensorVO;
        } else if(RouteSegmentForm.class.isInstance(args[0])){
            RouteSegmentForm arg = (RouteSegmentForm) args[0];
            //todo
        } else {
            log.warn("嗷嗷嗷");
        }

        websocket.sendMessage(result);

    }





}
