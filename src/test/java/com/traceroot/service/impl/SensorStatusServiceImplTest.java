package com.traceroot.service.impl;

import com.traceroot.dataobject.SensorStatus;
import com.traceroot.enums.SensorStatusEnum;
import com.traceroot.service.ifs.SensorStatusService;
import com.traceroot.utils.RandomUtil;
import com.traceroot.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SensorStatusServiceImplTest {

    @Autowired
    private SensorStatusService statusService;

    private String SENSORID = "1262750";

    private String STATUSID = "5055635";

    private Timestamp startTime;

    private Timestamp endTime;/* = new Timestamp(System.currentTimeMillis())*/;

    @Test
    public void selectBySensorId() {
        List<SensorStatus> statusList = statusService.selectBySensorId("8898273");
        Assert.assertNotNull(statusList);
    }

    @Test
    public void selectByStatusId() {
        SensorStatus status = statusService.selectByStatusId("0152242");
        Assert.assertEquals(status.getStatus(),"102");
        Assert.assertEquals(status.getStatusId(),STATUSID);
    }

    @Test
    public void selectByRecordTimeBetween() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-24 20:25:05");
            endTime = TimeUtil.string2Timestamp("2019-07-24 20:27:51");
        } catch (ParseException e) {
           log.error("【时间设定异常】",e.getMessage());
        }
        List<SensorStatus> sensorStatusList = statusService.selectByRecordTimeBetween(startTime, endTime);
        Assert.assertNotNull(sensorStatusList);
    }

    @Test
    public void selectBySensorIdAndRecordTimeBetween() {
        try {
            startTime = TimeUtil.string2Timestamp("2019-07-24 20:25:05");
            endTime = TimeUtil.string2Timestamp("2019-07-24 20:27:51");
        } catch (ParseException e) {
            log.error("【时间设定异常】",e.getMessage());
        }
        List<SensorStatus> sensorStatusList = statusService.selectBySensorIdAndRecordTimeBetween(SENSORID, startTime, endTime);
        Assert.assertNotNull(sensorStatusList);
    }

    @Test
    public void save() throws Exception{
        SensorStatus sensorStatus=new SensorStatus();
        sensorStatus.setSensorId("7337065");
        sensorStatus.setStatusId(RandomUtil.genUniqueId());
        //sensorStatus.setStatus(SensorStatusEnum.NORMAL.getCode());
        sensorStatus.setValue("1");
        SensorStatus result=statusService.save(sensorStatus);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteByStatusId() {
        STATUSID = "2757371";
        statusService.deleteByStatusId(STATUSID);
        SensorStatus status = statusService.selectByStatusId(STATUSID);
        Assert.assertNull(status);
    }

    @Test
    public void deleteBySensorId() {
        SENSORID = "1262750";
        statusService.deleteBySensorId(SENSORID);
        List<SensorStatus> statusList = statusService.selectBySensorId(SENSORID);
        Assert.assertEquals(statusList.size(),0);
    }

}