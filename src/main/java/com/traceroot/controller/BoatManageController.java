package com.traceroot.controller;

import com.traceroot.converter.form2dto.BoatForm2BoatDTOConverter;
import com.traceroot.dto.BoatDTO;
import com.traceroot.dto.BoatTraceDTO;
import com.traceroot.vo.ThreateningBoatVO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.BoatException;
import com.traceroot.form.BoatForm;
import com.traceroot.service.ifs.BoatService;
import com.traceroot.service.ifs.CrossService;
import com.traceroot.service.impl.BoatTraceServiceImpl;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.utils.TimeUtil;
import com.traceroot.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/boat")
@Slf4j
public class BoatManageController {

    @Autowired
    BoatTraceServiceImpl traceService;

    @Autowired
    CrossService crossService;

    @Autowired
    BoatService boatService;

    /**
     * 按照船只id查找trace
     * @param boatId
     * @return
     */
    @ResponseBody
    @GetMapping("/findbyboatid")
    public ResultVO<List<BoatTraceDTO>> findBoatTraceByBoatId(@RequestParam(value = "boatid",required = true)String boatId){
        List<BoatTraceDTO> traceDTOList = traceService.selectByBoatId(boatId);
        if (traceDTOList.size()==0){
            log.error("【查找船只轨迹】船只轨迹不存在，boatid={}",boatId);
            throw new BoatException(ResultEnum.BOAT_TRACE_NOT_EXIST);
        }
        return ResultVOUtil.success(traceDTOList);
    }

    /**
     * 按照时间区间查找trace
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @GetMapping("/findbytimesection")
    public ResultVO<List<BoatTraceDTO>> findBoatTraceByTimeSection(@RequestParam(value = "starttime",required = true)String startTime,
                                                                   @RequestParam(value = "endtime",required = true)String endTime){
        List<BoatTraceDTO> traceDTOList = null;
        try {
            traceDTOList = traceService.selectByRecordTimeBetween(TimeUtil.string2Timestamp(startTime), TimeUtil.string2Timestamp(endTime));
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
            return ResultVOUtil.error(ResultEnum.TIME_FORMAT_ERROR.getCode(),ResultEnum.TIME_FORMAT_ERROR.getMessage());
        }
        if (traceDTOList.size()==0){
            log.error("【查找船只轨迹】该时间区间内船只轨迹不存在，starttime={},endtime={}",startTime,endTime);
            return ResultVOUtil.error(ResultEnum.BOAT_TRACE_NOT_EXIST.getCode(),ResultEnum.BOAT_TRACE_NOT_EXIST.getMessage());
        }
        return ResultVOUtil.success(traceDTOList);
    }

    /**
     * 查找在指定时间指定经纬度范围内，穿越指定管道段的船只及其穿越次数
     * @param segmentId
     * @param startTime
     * @param endTime
     * @param accuracyDegree
     * @return
     */
    @ResponseBody
    @GetMapping("/findwarningBoat")
    public ResultVO<List<BoatTraceDTO>> findWarningBoatNearSegmentDuringTime(@RequestParam(value = "segmentid",required = true)String segmentId,
                                                                             @RequestParam(value = "starttime",required = false)String startTime,
                                                                             @RequestParam(value = "endtime",required = false)String endTime,
                                                                             @RequestParam(value = "accuracydegree",required = false,defaultValue = "1")Integer accuracyDegree){
        if (endTime == null || endTime.isEmpty()){
            endTime = TimeUtil.presentTime();
//            log.warn("into 1");
        }
        if (endTime != null && startTime == null){
            try {
                startTime = TimeUtil.getPastDateBeforeThatDay(TimeUtil.string2Timestamp(endTime),3);
//                log.warn("into 3");
            } catch (ParseException e) {
                log.error("【时间设定异常或格式错误】,endTime={}",endTime);
                return ResultVOUtil.error(ResultEnum.TIME_FORMAT_ERROR.getCode(),ResultEnum.TIME_FORMAT_ERROR.getMessage());
            }
        }
        if (startTime == null || startTime.isEmpty()){
            startTime = TimeUtil.getPastDateBeforePresent(3);
//            log.warn("into 2");
        }
        NavigableMap<Integer, List<String>> map = null;
        try {
            map = crossService.selectByPassingPipelineSegment(segmentId, TimeUtil.string2Timestamp(startTime), TimeUtil.string2Timestamp(endTime), accuracyDegree);
        } catch (ParseException e) {
            log.error("【时间设定异常或格式错误】,startTime={},endTime={}",startTime,endTime);
            return ResultVOUtil.error(ResultEnum.TIME_FORMAT_ERROR.getCode(),ResultEnum.TIME_FORMAT_ERROR.getMessage());
        }
        if (map.size()==0){
            log.error("【查找船只轨迹】该时间、精度区间内船只不存在，starttime={},endtime={},accuracydegree={}",startTime,endTime,accuracyDegree);
            return ResultVOUtil.error(ResultEnum.NO_SURROUND_BOAT_FOUND.getCode(),ResultEnum.NO_SURROUND_BOAT_FOUND.getMessage());
        }

        //转化成前端所需要的格式
        List<ThreateningBoatVO> resultList = new ArrayList<>();

        map.forEach((key, value)->
            value.forEach(e -> {
                ThreateningBoatVO threateningBoatDTO = new ThreateningBoatVO();
                threateningBoatDTO.setBoatId(e);
                threateningBoatDTO.setTraverseTime(key);
                threateningBoatDTO.setPresentLocation(boatService.selectByBoatId(e).getPresentLocation());
                resultList.add(threateningBoatDTO);}
                )
        );

        return ResultVOUtil.success(resultList);
    }

    /**
     * 船只接口
     * @param boatForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/saveboat")
    @ResponseBody
    public ResultVO<String> saveBoat (@Valid BoatForm boatForm,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【保存船只】参数不正确，boatForm={}",boatForm);
            ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        BoatDTO boatDTO = BoatForm2BoatDTOConverter.convert(boatForm);
        boatService.save(boatDTO);
        return ResultVOUtil.success(boatDTO.getBoatId());
    }

}
