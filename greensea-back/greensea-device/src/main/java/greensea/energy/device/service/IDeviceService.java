package greensea.energy.device.service;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.dto.UpdateAddressDto;

/**
 * @ClassName: IDeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 19:25
 * @Version: 1.0
 **/
public interface IDeviceService {
    R getyChart1(ChartDto chartDto);

    R getyChart2(ChartDto chartDto);

    R updateDeviceAddress(UpdateAddressDto updateAddressDto);
}
