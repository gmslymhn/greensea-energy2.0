package greensea.energy.device.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.service.IAbnormalLogService;
import greensea.energy.device.service.IDeviceUploadMsgService;
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
 * @ClassName: AbnormalLogController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 13:54
 * @Version: 1.0
 **/
@RestController
@Tag(name = "异常日志管理")
@RequestMapping("background/gm")
@Slf4j
public class AbnormalLogController {
    @Autowired
    private IAbnormalLogService iAbnormalLogService;
    @Autowired
    private IDeviceUploadMsgService iDeviceUploadMsgService;


    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/abnormalLogList")
    @Operation(summary = "获取异常日志列表")
    public R getAbnormalLogList(@RequestBody @Validated DeviceParam deviceParam){
        return iAbnormalLogService.getAbnormalLogList(deviceParam);
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/getUploadMsgList")
    @Operation(summary = "获取设备原始报文列表")
    public R getUploadMsgList(@RequestBody @Validated ChartDto chartDto){
        return iDeviceUploadMsgService.getUploadMsgList(chartDto);
    }

}
