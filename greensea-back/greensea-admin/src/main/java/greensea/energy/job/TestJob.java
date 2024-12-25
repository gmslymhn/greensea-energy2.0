package greensea.energy.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.entity.DeviceUploadEntity;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceUpload1Mapper;
import greensea.energy.device.mapper.DeviceUpload2Mapper;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.model.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private RedisUtils redisUtils;
//    @XxlJob("testJob")
//    public ReturnT<String> testJob(String date) {
//        log.info("---------testJob定时任务执行成功--------");
//        XxlJobHelper.log("---------testJob定时任务执行成功--------");
//        return ReturnT.SUCCESS;
//    }
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
        UploadDto uploadDto = new UploadDto();
        /**
         * 故障信息
         */
        FaultInfo faultInfo = new FaultInfo(deviceUploadEntity.getSysFaultLvl(),
        deviceUploadEntity.getSysFaultCode());

        /**
         * 燃料电池信息
         */
        FCInfo fcInfo = new FCInfo(deviceUploadEntity.getFcStatus(),
        deviceUploadEntity.getFcStackPower(),
        deviceUploadEntity.getFcStackVolt(),
        deviceUploadEntity.getFcStackCur(),
        deviceUploadEntity.getFcDCDCVoltOut(),
        deviceUploadEntity.getFcDCDCCurOut(),
        deviceUploadEntity.getFcSysPower(),
        deviceUploadEntity.getFckWh());
        /**
         * 电解槽信息
         */
        PEMH2Info pemh2Info = new PEMH2Info(deviceUploadEntity.getPemh2Status(),
        deviceUploadEntity.getPemh2DCDCVoltOut(),
        deviceUploadEntity.getPemh2DCDCCurOut(),
        deviceUploadEntity.getPemh2Pro());
        /**
         * 大锂电池信息
         */
        LBMSInfo lbmsInfo = new LBMSInfo(deviceUploadEntity.getLbmsVolt(),
        deviceUploadEntity.getLbmsCur(),
        deviceUploadEntity.getLbmsSOC());
        /**
         * 小锂电池信息
         */
        SBMSInfo sbmsInfo = new SBMSInfo(deviceUploadEntity.getSbmsVolt(),
                deviceUploadEntity.getSbmsCur(),
                deviceUploadEntity.getSbmsSOC());
        uploadDto.setDeviceNumber("FZFC-2241-2211-02");
        uploadDto.setFaultInfo(faultInfo);
        uploadDto.setFcInfo(fcInfo);
        uploadDto.setPemh2Info(pemh2Info);
        uploadDto.setSbmsInfo(sbmsInfo);
        uploadDto.setLbmsInfo(lbmsInfo);
        redisUtils.setCacheObject("device_message:"+uploadDto.getDeviceNumber(),uploadDto);
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
