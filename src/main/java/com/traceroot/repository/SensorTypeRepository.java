package com.traceroot.repository;

import com.traceroot.dataobject.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorTypeRepository extends JpaRepository<SensorType,String> {

    SensorType findByTypeId(String typeId);

    SensorType findByTypeName(String typeName);

}
