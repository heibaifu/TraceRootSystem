package com.traceroot.repository;

import com.traceroot.dataobject.SeaRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeaRouteRepository extends JpaRepository<SeaRoute,String> {

    //按航线id查找
    SeaRoute findByRouteId(String routeId);

    //按航线状态查找
    List<SeaRoute> findByStatus(String status);

}
