package com.traceroot.controller;

import com.traceroot.dto.PipeDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import com.traceroot.service.impl.PipelineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pipe")
@Slf4j
public class PipeManageController {

    @Autowired
    PipelineServiceImpl pipelineService;

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @GetMapping()
    public <OrderDTO> ModelAndView pipeManager(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "badnodeid", defaultValue = "5") Integer badnodeid,
            Map<String, Object> map) {


        List<PipeSegmentDTO> pipeSegmentDTOList = segmentService.selectAll();

        map.put("badnodeid", badnodeid);


        return new ModelAndView("bmaptest1.html", map);
    }
}
