package com.traceroot.service.ifs;

import com.traceroot.dataobject.SensorType;
import com.traceroot.dto.SensorTypeDTO;

/**
 * 传感器种类表，实现增删改查
 */
public interface SensorTypeService {

    SensorTypeDTO save(SensorTypeDTO sensorTypeDTO);

    SensorTypeDTO selectByTypeId(String typeId);

    SensorTypeDTO selectByTypeName(String typeName);

/*    SensorType updateByTypeId(String typeId, String updateName);*/

    void deleteByTypeId(String typeId);

}
