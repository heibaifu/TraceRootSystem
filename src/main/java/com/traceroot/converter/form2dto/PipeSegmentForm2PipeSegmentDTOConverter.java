package com.traceroot.converter.form2dto;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traceroot.dataobject.SensorStatus;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.form.PipeSegmentForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PipeSegmentForm2PipeSegmentDTOConverter {

    public static PipeSegmentDTO convert(PipeSegmentForm segmentForm ){
        PipeSegmentDTO segmentDTO = new PipeSegmentDTO();

        segmentDTO.setPipeId(segmentForm.getPipeId());
        segmentDTO.setSegmentId(segmentForm.getSegmentId());
        segmentDTO.setStart(segmentForm.getStart());
        segmentDTO.setEnd(segmentForm.getEnd());
        segmentDTO.setSegmentSerialNumber(segmentForm.getSegmentSerialNumber());

        Gson gson = new Gson();
        if (segmentForm.getStatusList()!=null && !segmentForm.getStatusList().equals("")){
            List<SensorStatus> statusList = new ArrayList<>();
            try {
                statusList = gson.fromJson(segmentForm.getStatusList(),new TypeToken<List<SensorStatus>>(){}.getType());
            } catch (JsonSyntaxException e) {
                log.error("【对象转换】错误，string={}",segmentForm.getStatusList());
                throw new PipeException(ResultEnum.PARAM_ERROR);
            }

            segmentDTO.setStatusList(statusList);
        }

        return segmentDTO;
    }
}
