package com.traceroot.controller;

import com.traceroot.converter.form2dto.RouteSegmentForm2RouteSegmentDTO;
import com.traceroot.converter.form2dto.SeaRouteForm2SeaRouteDTO;
import com.traceroot.dataobject.RouteSegment;
import com.traceroot.dataobject.SeaRoute;
import com.traceroot.dto.RouteSegmentDTO;
import com.traceroot.dto.SeaRouteDTO;
import com.traceroot.enums.ResultEnum;
import com.traceroot.exception.RouteException;
import com.traceroot.form.RouteSegmentForm;
import com.traceroot.form.SeaRouteForm;
import com.traceroot.service.ifs.RouteSegmentService;
import com.traceroot.service.ifs.SeaRouteService;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.ResultVOUtil;
import com.traceroot.vo.ResultVO;
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
@RequestMapping("/searoute")
@Slf4j
public class SeaRouteController {

    @Autowired
    private SeaRouteService seaRouteService;

    @Autowired
    private RouteSegmentService routeSegmentService;

    /**
     *按航线状态号查找航线
     * @param routestatus
     * @return
     */
    @ResponseBody
    @GetMapping("/findroutebystatus")
    public ResultVO<List<SeaRouteDTO>> findRouteByStatus(@RequestParam(value = "routestatus",required = true)String routestatus){
        List<SeaRouteDTO> seaRouteDTOS=seaRouteService.selectByStatus(routestatus);

        if (seaRouteDTOS.size()==0){
            log.error("【查找航线】航线不存在，routestatus={}",routestatus);
            return ResultVOUtil.error(ResultEnum.SEA_ROUTE_NOT_EXIST.getCode(),ResultEnum.SEA_ROUTE_NOT_EXIST.getMessage());
        }

        return ResultVOUtil.success(seaRouteDTOS);
    }

    /**
     * 按航线ID查找航线，其中可以查出来对应航线的航线段
     * @param routeid
     * @return
     */
    @ResponseBody
    @GetMapping("/findroutebyid")
    public ResultVO<SeaRoute> findRouteById(@RequestParam(value = "routeid",required = true) String routeid){

        SeaRouteDTO seaRouteDTO=seaRouteService.selectByRouteId(routeid);

        if (seaRouteDTO==null){
            log.error("【查找航线】航线不存在, routeId={}",routeid);
            return ResultVOUtil.error(ResultEnum.SEA_ROUTE_NOT_EXIST.getCode(),ResultEnum.SEA_ROUTE_NOT_EXIST.getMessage());
        }
        return ResultVOUtil.success(seaRouteDTO);
    }

    /**
     * 航线的新增
     * @param seaRouteForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/saveroute")
    @ResponseBody
    public ResultVO<Map<String,String>> saveRoute(@Valid SeaRouteForm seaRouteForm,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【保存航线】参数不正确, seaRouteForm={}",seaRouteForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        SeaRouteDTO seaRouteDTO= SeaRouteForm2SeaRouteDTO.convert(seaRouteForm);
        if (seaRouteForm.getRouteId()!=null){
            seaRouteDTO=seaRouteService.selectByRouteId(seaRouteForm.getRouteId());
        }else {
            seaRouteDTO.setRouteId(RandomUtil.genUniqueId()); //设置航线号
        }
        SeaRouteDTO result=new SeaRouteDTO();
        result=seaRouteService.insert(seaRouteDTO);

        if (result == null){
            log.error("【保存航线】保存失败，result={}",result);
            return ResultVOUtil.error(ResultEnum.SAVE_FAIL.getCode(),ResultEnum.SAVE_FAIL.getMessage());
        }

        Map<String,String> map = new HashMap<>();
        map.put("routeId",result.getRouteId());

        return ResultVOUtil.success(map);
    }

    @PostMapping("/saveroutesegment")
    @ResponseBody
    public ResultVO<Map<String,String>> saveRouteSegment(@Valid RouteSegmentForm routeSegmentForm,
                                     BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【保存航线段】参数不正确, routeSegmentForm={}",routeSegmentForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        RouteSegmentDTO routeSegmentDTO= RouteSegmentForm2RouteSegmentDTO.convert(routeSegmentForm);
        if (routeSegmentForm.getSegmentId()!=null){
            routeSegmentDTO=routeSegmentService.selectBySegmentId(routeSegmentForm.getSegmentId());
        }else {
            routeSegmentDTO.setSegmentId(RandomUtil.genUniqueId()); //设置航线段号
        }
        RouteSegmentDTO result=routeSegmentService.insert(routeSegmentDTO);


        if (result == null){
            log.error("【保存航线段】保存失败，result={}",result);
            return ResultVOUtil.error(ResultEnum.SAVE_FAIL.getCode(),ResultEnum.SAVE_FAIL.getMessage());
        }

        Map<String,String> map = new HashMap<>();
        map.put("segmentId",result.getSegmentId());

        return ResultVOUtil.success(map);
    }


}


