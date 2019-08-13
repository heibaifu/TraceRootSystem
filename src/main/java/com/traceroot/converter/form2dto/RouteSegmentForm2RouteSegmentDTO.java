package com.traceroot.converter.form2dto;

import com.traceroot.dto.RouteSegmentDTO;
import com.traceroot.form.RouteSegmentForm;
import org.springframework.beans.BeanUtils;

public class RouteSegmentForm2RouteSegmentDTO {

    public static RouteSegmentDTO convert (RouteSegmentForm routeSegmentForm){

        RouteSegmentDTO routeSegmentDTO=new RouteSegmentDTO();
        BeanUtils.copyProperties(routeSegmentForm,routeSegmentDTO);

        return routeSegmentDTO;
    }

}
