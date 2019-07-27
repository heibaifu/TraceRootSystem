package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;

import java.util.List;

public interface BoatService {

    Boat selectByBoatId(String boatId);

    List<Boat> selectByStatus(String status);

    List<Boat> selectAllBoat();

    List<Boat> selectByRoute(String routeId);

    List<Boat> selectByTypeIn(List<String> typeList);

    //同时插入一条该船只的轨迹
    Boat save(Boat boat);

    //船只当前位置变化，同时将此变化插入到船只轨迹表中  todo 此方法有待考虑
    Boat updateByLocation(String boatId, String presentLocation);

    Boolean deleteById(String boatId);

}
