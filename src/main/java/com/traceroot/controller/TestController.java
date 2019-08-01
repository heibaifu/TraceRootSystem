package com.traceroot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/test1")
    public <OrderDTO> ModelAndView test1(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "badnodeid", defaultValue = "5") Integer badnodeid,
            Map<String, Object> map) {


        map.put("badnodeid", badnodeid);


        return new ModelAndView("bmaptest1.html", map);
    }


    @GetMapping("/test2")
    public ModelAndView test2(Map<String, Object> map) {
        return new ModelAndView("nav.html", map);
    }







/*    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "5") Integer size,

                             Map<String,Object> map){
        PageRequest request = PageRequest.of(page-1,size);  //起始位置不同，下标要-1
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }*/

}
