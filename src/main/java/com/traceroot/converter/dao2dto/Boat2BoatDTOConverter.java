package com.traceroot.converter.dao2dto;

import com.traceroot.dataobject.Boat;
import com.traceroot.dto.BoatDTO;
import com.traceroot.utils.LocationUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Boat2BoatDTOConverter {

    public static BoatDTO convert(Boat boat){

        BoatDTO boatDTO = new BoatDTO() ;

        BeanUtils.copyProperties(boat,boatDTO);

        return boatDTO;
    }

    public static List<BoatDTO> convert(List<Boat> boatList ){
        return boatList.stream()
                .map(e ->convert(e))
                .collect(Collectors.toList());
    }

}
