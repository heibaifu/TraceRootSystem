package com.traceroot.converter.form2dto;

import com.traceroot.dto.SeaRouteDTO;
import com.traceroot.form.SeaRouteForm;
import org.springframework.beans.BeanUtils;

public class SeaRouteForm2SeaRouteDTO {

    public static SeaRouteDTO convert (SeaRouteForm seaRouteForm){

        SeaRouteDTO seaRouteDTO=new SeaRouteDTO();
        BeanUtils.copyProperties(seaRouteForm,seaRouteDTO);

        return seaRouteDTO;
    }

}
