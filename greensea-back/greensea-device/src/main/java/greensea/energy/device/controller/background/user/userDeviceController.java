package greensea.energy.device.controller.background.user;

import greensea.energy.common.domain.R;
import greensea.energy.device.doman.param.DeviceParam;
import greensea.energy.device.service.IUserDeviceService;
import greensea.energy.framework.web.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: userDeviceControler
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 16:21
 * @Version: 1.0
 **/
@RestController
@Tag(name = "用户设备管理")
@RequestMapping("background/user")
@Slf4j
public class userDeviceController {
    @Autowired
    private IUserDeviceService iUserDeviceService;
    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/devlist")
    @Operation(summary = "获取绑定的设备")
    public R getAllDevs(@RequestBody @Validated DeviceParam deviceParam) {
        return iUserDeviceService.getDeviceList2(SecurityUtils.getUserId(),deviceParam);
    }
    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/getdevmsg")
    @Operation(summary = "获取设备详细信息")
    public R getDeviceMsg(@RequestParam("deviceId")Integer deviceId) {
        return iUserDeviceService.getDeviceMsgById2(SecurityUtils.getUserId(),deviceId);
    }

    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/binddev")
    @Operation(summary = "绑定设备")
    @Parameter(name="deviceNumber",description="设备序列号",required=true)
    public R getDeviceMsg(@RequestParam("deviceNumber") String deviceNumber) {
        return iUserDeviceService.bindDevice(SecurityUtils.getUserId(),deviceNumber);
    }
}
