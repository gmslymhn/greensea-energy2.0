package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.param.DeviceParam;

/**
 * @ClassName: AbnormalLogService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 13:48
 * @Version: 1.0
 **/
public interface IAbnormalLogService {
    R getAbnormalLogList(DeviceParam deviceParam);
}
