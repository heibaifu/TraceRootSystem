package com.traceroot.controller;

import com.traceroot.VO.ResultVO;
import com.traceroot.converter.PipeForm2PipeDTOConverter;
import com.traceroot.converter.PipeSegmentForm2PipeSegmentDTOConverter;
import com.traceroot.dataobject.Pipeline;
import com.traceroot.dataobject.PipelineSegment;
import com.traceroot.dto.PipeDTO;
import com.traceroot.dto.PipeSegmentDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.PipeException;
import com.traceroot.form.PipeForm;
import com.traceroot.form.PipeSegmentForm;
import com.traceroot.service.impl.PipelineSegmentServiceImpl;
import com.traceroot.service.impl.PipelineServiceImpl;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.utils.String2TimestampUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
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
        PipeSegmentDTO result = segmentService.save(pipeSegmentDTO);
        if (result == null){
            log.error("【保存管道段】保存失败，result={}",result);
            throw new PipeException(ResultEnum.SAVE_FAIL);
        }

        Map<String,String> map = new HashMap<>();
        map.put("pipeId",result.getPipeId());

        return ResultVOUtil.success(map);
    }

}
