package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.RouteSegment2RouteSegmentDTO;
import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dto.RouteSegmentDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.repository.RouteSegmentRepository;
import com.traceroot.service.ifs.RouteSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class RouteSegmentServiceImpl implements RouteSegmentService {

    @Autowired
    RouteSegmentRepository repository;

    /**
     * 根据航线段id查找
     * @param segmentId
     * @return
     */
    @Override
    public RouteSegmentDTO selectBySegmentId(String segmentId) {

        RouteSegment routeSegment=repository.findBySegmentId(segmentId);

        RouteSegmentDTO routeSegmentDTO= RouteSegment2RouteSegmentDTO.convert(routeSegment);

        return routeSegmentDTO;
    }

    /**
     * 根据航线id查找
     * @param routeId
     * @return
     */
    @Override
    public List<RouteSegmentDTO> selectByRouteId(String routeId) {

        List<RouteSegment> routeSegmentList=repository.findByRouteId(routeId);
        List<RouteSegmentDTO> routeSegmentDTOS=RouteSegment2RouteSegmentDTO.convert(routeSegmentList);

        return routeSegmentDTOS;
    }

    /**
     * 根据航线段id查找并根据序列升序返回
     * @param routeId
     * @return
     */
    @Override
    public List<RouteSegmentDTO> selectByRouteIdOrderBySegmentSerialNumberAsc(String routeId) {

        List<RouteSegment> routeSegmentList=repository.findByRouteIdOrderBySegmentSerialNumberAsc(routeId);
        List<RouteSegmentDTO> routeSegmentDTOS=RouteSegment2RouteSegmentDTO.convert(routeSegmentList);
        return routeSegmentDTOS;
    }

    /**
     * 查找在序列号之后的航线段，按升序排列
     * @param routeId
     * @param serialNumber
     * @return
     */
    @Override
    public List<RouteSegmentDTO> findByRouteIdAndSegmentSerialNumberAfter(String routeId, Integer serialNumber) {

        List<RouteSegment> routeSegmentList=repository.findByRouteIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumberAsc(routeId,serialNumber);
        List<RouteSegmentDTO> routeSegmentDTOS=RouteSegment2RouteSegmentDTO.convert(routeSegmentList);

        return routeSegmentDTOS;
    }

    @Override
    public List<RouteSegmentDTO> selectAll() {
        List<RouteSegmentDTO> routeSegmentDTOS=RouteSegment2RouteSegmentDTO.convert(repository.findAll());
        return routeSegmentDTOS;
    }

    /**
     * 插入数据
     * @param segmentDTO
     * @return
     */
    @Override
    @Transactional
    public RouteSegmentDTO insert(RouteSegmentDTO segmentDTO) {
        Integer number = repository.countRouteSegmentByRouteId(segmentDTO.getRouteId())+1;
        RouteSegment routeSegment=new RouteSegment();
        BeanUtils.copyProperties(segmentDTO,routeSegment);
        routeSegment.setSegmentSerialNumber(number);
        RouteSegmentDTO routeSegmentDTO=RouteSegment2RouteSegmentDTO.convert(repository.save(routeSegment));
        return routeSegmentDTO;
    }

    /**
     * 更新数据
     * @param segment
     * @return
     */
    /*@Override
    @Transactional
    public RouteSegment update(RouteSegment segment) {
        RouteSegment result = repository.findBySegmentId(segment.getSegmentId());
        if(result!=null)
            return repository.save(segment);
        else
            throw new RouteException(ResultEnum.ROUTE_SEGMENT_NOT_EXIST);
    }*/

    /**
     * 按照航线段id删除
     * @param segmentId
     */
    @Override
    public void deleteBySegmentId(String segmentId) {
        RouteSegment routeSegment = repository.findBySegmentId(segmentId);
        if(routeSegment!=null) {
            //修改其余航线段的序列号
            Integer number=routeSegment.getSegmentSerialNumber();
            String routeId=routeSegment.getRouteId();
            int size=repository.countRouteSegmentByRouteId(routeId);
            List<RouteSegment> routeSegments=repository.findByRouteIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumberAsc(routeId,number);

            if (number != size){
                for (int i=0;i<routeSegments.size();i++){
                    routeSegments.get(i).setSegmentSerialNumber(i+number);
                    repository.save(routeSegments.get(i));
                }
            }

            repository.delete(routeSegment);
        }
        else {
            throw new RouteException(ResultEnum.ROUTE_SEGMENT_NOT_EXIST);
        }
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
