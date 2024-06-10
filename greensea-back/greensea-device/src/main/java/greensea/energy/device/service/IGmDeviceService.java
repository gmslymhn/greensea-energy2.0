package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.doman.dto.AddDeviceDto;
import greensea.energy.device.doman.dto.UpdateDeviceDto;
import greensea.energy.device.doman.param.DeviceParam;

/**
 * @ClassName: IDeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:34
 * @Version: 1.0
 **/
public interface IGmDeviceService {
    R getDeviceList(DeviceParam deviceParam);

    R getDeviceList1(Integer gmId, DeviceParam deviceParam);

    R addDevice(AddDeviceDto addDeviceDto);

    R updateDevice(UpdateDeviceDto updateDeviceDto);

    R getDeviceMsgById(Integer deviceId);

    R getDeviceMsgById1(Integer gmId, Integer deviceId);

    R deleteDevice(Integer deviceId, String deviceNumber);
}
