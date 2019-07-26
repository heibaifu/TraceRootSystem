package com.traceroot.service.impl;

import com.traceroot.dataobject.RouteSegment;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.repository.RouteSegmentRepository;
import com.traceroot.service.ifs.RouteSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RouteSegmentServiceImpl implements RouteSegmentService {

    @Autowired
    RouteSegmentRepository repository;

    /**
     * 根据航线段id查找
     * @param segmentId
     * @return
     */
    @Override
    public RouteSegment selectBySegmentId(String segmentId) {
        return repository.findBySegmentId(segmentId);
    }

    /**
     * 根据航线id查找
     * @param routeId
     * @return
     */
    @Override
    public List<RouteSegment> selectByRouteId(String routeId) {
        return repository.findByRouteId(routeId);
    }

    /**
     * 根据航线段id查找并根据序列升序返回
     * @param routeId
     * @return
     */
    @Override
    public List<RouteSegment> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId) {
        return repository.findByRouteIdOrderBySegmentSerialNumberAsc(routeId);
    }

    /**
     * 插入数据
     * @param segment
     * @return
     */
    @Override
    public RouteSegment insert(RouteSegment segment) {
        Integer number = repository.countRouteSegmentByRouteId(segment.getRouteId())+1;
        segment.setSegmentSerialNumber(number);
        return repository.save(segment);
    }

    /**
     * 更新数据
     * @param segment
     * @return
     */
    @Override
    @Transactional
    public RouteSegment update(RouteSegment segment) {
        RouteSegment result = repository.findBySegmentId(segment.getSegmentId());
        if(result!=null)
            return repository.save(segment);
        else
            throw new RouteException(ResultEnum.ROUTE_SEGMENT_NOT_EXIST);
    }

    /**
     * 按照航线段id删除
     * @param segmentId
     */
    @Override
    public void deleteBySegmentId(String segmentId) {
        RouteSegment result = repository.findBySegmentId(segmentId);
        if(result!=null)
            repository.delete(result);
        else
            throw new RouteException(ResultEnum.ROUTE_SEGMENT_NOT_EXIST);
    }

    /**
     * 根据航线id删除
     * @param routeId
     */
    @Override
    public void deleteByRouteId(String routeId) {
        List<RouteSegment> result = repository.findByRouteId(routeId);
        if(result!=null)
            repository.deleteAll(result);
        else
            throw new RouteException(ResultEnum.SEA_ROUTE_NOT_EXIST);
    }
}
