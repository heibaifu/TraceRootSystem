package com.traceroot.repository;

import com.traceroot.dataobject.PipelineSegment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PipelineSegmentRepositoryTest {

    @Autowired
    private PipelineSegmentRepository repository;

    @Test
    public void findByPipeIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumber() throws Exception{
        List<PipelineSegment> segmentList = repository.findByPipeIdAndSegmentSerialNumberAfterOrderBySegmentSerialNumber("2044895", 2);
        log.info("{}",segmentList.size());
    }

}