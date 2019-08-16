package com.traceroot.aspect;

import com.traceroot.service.Websocket;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class UpdateAspect {

    @Autowired
    private Websocket websocket;

    @Pointcut("execution(public * com.traceroot.controller.*.save*(..))")
    public void update(){}

    @AfterReturning("update()")
    public void doUpdate(){
        websocket.sendMessage("update");
    }



}
