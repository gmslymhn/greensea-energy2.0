package greensea.energy.device.service.impl;

import greensea.energy.common.domain.R;
import greensea.energy.device.doman.entity.DeviceUploadEntity;
import greensea.energy.device.doman.vo.ChartVo;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceUploadMapper;
import greensea.energy.device.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 19:26
 * @Version: 1.0
 **/
@Service
public class DeviceService implements IDeviceService {
    @Autowired
    private DeviceUploadMapper deviceUploadMapper;

    @Override
    public R testChart(){
        DeviceTableNameHandler.setData("1");
        List<DeviceUploadEntity> list = deviceUploadMapper.selectList(null);
        // 用完即销毁
        DeviceTableNameHandler.removeData();
        List<ChartVo> chartVos = new ArrayList<>();
        for (DeviceUploadEntity deviceUploadEntity:list){
            ChartVo chartVo = new ChartVo();
            chartVo.setTimestamp(deviceUploadEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            chartVo.setValues1(deviceUploadEntity.getSbmsVolt());
            chartVo.setValues2(deviceUploadEntity.getPemh2DCDCVoltOut());
            chartVo.setValues3(deviceUploadEntity.getLbmsVolt());
            chartVos.add(chartVo);
        }
        return R.success(chartVos);
    }
}
