package com.traceroot.service.ifs;

import com.traceroot.dto.BoatPredictDTO;

import java.util.List;

public interface BoatPredictService {

    BoatPredictDTO save(BoatPredictDTO boatPredictDTO);

    List<BoatPredictDTO> selectByBoatId(String boatId);

}
