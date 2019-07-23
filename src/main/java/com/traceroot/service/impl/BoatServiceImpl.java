package com.traceroot.service.impl;

import com.traceroot.dataobject.Boat;
import com.traceroot.repository.BoatRepository;
import com.traceroot.service.BoatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    BoatRepository repository;

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
     * 按是否穿越管道查询船只
     * @param segmentId
     * @return
     */
    @Override
    public List<Boat> selectByPassingPipelineSegment(String segmentId) {
        //todo 根据船只穿越管道线返回船只列表
        return null;
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
        return repository.save(boat);
    }

    /**
     * 按照船只id删除船只
     * @param boatId
     * @return
     */
    @Override
    public Boolean deleteById(String boatId) {
        repository.deleteById(boatId);
        return true;
    }


}
