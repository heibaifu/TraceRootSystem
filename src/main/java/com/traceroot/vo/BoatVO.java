package com.traceroot.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.dto.SeaRouteDTO;
import lombok.Data;

import java.util.List;

@Data
public class BoatVO {

    private List<BoatTraceDTO> traceDTOList;

    private SeaRouteDTO seaRouteDTO;

    @JsonIgnore
    public BoatVO(List<BoatTraceDTO> traceDTOList, SeaRouteDTO seaRouteDTO) {
        this.traceDTOList = traceDTOList;
        this.seaRouteDTO = seaRouteDTO;
    }

    public BoatVO() {
    }
}
