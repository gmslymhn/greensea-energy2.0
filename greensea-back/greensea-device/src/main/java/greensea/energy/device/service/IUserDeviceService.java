package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.param.DeviceParam;

/**
 * @ClassName: IUserDeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 16:28
 * @Version: 1.0
 **/
public interface IUserDeviceService {
    R getDeviceList2(Integer userId, DeviceParam deviceParam);

    R getDeviceMsgById2(Integer userId, Integer deviceId);

    R bindDevice(Integer userId, String deviceNumber);

    R untieDevice(Integer userId, String deviceNumber);
}
