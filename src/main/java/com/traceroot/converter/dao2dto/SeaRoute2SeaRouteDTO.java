package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.SeaRoute;
import com.traceroot.dto.SeaRouteDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SeaRoute2SeaRouteDTO {

    public static SeaRouteDTO convert(SeaRoute seaRoute){
        if (seaRoute == null){
            return null;
        }
        SeaRouteDTO seaRouteDTO = new SeaRouteDTO() ;

        BeanUtils.copyProperties(seaRoute,seaRouteDTO);

        return seaRouteDTO;
    }

    public static List<SeaRouteDTO> convert(List<SeaRoute> seaRouteList ) {
        return seaRouteList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
