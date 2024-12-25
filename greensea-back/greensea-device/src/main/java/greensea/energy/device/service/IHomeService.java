package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.PowerCharDto;

/**
 * @ClassName: IHomeService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 12:42
 * @Version: 1.0
 **/
public interface IHomeService {
    R GmgetDeviceMsgs1(String areacode);

    R GmgetDeviceMsgs2(Integer gmId);

    R UsergetDeviceMsgs(Integer userId);


    R userGetPowerChar(Integer userId, PowerCharDto powerCharDto);

    R gmGetPowerChar(Integer gmId, PowerCharDto powerCharDto);

    R getPowerChar(PowerCharDto powerCharDto);
}
