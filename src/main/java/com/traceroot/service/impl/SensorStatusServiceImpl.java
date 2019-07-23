package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorStatus;
import com.traceroot.repository.SensorStatusRepository;
import com.traceroot.service.SensorStatusService;
import com.traceroot.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SensorStatusServiceImpl implements SensorStatusService {

    @Autowired
    private SensorStatusRepository repository;

    @Override
    public SensorStatus save(SensorStatus status) {

        return repository.save(status);
    }

    //todo 功能完善，比如根据status号get其内容

}
