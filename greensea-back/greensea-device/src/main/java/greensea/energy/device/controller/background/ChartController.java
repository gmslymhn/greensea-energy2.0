package greensea.energy.device.controller.background;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.dto.UpdateAddressDto;
import greensea.energy.device.service.IDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ChartController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 19:48
 * @Version: 1.0
 **/
@RestController
@Tag(name = "设备图表管理")
@RequestMapping("background/chart")
@Slf4j
public class ChartController {
    @Autowired
    private IDeviceService iDeviceService;
//    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/getChart1")
    @Operation(summary = "获取设备图表1")
    public R getDevChart1(@RequestBody @Validated ChartDto chartDto) {
        return iDeviceService.getyChart1(chartDto);
    }
    @PostMapping("/getChart2")
    @Operation(summary = "获取设备图表2")
    public R getDevChart2(@RequestBody @Validated ChartDto chartDto) {
        return iDeviceService.getyChart2(chartDto);
    }
    @PostMapping("/updateDeviceAddress")
    @Operation(summary = "修改设备地址信息")
    public R updateDeviceAddress(@RequestBody @Validated UpdateAddressDto updateAddressDto) {
        return iDeviceService.updateDeviceAddress(updateAddressDto);
    }
}
