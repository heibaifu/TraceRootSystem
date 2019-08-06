package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;
import com.traceroot.dto.BoatDTO;

import java.util.List;

public interface BoatService {

    BoatDTO selectByBoatId(String boatId);

    List<BoatDTO> selectByStatus(String status);

    List<BoatDTO> selectAllBoat();

    List<BoatDTO> selectByRoute(String routeId);

    List<BoatDTO> selectByTypeIn(List<String> typeList);

    //同时插入一条该船只的轨迹
    BoatDTO save(BoatDTO boatDTO);

    /*//船只当前位置变化，同时将此变化插入到船只轨迹表中  todo 船只更新方法有待考虑，使用了DTO应该不需要此方法了
    Boat updateByLocation(String boatId, String presentLocation);*/

    Boolean deleteById(String boatId);

}
