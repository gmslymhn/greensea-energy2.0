package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.DevUpdateDeliveryDto;
import greensea.energy.device.domain.entity.DevUpdateEntity;
import greensea.energy.device.domain.param.DevUpdateParam;

/**
 * @ClassName: DevUpdateService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 20:18
 * @Version: 1.0
 **/
public interface IDevUpdateService {
    R getDevUpdateList(DevUpdateParam devUpdateParam);

    R addDevUpdate(DevUpdateEntity devUpdateEntity);

    R updateDevUpdate(DevUpdateEntity devUpdateEntity);

    R deleteDevUpdateById(Integer devUpdateId);


    R updateDelivery(DevUpdateDeliveryDto devUpdateDeliveryDto);

    R devUpdateDelivery(DevUpdateDeliveryDto devUpdateDeliveryDto);
}
