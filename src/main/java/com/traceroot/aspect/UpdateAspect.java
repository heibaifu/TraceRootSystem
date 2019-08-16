package com.traceroot.aspect;

import com.traceroot.converter.form2dto.BoatForm2BoatDTOConverter;
import com.traceroot.dto.BoatDTO;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.form.BoatForm;
import com.traceroot.service.Websocket;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.vo.UpdateBoatVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Slf4j
@Component
public class UpdateAspect {

    @Autowired
    private Websocket websocket;

    @Autowired
    BoatService boatService;

    public void update(){}

    @AfterReturning(pointcut="execution(public * com.traceroot.controller.*.save*(..))")
    public void UpdateBoat(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        BoatForm arg = (BoatForm) args[0];
        BoatDTO boatDTO = boatService.selectByBoatId(arg.getBoatId());
        List<BoatTraceDTO> boatTraces = boatDTO.getBoatTraces();
        UpdateBoatVO updateBoatVO = new UpdateBoatVO();
        BeanUtils.copyProperties(boatDTO,updateBoatVO);
        if (boatTraces.size()>1){
            updateBoatVO.setLastLocation(boatTraces.get(boatTraces.size()-1).getRecordLocation());
        }

        websocket.sendMessage(updateBoatVO);
    }





}
