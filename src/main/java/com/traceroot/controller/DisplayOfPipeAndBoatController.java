package com.traceroot.controller;

import com.traceroot.dto.*;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.service.ifs.*;
import com.traceroot.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pipe-boat-display")
@Slf4j
public class DisplayOfPipeAndBoatController {

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private PipelineSegmentService segmentService;

    @Autowired
    private PipelineSensorService sensorService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private SeaRouteService seaRouteService;

    @Autowired
    private RouteSegmentService routeSegmentService;


    @GetMapping()
    public ModelAndView pipeIndex(Map<String, Object> map) {
        return new ModelAndView("bmaptest1.html", map);
    }


    @GetMapping("/datatable")
    public ModelAndView datatableInsert(
            /*@RequestParam(value = "badnodeid", defaultValue = "5") Integer badnodeid*/
            Map<String, Object> map) {

        List<PipeSegmentDTO> pipeSegmentDTOList = segmentService.selectAll();

        List<PipelineSensorDTO> sensorDTOList = sensorService.selectAll();

        List<PipeSegmentDTO> warningSegments=segmentService.selectBySensorStatus(SensorStatusEnum.ABNORMAL);

        List<PipeSegmentDTO> unknownSegments=segmentService.selectBySensorStatus(SensorStatusEnum.BROKEN);

        List<BoatDTO> boatDTOList = boatService.selectAllBoat();

        List<RouteSegmentDTO>routeSegmentDTOS=routeSegmentService.selectAll();

        map.put("warningSegments", warningSegments);

        map.put("pipeSegmentList", pipeSegmentDTOList);

        map.put("unknownSegments", unknownSegments);

        map.put("sensorDTOList", sensorDTOList);

        map.put("boatDTOList", boatDTOList);

        map.put("routeSegmentDTOS",routeSegmentDTOS); //船只航线段

        return new ModelAndView("datatable.html", map);
    }



}
