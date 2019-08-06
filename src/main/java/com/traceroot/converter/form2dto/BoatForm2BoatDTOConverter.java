package com.traceroot.converter.form2dto;

import com.traceroot.dto.BoatDTO;
import com.traceroot.form.BoatForm;
import org.springframework.beans.BeanUtils;

public class BoatForm2BoatDTOConverter {
    public static BoatDTO convert (BoatForm boatForm){

        BoatDTO boatDTO=new BoatDTO();
        BeanUtils.copyProperties(boatForm,boatDTO);

        boatDTO.setPresentLocation("("+boatForm.getPresentLocation()+")");

        return boatDTO;
    }
}
