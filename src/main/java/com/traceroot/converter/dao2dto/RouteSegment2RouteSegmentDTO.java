package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dto.RouteSegmentDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RouteSegment2RouteSegmentDTO {

    public static RouteSegmentDTO convert(RouteSegment routeSegment){
        if (routeSegment == null){
            return null;
        }
        RouteSegmentDTO routeSegmentDTO = new RouteSegmentDTO() ;

        BeanUtils.copyProperties(routeSegment,routeSegmentDTO);

        return routeSegmentDTO;
    }

    public static List<RouteSegmentDTO> convert(List<RouteSegment> routeSegmentList ) {
        return routeSegmentList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
