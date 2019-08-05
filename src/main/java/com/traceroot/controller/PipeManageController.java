package com.traceroot.controller;

import com.traceroot.VO.ResultVO;
import com.traceroot.converter.dao2dto.PipelineSensor2SensorDTOConverter;
import com.traceroot.converter.form2dto.PipeForm2PipeDTOConverter;
import com.traceroot.converter.form2dto.PipeSegmentForm2PipeSegmentDTOConverter;
import com.traceroot.converter.form2dto.PipelineSensorForm2SensorDTOConverter;
import com.traceroot.dataobject.Pipeline;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dataobject.PipelineSensor;
import com.traceroot.dto.PipeDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.dto.PipelineSensorDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.form.PipeForm;
import com.traceroot.form.PipeSegmentForm;
import com.traceroot.form.PipelineSensorForm;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import com.traceroot.service.impl.PipelineSensorServiceImpl;
import com.traceroot.service.impl.PipelineServiceImpl;
import com.traceroot.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pipe")
@Slf4j
public class PipeManageController {

    @Autowired
    PipelineServiceImpl pipelineService;

    @Autowired
    PipelineSegmentServiceImpl segmentService;

    @Autowired
    PipelineSensorServiceImpl pipelineSensorService;

    @GetMapping()
    public ModelAndView pipeManager(
            /*@RequestParam(value = "badnodeid", defaultValue = "5") Integer badnodeid*/
            Map<String, Object> map) {

        //List<PipeSegmentDTO> pipeSegmentDTOList = segmentService.selectAll();

        List<PipeSegmentDTO> warningSegments=segmentService.selectByWarning();
        String[] badnodeid=new String[warningSegments.size()];
        for (int i=0;i<warningSegments.size();i++){
            badnodeid[i]=warningSegments.get(i).getSegmentId();
        }
        map.put("badnodeid", badnodeid);

        return new ModelAndView("bmaptest1.html", map);
    }

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
            throw new PipeException(ResultEnum.PARAM_ERROR.getCode()
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
            throw new PipeException(ResultEnum.SAVE_FAIL);
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
            throw new PipeException(ResultEnum.PARAM_ERROR.getCode()
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
            throw new PipeException(ResultEnum.SAVE_FAIL);
        }

        Map<String,String> map = new HashMap<>();
        map.put("pipeId",result.getPipeId());

        return ResultVOUtil.success(map);
    }


    //管道及管道段的删除操作

    /**
     * 管道传感器的新增
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
            throw new PipeException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        PipelineSensorDTO pipelineSensorDTO= PipelineSensorForm2SensorDTOConverter.convert(pipelineSensorForm);
        PipelineSensorDTO result = pipelineSensorService.save(pipelineSensorDTO);
        if (result == null){
            log.error("【保存传感器】保存失败，result={}",result);
            throw new PipeException(ResultEnum.SAVE_FAIL);
        }

        Map<String,String> map = new HashMap<>();
        map.put("sensorId",result.getSensorId());

        return ResultVOUtil.success(map);
    }


}
