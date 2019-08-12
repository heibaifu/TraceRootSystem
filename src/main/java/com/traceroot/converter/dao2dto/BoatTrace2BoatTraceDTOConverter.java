package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dto.BoatTraceDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BoatTrace2BoatTraceDTOConverter {

    public static BoatTraceDTO convert(BoatTrace boatTrace){

        BoatTraceDTO boatTraceDTO = new BoatTraceDTO() ;
        BeanUtils.copyProperties(boatTrace,boatTraceDTO);

        return boatTraceDTO;
    }

    public static List<BoatTraceDTO> convert(List<BoatTrace> traceList ){
        return traceList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }
}
