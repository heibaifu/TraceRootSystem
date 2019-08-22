package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.BoatPredict;
import com.traceroot.dto.BoatPredictDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BoatPredict2BoatPredictDTOConverter {

    public static BoatPredictDTO convert(BoatPredict boatPredict){

        BoatPredictDTO boatDTO = new BoatPredictDTO() ;

        BeanUtils.copyProperties(boatPredict,boatDTO);

        return boatDTO;
    }

    public static List<BoatPredictDTO> convert(List<BoatPredict> boatPredicts ){
        return boatPredicts.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }
}
