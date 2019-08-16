package com.traceroot.aspect;

import com.traceroot.service.Websocket;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public aspect DataUpdateAspect {

    @Autowired
    private Websocket websocket;

    @Pointcut("execution(public * com.traceroot.controller.save*(..))")
    public void update(){}

    @AfterReturning("update()")
    public void doUpdate(){
        websocket.sendMessage("大厦狗");
    }


}
