package com.traceroot.repository;

import com.traceroot.dataobject.BoatType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 船只种类dto
 */
public interface BoatTypeRepository extends JpaRepository<BoatType, String> {

    BoatType findByTypeId(String typeId);

    BoatType findByTypeName(String typeName);
}
