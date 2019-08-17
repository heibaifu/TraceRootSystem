package com.traceroot.controller;

import com.traceroot.converter.form2dto.PipeForm2PipeDTOConverter;
import com.traceroot.converter.form2dto.PipeSegmentForm2PipeSegmentDTOConverter;
import com.traceroot.converter.form2dto.PipelineSensorForm2SensorDTOConverter;
import com.traceroot.dataobject.Pipeline;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.form.PipeForm;
import com.traceroot.form.PipeSegmentForm;
import com.traceroot.form.PipelineSensorForm;
import com.traceroot.service.ifs.SensorTypeService;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import com.traceroot.service.impl.PipelineSensorServiceImpl;
import com.traceroot.service.impl.PipelineServiceImpl;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.vo.ResultVO;
import com.traceroot.vo.SensorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pipe")
@Slf4j
public class PipeManagerController {

    @Autowired
    PipelineServiceImpl pipelineService;

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @Autowired
    PipelineSensorServiceImpl sensorService;

    @Autowired
    SensorTypeService sensorTypeService;

    /**
     * 保存管道
     * @param pipeForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/savepipe")
    @ResponseBody
    public ResultVO<Map<String,String>> savePipe(@Valid PipeForm pipeForm,
                                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【保存管道】参数不正确，pipeForm={}",pipeForm);
            ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode()
                    ,bindingResult.getFieldError().getDefaultMessage());
        }

        /*Pipeline result = new Pipeline();
        try {
            PipeDTO pipeDTO = PipeForm2PipeDTOConverter.convert(pipeForm);
            result = pipelineService.save(pipeDTO);
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }*/

        PipeDTO pipeDTO = PipeForm2PipeDTOConverter.convert(pipeForm);
        Pipeline result = pipelineService.save(pipeDTO);
        if (result == null){
            log.error("【保存管道】保存失败，result={}",result);
            ResultVOUtil.error(ResultEnum.SAVE_FAIL.getCode(),ResultEnum.SAVE_FAIL.getMessage());
        }

        Map<String,String> map = new HashMap<>();
        map.put("pipeId",result.getPipeId());

