package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;

/**
 * @ClassName: IDeviceUploadMsgService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 16:03
 * @Version: 1.0
 **/
public interface IDeviceUploadMsgService {

    R getUploadMsgList(ChartDto chartDto);
}
