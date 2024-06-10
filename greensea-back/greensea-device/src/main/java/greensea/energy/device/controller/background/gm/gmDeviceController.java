package greensea.energy.device.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.device.doman.dto.AddDeviceDto;
import greensea.energy.device.doman.dto.UpdateDeviceDto;
import greensea.energy.device.doman.param.DeviceParam;
import greensea.energy.device.service.IGmDeviceService;
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
 * @ClassName: gmDevoceController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 12:50
 * @Version: 1.0
 **/
@RestController
@Tag(name = "管理员设备管理")
@RequestMapping("background/gm")
@Slf4j
public class gmDeviceController {
    @Autowired
    private IGmDeviceService iGmDeviceService;

    @PreAuthorize("@ss.hasLoginType('A')")
    @PostMapping("/devlist")
    @Operation(summary = "获取管理的设备")
    public R getAllDevs(@RequestBody @Validated DeviceParam deviceParam) {
        if (SecurityUtils.getPermission().equals("admin")){
            return iGmDeviceService.getDeviceList(deviceParam);
        }else if (SecurityUtils.getPermission().equals("manager")){
            return iGmDeviceService.getDeviceList1(SecurityUtils.getUserId(),deviceParam);
        }
        return R.error("系统错误！");
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/adddev")
    @Operation(summary = "添加设备（超管）",description = "超管才有权限调取")
    public R addDev(@RequestBody @Validated AddDeviceDto addDeviceDto ) {
        R r = iGmDeviceService.addDevice(addDeviceDto);
        return r;
    }
    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/updatedev")
    @Operation(summary = "修改设备（超管）",description = "超管才有权限调取")
    public R updateDev(@RequestBody @Validated(UpdateDeviceDto.Update.class) UpdateDeviceDto updateDeviceDto) {
        R r = iGmDeviceService.updateDevice(updateDeviceDto);
        return r;
    }
    @PreAuthorize("@ss.hasLoginType('A')")
    @PostMapping("/getdevmsg")
    @Operation(summary = "获取设备详细信息",description = "超管以及普通管理员才有权限调取")
    public R getDeviceMsg(@RequestParam("deviceId")Integer deviceId) {
        if (SecurityUtils.getPermission().equals("admin")){
            return iGmDeviceService.getDeviceMsgById(deviceId);
        }else if (SecurityUtils.getPermission().equals("manager")){
            return iGmDeviceService.getDeviceMsgById1(SecurityUtils.getUserId(),deviceId);
        }
        return R.error("系统错误！");
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/deletedev")
    @Operation(summary = "删除设备（超管）",description = "超管才有权限调取")
    @Parameter(name="deviceId",description="设备id",required=true)
    @Parameter(name="deviceNumber",description="设备序列号",required=true)
    public R deleteDev(@RequestParam("deviceId")Integer deviceId,@RequestParam("deviceNumber") String deviceNumber) {
        R r = iGmDeviceService.deleteDevice(deviceId,deviceNumber);
        return r;
    }
}