        return ResultVOUtil.success(map);
    }


    /**
     * 保存管道段
     * @param segmentForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/savesegment")
    @ResponseBody
    public ResultVO<Map<String,String>> saveSegment(@Valid PipeSegmentForm segmentForm,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【保存管道段】参数不正确，pipeForm={}",segmentForm);
            ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode()
                    ,bindingResult.getFieldError().getDefaultMessage());
        }

        PipeSegmentDTO pipeSegmentDTO = PipeSegmentForm2PipeSegmentDTOConverter.convert(segmentForm);

        PipelineSegment result;
        if (segmentService.selectBySegmentId (segmentForm.getSegmentId()) !=null) {
            result = segmentService.update(pipeSegmentDTO);
        }else {
            result = segmentService.insert(pipeSegmentDTO);
        }
        if (result == null){
            log.error("【保存管道段】保存失败，result={}",result);
            ResultVOUtil.error(ResultEnum.SAVE_FAIL.getCode(),ResultEnum.SAVE_FAIL.getMessage());
        }

        Map<String,String> map = new HashMap<>();
        map.put("pipeId",result.getPipeId());

        return ResultVOUtil.success(map);
    }

    /**
     * 删除管道
     * todo 删除操作都没写
     * @param pipeId
     * @return
     */
    @GetMapping("/deletepipe")
    @ResponseBody
    public ResultVO<Map<String,String>> pipelineDelete(@RequestParam(value = "pipeId",required = false)String pipeId){

        Map<String,String> map = new HashMap<>();
//        map.put("pipeId",result.getSensorId());

        return ResultVOUtil.success(map);
    }

    /**
     * 删除管道段
     * @param segmentId
     * @return
     */
    @GetMapping("/deletesegment")
    @ResponseBody
    public ResultVO<Map<String,String>> segmentDelete(@RequestParam(value = "segmentId",required = false)String segmentId){

        Map<String,String> map = new HashMap<>();
//        map.put("segmentId",result.getSensorId());

        return ResultVOUtil.success(map);
    }

    /*下面是关于传感器的方法*/

    /**
     * 根据传感器坐标查找传感器
     * @param sensorLocation
     * @param map
     * @return
     */
    @GetMapping("/findsensor")
    public ModelAndView findSensorByLocation(@RequestParam(value = "sensorlocation",required = false)String sensorLocation,
                                             Map<String,Object> map){
        PipelineSensorDTO pipelineSensorDTO= sensorService.selectByLocation(sensorLocation);
        if (pipelineSensorDTO==null){
            log.error("【查找传感器】传感器不存在，sensorLocation={}",sensorLocation);
            throw new PipeException(ResultEnum.SENSOR_NOT_EXIST);
        }
        map.put("pipelineSensor",pipelineSensorDTO);
        return new ModelAndView("bmaptest1.html", map);
    }

    /**
     * 根据管道段id返回传感器
     * @param segmentId
     * @return
     */
    @GetMapping("/sensorwithsegid")
    @ResponseBody
    public ResultVO<List<SensorVO>> findSensorBySegmentId (@RequestParam(value = "segmentid",required = true)String segmentId){

        PipelineSegment segment = segmentService.selectBySegmentId(segmentId);
        if (segment == null){
            return ResultVOUtil.error(ResultEnum.PIPE_SEGMENT_NOT_EXIST.getCode(),ResultEnum.PIPE_SEGMENT_NOT_EXIST.getMessage());
        }
        List<PipelineSensorDTO> sensorDTOList = sensorService.selectBySegmentId(segmentId);
        List<SensorVO> sensorVOList = new ArrayList<>();
        sensorDTOList.forEach(element -> {
            SensorVO sensorVO = new SensorVO(element.getTypeId(),element.getPresentValue(),sensorTypeService.selectByTypeId(element.getTypeId()).getTypeName());
            sensorVOList.add(sensorVO);
        });
        return ResultVOUtil.success(sensorVOList);
    }

    /**
     * 管道传感器的新增及更新
     * @param pipelineSensorForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/savesensor")
    @ResponseBody
    public ResultVO<Map<String,String>> saveSensor (@Valid PipelineSensorForm pipelineSensorForm,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【保存传感器】参数不正确，pipelineSensorForm={}",pipelineSensorForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        //先判断传感器ID是否存在以分清是更新还是新增
        PipelineSensorDTO pipelineSensorDTO = PipelineSensorForm2SensorDTOConverter.convert(pipelineSensorForm);
        PipelineSensorDTO result = new PipelineSensorDTO();
        if (pipelineSensorForm.getSensorId() == null|| StringUtils.isEmpty(pipelineSensorForm.getSensorId())){

            pipelineSensorDTO= PipelineSensorForm2SensorDTOConverter.convert(pipelineSensorForm);
            pipelineSensorDTO.setSensorId(RandomUtil.genUniqueId());
            result = sensorService.save(pipelineSensorDTO); //新增

        }else{
            result = sensorService.update(pipelineSensorDTO);    //更新
        }

        if (result == null){
            log.error("【保存传感器】保存失败，result={}",result);
            ResultVOUtil.error(ResultEnum.SAVE_FAIL.getCode(),ResultEnum.SAVE_FAIL.getMessage());
        }

        Map<String,String> map = new HashMap<>();
        map.put("sensorId",result.getSensorId());

        return ResultVOUtil.success(map);
    }

    /**
     * 删除传感器
     * @param sensorId
     * @return
     */
    @GetMapping("/deletesensor")
    @ResponseBody
    public ResultVO<Map<String,String>> sensorDelete(@RequestParam(value = "sensorId",required = false)String sensorId){

        Map<String,String> map = new HashMap<>();
//        map.put("sensorId",result.getSensorId());

        return ResultVOUtil.success(map);
    }

}
