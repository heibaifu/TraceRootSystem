package com.traceroot.service;

import com.traceroot.dataobject.BoatType;

public interface BoatTypeService {

    BoatType insert(BoatType boatType);

    BoatType updateByTypeId(String typeId, String typeName);

    BoatType selectByTypeId(String typeId);

    BoatType selectByTypeName(String typeName);

    void deleteByTypeId (String typeId);
}
