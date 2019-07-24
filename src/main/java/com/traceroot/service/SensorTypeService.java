package com.traceroot.service;

import com.traceroot.dataobject.SensorType;

/**
 * 传感器种类表，实现增删改查
 */
public interface SensorTypeService {

    SensorType save(SensorType sensorType);

    SensorType selectByTypeId(String typeId);

    SensorType selectByTypeName(String typeName);

    SensorType updateByTypeId(String typeId, String updateName);

    void deleteByTypeId(String typeId);

}
