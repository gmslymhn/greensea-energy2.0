package greensea.energy.device.controller.background;

import greensea.energy.common.domain.R;
import greensea.energy.device.doman.param.DeviceParam;
import greensea.energy.device.service.IDeviceService;
import greensea.energy.framework.web.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/testchart")
    @Operation(summary = "测试设备图表")
    public R getAllDevs() {
        return iDeviceService.testChart();
    }
}
