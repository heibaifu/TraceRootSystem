package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;
import com.traceroot.dto.BoatDTO;

import java.util.Date;
import java.util.List;

public interface BoatService {

    BoatDTO selectByBoatId(String boatId);

    List<BoatDTO> selectByStatus(String status);

    List<BoatDTO> selectAllBoat();

    List<BoatDTO> selectByRoute(String routeId);

    List<BoatDTO> selectByTypeIn(List<String> typeList);

    Double directionCalculate(String boatId , String presentLocation);

    String speedCalculate (String boatId, String presentLocation, Date updateTime);

    BoatDTO save(BoatDTO boatDTO);

    /*//船只当前位置变化，同时将此变化插入到船只轨迹表中
    Boat updateByLocation(String boatId, String presentLocation);*/

    Boolean deleteById(String boatId);

}
