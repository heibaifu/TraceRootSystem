package com.traceroot.controller;

import com.traceroot.dataobject.Upcreattime;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import com.traceroot.service.impl.PipelineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pipe")
@Slf4j
public class PipeManageControllerTest {

    @Autowired
    PipelineServiceImpl pipelineService;

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    //@GetMapping()
    /*public <OrderDTO> ModelAndView pipeManager(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            *//*@RequestParam(value = "badnodeid", defaultValue = "5") Integer badnodeid*//*
            Map<String, Object> map) {


        //List<PipeSegmentDTO> pipeSegmentDTOList = segmentService.selectAll();

        *//*List<PipeSegmentDTO> warningSegments=segmentService.selectByWarning();
        String[] badnodeid=new String[warningSegments.size()];
        for (int i=0;i<warningSegments.size();i++){
            badnodeid[i]=warningSegments.get(i).getSegmentId();
        }
        map.put("badnodeid", badnodeid);*//*


        //map.put();

        *//*return new ModelAndView("bmaptest1.html", map);*//*

    }*/

    public String pipeManager(Model model){

        List<Upcreattime> upcreattime=new ArrayList<>();
        upcreattime.add(new Upcreattime("1998-09-11","2010-12-21"));
        model.addAttribute("upcreattime",upcreattime);


        return "bmaptest1.html";
    }


}
