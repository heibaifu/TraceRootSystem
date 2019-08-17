package com.traceroot.aspect;

import com.traceroot.converter.form2dto.BoatForm2BoatDTOConverter;
import com.traceroot.dto.*;
import com.traceroot.form.BoatForm;
import com.traceroot.form.PipeSegmentForm;
import com.traceroot.form.PipelineSensorForm;
import com.traceroot.form.SeaRouteForm;
import com.traceroot.service.Websocket;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.ifs.PipelineSegmentService;
import com.traceroot.service.ifs.PipelineSensorService;
import com.traceroot.service.ifs.RouteSegmentService;
import com.traceroot.vo.SensorVO;
import com.traceroot.vo.UpdateBoatVO;
import com.traceroot.vo.UpdatePipeSegmentVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
        } else if(SeaRouteForm.class.isInstance(args[0])){
            SeaRouteForm arg = (SeaRouteForm) args[0];
            //todo
            RouteSegmentDTO routeSegmentDTO = routeSegmentService.selectBySegmentId(arg.getRouteId());
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

            SensorVO sensorVO = new SensorVO();
            BeanUtils.copyProperties(pipelineSensorDTO,sensorVO);
            result = sensorVO;
        } else if(SeaRouteForm.class.isInstance(args[0])){
            SeaRouteForm arg = (SeaRouteForm) args[0];
            //todo
        } else {
            log.warn("嗷嗷嗷");
        }

        websocket.sendMessage(result);

    }





}
