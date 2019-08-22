package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.BoatPredict2BoatPredictDTOConverter;
import com.traceroot.dataobject.BoatPredict;
import com.traceroot.dto.BoatPredictDTO;
import com.traceroot.repository.BoatPredictRepository;
import com.traceroot.service.ifs.BoatPredictService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoatPredictServiceImpl implements BoatPredictService {

    @Autowired
    private BoatPredictRepository boatPredictRepository;

    @Override
    public BoatPredictDTO save(BoatPredictDTO boatPredictDTO) {

        BoatPredict boatPredict = new BoatPredict();
        BeanUtils.copyProperties(boatPredictDTO,boatPredict);

        return BoatPredict2BoatPredictDTOConverter.convert(boatPredictRepository.save(boatPredict));
    }

    @Override
    public List<BoatPredictDTO> selectByBoatId(String boatId) {

        List<BoatPredict> boatPredicts=boatPredictRepository.findByBoatIdOrderByCreateTimeDesc(boatId);
        List<BoatPredictDTO>boatPredictDTOS=BoatPredict2BoatPredictDTOConverter.convert(boatPredicts);
        return boatPredictDTOS;
    }
}
