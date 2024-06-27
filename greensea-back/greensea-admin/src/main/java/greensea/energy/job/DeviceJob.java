package greensea.energy.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.upload.domain.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: DeviceJob
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 17:55
 * @Version: 1.0
 **/
@Slf4j
@Component
public class DeviceJob {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private RedisUtils redisUtils;
    @XxlJob("testJob")
    public ReturnT<String> testJob(String date) {
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(null);
        for (DeviceEntity deviceEntity:deviceEntityList){
            Device device = new Device();
            device.setDeviceNumber(deviceEntity.getDeviceNumber());
            device.setDeviceId(deviceEntity.getDeviceId());
            redisUtils.setCacheObject("device_number:"+deviceEntity.getDeviceNumber(),device);
        }
        log.info("---------testJob定时任务执行成功--------");
        XxlJobHelper.log("---------testJob定时任务执行成功--------");
        return ReturnT.SUCCESS;
    }
}
