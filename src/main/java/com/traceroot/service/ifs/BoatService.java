package com.traceroot.service.ifs;

import com.traceroot.dataobject.Boat;

import java.util.List;

public interface BoatService {

    Boat selectByBoatId(String boatId);

    List<Boat> selectByStatus(String status);

    List<Boat> selectAllBoat();

    List<Boat> selectByRoute(String routeId);

    List<Boat> selectByTypeIn(List<String> typeList);

    Boat save(Boat boat);

    Boolean deleteById(String boatId);

}
