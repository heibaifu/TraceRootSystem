package com.traceroot.service.impl;

import com.traceroot.dataobject.BoatType;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.repository.BoatTypeRepository;
import com.traceroot.service.ifs.BoatTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoatTypeServiceImpl implements BoatTypeService {

    @Autowired
    private BoatTypeRepository repository;

    @Override
    public BoatType insert(BoatType boatType) {

        return repository.save(boatType);
    }

    @Override
    public BoatType updateByTypeId(String typeId, String typeName){

        BoatType result=repository.findByTypeId(typeId);
        if (result==null){
            throw new RouteException(ResultEnum.BOAT_TYPE_NOT_EXIST);
        }
        result.setTypeName(typeName);
        return repository.save(result);
    }

    @Override
    public BoatType selectByTypeId(String typeId) {

        return repository.findByTypeId(typeId);
    }

    @Override
    public BoatType selectByTypeName(String typeName) {
        return repository.findByTypeName(typeName);
    }

    @Override
    public void deleteByTypeId(String typeId) {
        BoatType boatType=repository.findByTypeId(typeId);
        if (boatType==null){
            throw new RouteException(ResultEnum.BOAT_TYPE_NOT_EXIST);
        }
        repository.delete(boatType);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
