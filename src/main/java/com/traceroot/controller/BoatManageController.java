package com.traceroot.controller;

import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.service.impl.BoatTraceServiceImpl;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boat")
@Slf4j
public class BoatManageController {

    @Autowired
    BoatTraceServiceImpl traceService;

    @ResponseBody
    @GetMapping("/findtrace")
    public ResultVO<List<BoatTraceDTO>> boatTraceMsg(@RequestParam(value = "boatid",required = true)String boatId){
        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(boatId);
        if (traceDTOList.size()==0){
            log.error("【查找船只轨迹】船只轨迹不存在，boatid={}",boatId);
            throw new BoatException(ResultEnum.BOAT_TRACE_NOT_EXIST);
        }
        return ResultVOUtil.success(traceDTOList);
    }

}
