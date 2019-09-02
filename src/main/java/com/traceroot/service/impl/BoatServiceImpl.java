package com.traceroot.service.impl;

import com.traceroot.converter.dao2dto.Boat2BoatDTOConverter;
import com.traceroot.dataobject.Boat;
import com.traceroot.dto.BoatDTO;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.repository.BoatRepository;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.ifs.BoatTraceService;
import com.traceroot.service.ifs.CrossService;
import com.traceroot.utils.GeographyUtil;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatRepository repository;

    @Autowired
    private BoatTraceService traceService;

    @Autowired
    private CrossService crossService;

    /**
     * 按照船只id查询
     * @param boatId
     * @return
     */
    @Override
    public BoatDTO selectByBoatId(String boatId) {

        Boat boat=repository.findByBoatId(boatId);
        if (boat == null){
            return null;
        }
        BoatDTO boatDTO= Boat2BoatDTOConverter.convert(boat);

        List<BoatTraceDTO> boatTraceDTOS=traceService.selectByBoatId(boatId);
        boatDTO.setBoatTraces(boatTraceDTOS);
        return boatDTO;
    }

    /**
     * 按照船只状态查询
     * @param status
     * @return
     */
    @Override
    public List<BoatDTO> selectByStatus(String status) {
        List<Boat> boatList=repository.findByStatus(status);
        List<BoatDTO> boatDTOList=Boat2BoatDTOConverter.convert(boatList);

        return boatDTOList;
    }

    /**
     * 查询所有船只
     * @return
     */
    @Override
    public List<BoatDTO> selectAllBoat() {
        List<Boat> boatList=repository.findAll();
        List<BoatDTO> boatDTOList=Boat2BoatDTOConverter.convert(boatList);

        return boatDTOList;
    }

    /**
     * 按航线查询船只
     * @param routeId
     * @return
     */
    @Override
    public List<BoatDTO> selectByRoute(String routeId) {
        List<Boat> boatList=repository.findByRouteId(routeId);
        List<BoatDTO> boatDTOList=Boat2BoatDTOConverter.convert(boatList);

        return boatDTOList;
    }


    /**
     * 按船只类型查询船只
     * @param typeList
     * @return
     */
    @Override
    public List<BoatDTO> selectByTypeIn(List<String> typeList) {
        List<Boat> boatList=repository.findByTypeIn(typeList);
        List<BoatDTO> boatDTOList=Boat2BoatDTOConverter.convert(boatList);

        return boatDTOList;
    }

    @Override
    public Double directionCalculate(String boatId , String presentLocation) {

        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(boatId);
        if (traceDTOList.size() == 0)
            return null;
        else {
            return GeographyUtil.inferDirection(traceDTOList.get(traceDTOList.size()-1).getRecordLocation(),presentLocation);
        }
    }

    @Override
    public String speedCalculate(String boatId, String presentLocation, Date updateTime) {
        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(boatId);
        if (traceDTOList.size() == 0)
            return null;
        else {
            String speed=GeographyUtil.getSpeed(traceDTOList.get(traceDTOList.size()-1).getRecordLocation(),
                                                presentLocation,
                                                traceDTOList.get(traceDTOList.size()-1).getRecordTime(),
                                                updateTime);
            return speed;
        }



    }

    /**
     * 保存船只信息
     * 同时插入一条该船只的轨迹
     * @param boatDTO
     * @return
     */
    @Override
    @Transactional
    public BoatDTO save(BoatDTO boatDTO) {

        Boat boat=new Boat();
        BeanUtils.copyProperties(boatDTO,boat);

        //todo 判断船只是否超速需根据船只所在的航道段的限速判断，所以需要写出船只在那个航线段的判断方法

        Double directionCalculate = directionCalculate(boat.getBoatId(), boat.getPresentLocation());
        if (directionCalculate != null){
            boat.setDirection(directionCalculate.toString());
        }

        //获取当前系统时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH); //设定格式
        Date timeDate = null;   //util类型
        try {
            timeDate = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());

        //计算速度
        String speed = speedCalculate(boat.getBoatId(),boat.getPresentLocation(),dateTime);
        if (speed != null) {
            boat.setSpeed(speed);
            if (boat.getRouteId() != null){
                Integer overspeedJudging = crossService.ditermineOverspeed(boat.getRouteId(),boat.getPresentLocation(), speed);
                boat.setOverspeedJudging(overspeedJudging);
            }
        }
        BoatDTO result = Boat2BoatDTOConverter.convert(repository.save(boat));

        //新建一条轨迹信息
        BoatTraceDTO boatTraceDTO = new BoatTraceDTO(RandomUtil.genUniqueId(),boatDTO.getBoatId(),boatDTO.getPresentLocation(),result.getDirection(), result.getSpeed(),boatDTO.getStatus());
        traceService.insert(boatTraceDTO);
        return result;
    }

    /*@Override
    public Boat updateByLocation(String boatId, String presentLocation) {
        Boat boat=repository.findByBoatId(boatId);
        if (boat==null){
            throw new BoatException(ResultEnum.BOAT_NOT_EXIST);
        }
        boat.setPresentLocation(presentLocation);
        repository.save(boat);

        //保存一条船只轨迹
        BoatTrace boatTrace=new BoatTrace();
        boatTrace.setTraceId(RandomUtil.genUniqueId());
        boatTrace.setBoatId(boatId);
        boatTrace.setRecordLocation(presentLocation);
        boatTrace.setStatus(boat.getStatus());
        traceService.insert(boatTrace);

        return boat;
    }*/

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
