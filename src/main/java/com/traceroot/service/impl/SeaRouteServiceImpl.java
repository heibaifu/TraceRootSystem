package com.traceroot.service.impl;

import com.traceroot.dataobject.SeaRoute;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.repository.SeaRouteRepository;
import com.traceroot.service.ifs.SeaRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeaRouteServiceImpl implements SeaRouteService {

    @Autowired
    private SeaRouteRepository repository;

    @Override
    public SeaRoute insert(SeaRoute seaRoute) {

        return repository.save(seaRoute);
    }

    @Override
    public SeaRoute update(SeaRoute seaRoute) {
        SeaRoute result= repository.findByRouteId(seaRoute.getRouteId());
        if (result==null){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }
        return repository.save(seaRoute);
    }

    @Override
    public SeaRoute selectByRouteId(String routeId) {
        SeaRoute result= repository.findByRouteId(routeId);
        if (result==null){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }
        return result;
    }

    @Override
    public List<SeaRoute> selectByStatus(String status) {

        return repository.findByStatus(status);
    }

    @Override
    public void deleteByRouteId(String routeId) {
        SeaRoute seaRoute = repository.findByRouteId(routeId);
        if (seaRoute==null){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }
        repository.delete(seaRoute);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }

    @Override
    public void deleteByStatus(String status) {
        List<SeaRoute> seaRouteList = repository.findByStatus(status);
        if (seaRouteList.size()==0){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }
        repository.deleteAll(seaRouteList);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
    }
}
