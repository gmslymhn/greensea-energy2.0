package greensea.energy.upload.service;

import greensea.energy.upload.domain.R;

/**
 * @ClassName: IDeliveryService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 15:41
 * @Version: 1.0
 **/
public interface IDeliveryService {
    R delivery(String deviceNumber);
}
