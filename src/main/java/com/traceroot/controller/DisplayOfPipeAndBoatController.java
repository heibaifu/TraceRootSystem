package com.traceroot.controller;

import com.traceroot.dto.BoatDTO;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.impl.*;
import com.traceroot.vo.ResultVO;
import com.traceroot.converter.form2dto.PipeForm2PipeDTOConverter;
import com.traceroot.converter.form2dto.PipeSegmentForm2PipeSegmentDTOConverter;
import com.traceroot.converter.form2dto.PipelineSensorForm2SensorDTOConverter;
import com.traceroot.dataobject.Pipeline;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.form.PipeForm;
import com.traceroot.form.PipeSegmentForm;
import com.traceroot.form.PipelineSensorForm;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pipe-boat-display")
@Slf4j
public class DisplayOfPipeAndBoatController {

    @Autowired
    PipelineServiceImpl pipelineService;

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @Autowired
    PipelineSensorServiceImpl sensorService;

    @Autowired
    BoatServiceImpl boatService;


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

        List<PipeSegmentDTO> warningSegments=segmentService.selectByWarning();

        List<BoatDTO> boatDTOList = boatService.selectAllBoat();

        map.put("warningSegments", warningSegments);

        map.put("pipeSegmentList", pipeSegmentDTOList);

        map.put("sensorDTOList", sensorDTOList);

        map.put("boatDTOList", boatDTOList);

        return new ModelAndView("datatable.html", map);
    }



}
