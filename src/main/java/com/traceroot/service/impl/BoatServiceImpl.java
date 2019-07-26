package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.dataobject.BoatTrace;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.repository.BoatRepository;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.utils.DoubleLocation;
import com.traceroot.utils.LocationUtil;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatRepository repository;

    @Autowired
    private BoatTraceServiceImpl traceService;

    /**
     * 按照船只id查询
     * @param boatId
     * @return
     */
    @Override
    public Boat selectByBoatId(String boatId) {
        return repository.findByBoatId(boatId);
    }

    /**
     * 按照船只状态查询
     * @param status
     * @return
     */
    @Override
    public List<Boat> selectByStatus(String status) {
        return repository.findByStatus(status);
    }

    /**
     * 查询所有船只
     * @return
     */
    @Override
    public List<Boat> selectAllBoat() {
        return repository.findAll();
    }

    /**
     * 按航线查询船只
     * @param routeId
     * @return
     */
    @Override
    public List<Boat> selectByRoute(String routeId) {
        return repository.findByRouteId(routeId);
    }


    /**
     * 按船只类型查询船只
     * @param typeList
     * @return
     */
    @Override
    public List<Boat> selectByTypeIn(List<String> typeList) {
        return repository.findByTypeIn(typeList);
    }

    /**
     * 保存船只信息
     * @param boat
     * @return
     */
    @Override
    public Boat save(Boat boat) {
        BoatTrace boatTrace = new BoatTrace(RandomUtil.genUniqueId(),boat.getBoatId(),boat.getPresentLocation(),boat.getStatus());
        Boat save = repository.save(boat);
        traceService.insert(boatTrace);
        return save;
    }

    /**
     * 按照船只id删除船只
     * @param boatId
     * @return
     */
    @Override
    public Boolean deleteById(String boatId) {
        Boat boat = repository.findByBoatId(boatId);
        if (boat==null){
            throw new BoatException(ResultEnum.BOAT_NOT_EXIST);
        }
        repository.deleteById(boatId);
        log.info(ResultEnum.DELETE_SUCCESS.getMessage());
        return true;
    }


}
