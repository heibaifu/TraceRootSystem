package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.SeaRoute2SeaRouteDTO;
import com.traceroot.dataobject.SeaRoute;
import com.traceroot.dto.SeaRouteDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.repository.SeaRouteRepository;
import com.traceroot.service.ifs.SeaRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeaRouteServiceImpl implements SeaRouteService {

    @Autowired
    private SeaRouteRepository repository;

    @Autowired
    private RouteSegmentServiceImpl routeSegmentService;

    @Override
    public SeaRouteDTO insert(SeaRouteDTO seaRouteDTO) {
        SeaRoute seaRoute=new SeaRoute();
        BeanUtils.copyProperties(seaRouteDTO,seaRoute);
        SeaRouteDTO result= SeaRoute2SeaRouteDTO.convert(repository.save(seaRoute));
        return result;
    }

    /*@Override
    public SeaRouteDTO update(SeaRouteDTO seaRouteDTO) {
        SeaRoute result= repository.findByRouteId(seaRoute.getRouteId());
        if (result==null){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }
        return repository.save(seaRoute);
    }*/

    @Override
    public SeaRouteDTO selectByRouteId(String routeId) {
        SeaRoute seaRoute= repository.findByRouteId(routeId);
        if (seaRoute==null){
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
        }

        SeaRouteDTO seaRouteDTO=SeaRoute2SeaRouteDTO.convert(seaRoute);
        seaRouteDTO.setRouteSegments(routeSegmentService.selectByRouteId(routeId));

        return seaRouteDTO;
    }

    @Override
    public List<SeaRouteDTO> selectByStatus(String status) {

        List<SeaRouteDTO> seaRouteDTOS=SeaRoute2SeaRouteDTO.convert(repository.findByStatus(status));
        return seaRouteDTOS;
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
