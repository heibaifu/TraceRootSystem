package com.traceroot.repository;

import com.traceroot.dataobject.BoatPredict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoatPredictRepository extends JpaRepository<BoatPredict,String> {

    List<BoatPredict> findByBoatIdOrderByCreateTimeDesc(String boatId);

}
