package com.traceroot.repository;

import com.traceroot.dataobject.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoatRepository extends JpaRepository<Boat,String> {

    Boat findByBoatId(String boatId);

    List<Boat> findByStatus(String status);

    List<Boat> findByRouteId(String routeId);

    List<Boat> findByTypeIn(List<String> typeList);

}
