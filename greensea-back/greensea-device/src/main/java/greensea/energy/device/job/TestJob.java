package greensea.energy.device.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import greensea.energy.device.doman.entity.DeviceUploadEntity;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceUpload1Mapper;
import greensea.energy.device.mapper.DeviceUpload2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @ClassName: TestJob
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-22 15:11
 * @Version: 1.0
 **/
@Slf4j
@Component
public class TestJob {
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;
    @Autowired
    private DeviceUpload2Mapper deviceUpload2Mapper;
    @XxlJob("testJob")
    public ReturnT<String> testJob(String date) {
        log.info("---------testJob定时任务执行成功--------");
        XxlJobHelper.log("---------testJob定时任务执行成功--------");
        return ReturnT.SUCCESS;
    }
    @XxlJob("uploadJob")
    public ReturnT<String> uploadJob() throws Exception {
        DeviceTableNameHandler.setData("1");
        DeviceUploadEntity deviceUploadEntity = new DeviceUploadEntity();
        deviceUploadEntity.setSysFaultLvl(randomInteger(0,15));
        deviceUploadEntity.setSysFaultCode(randomInteger(0,10000));
        deviceUploadEntity.setFcStatus(randomInteger(0,15));
        deviceUploadEntity.setFcStackPower(randomFloat(-100f,200f));
        deviceUploadEntity.setFcStackVolt(randomFloat(-100f,1000f));
        deviceUploadEntity.setFcStackCur(randomFloat(0f,1000f));
        deviceUploadEntity.setFcStackCur(randomFloat(0f,1000f));
        deviceUploadEntity.setFcDCDCVoltOut(randomFloat(0f,1000f));
        deviceUploadEntity.setFcDCDCCurOut(randomFloat(0f,1000f));
        deviceUploadEntity.setFcSysPower(randomFloat(-100f,200f));
        deviceUploadEntity.setFckWh(randomFloat(0f,100f));
        deviceUploadEntity.setPemh2Status(randomInteger(0,15));
        deviceUploadEntity.setPemh2DCDCVoltOut(randomFloat(0f,1000f));
        deviceUploadEntity.setPemh2DCDCCurOut(randomFloat(0f,1000f));
        deviceUploadEntity.setPemh2Pro(randomFloat(0f,10000f));
        deviceUploadEntity.setLbmsVolt(randomFloat(0f,1000f));
        deviceUploadEntity.setLbmsCur(randomFloat(0f,1000f));
        deviceUploadEntity.setLbmsSOC(randomFloat(0f,100f));
        deviceUploadEntity.setSbmsVolt(randomFloat(0f,1000f));
        deviceUploadEntity.setSbmsCur(randomFloat(0f,1000f));
        deviceUploadEntity.setSbmsSOC(randomFloat(0f,100f));
        deviceUpload1Mapper.insert(deviceUploadEntity);
        // 用完即销毁
        DeviceTableNameHandler.removeData();
        log.info("---------testJob定时任务执行成功--------");
        return ReturnT.SUCCESS;
    }
    public float randomFloat(float min,float max) throws Exception {
//        float min = 1f;
//        float max = 10f;
        float floatBounded = min + new Random().nextFloat() * (max - min);
        return floatBounded;
    }
    public int randomInteger(int min,int max) throws Exception {
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        return intBounded;
    }
}
