package greensea.energy.upload.service.impl;

import greensea.energy.upload.domain.R;
import greensea.energy.upload.domain.entity.DeviceEntity;
import greensea.energy.upload.domain.model.Device;
import greensea.energy.upload.domain.model.Timelnfo;
import greensea.energy.upload.domain.vo.DeliveryVo;
import greensea.energy.upload.mapper.DevUpdateMapper;
import greensea.energy.upload.mapper.DeviceMapper;
import greensea.energy.upload.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @ClassName: DeliveryService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 15:41
 * @Version: 1.0
 **/
@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DevUpdateMapper devUpdateMapper;
    @Override
    public R delivery(String deviceNumber){
        Device device = tokenService.getDevice(deviceNumber);
        DeviceEntity deviceEntity = deviceMapper.selectById(device.getDeviceId());
        DeliveryVo deliveryVo = new DeliveryVo();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;  // 月份从0开始，需要加1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Timelnfo timelnfo = new Timelnfo(year,month,day,hour);
        deliveryVo.setTimelnfo(timelnfo);
        deliveryVo.setFaultReset(deviceEntity.getFaultReset());
        deliveryVo.setSysStartStop(deviceEntity.getSysStartStop());
        // 获取当前时间
        LocalTime now = LocalTime.now();

        // 定义格式化器，注意这里使用了毫秒
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
        deliveryVo.setTimeStamp(now.format(formatter));
        return R.success(deliveryVo);
    }



}
