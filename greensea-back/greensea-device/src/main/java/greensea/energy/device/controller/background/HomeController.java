package greensea.energy.device.controller.background;

import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.dto.PowerCharDto;
import greensea.energy.device.service.IHomeService;
import greensea.energy.framework.domain.PageParam;
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
 * @ClassName: HomeController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 12:41
 * @Version: 1.0
 **/
@RestController
@Tag(name = "首页")
@RequestMapping("background/home")
@Slf4j
public class HomeController {
    @Autowired
    private IHomeService iHomeService;
    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/userGetDeviceMsgs")
    @Operation(summary = "用户获取设备信息合集")
    public R userGetDeviceMsgs() {
        return iHomeService.UsergetDeviceMsgs(SecurityUtils.getUserId());
    }


    @PostMapping("/gmGetDeviceMsgs")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = " 超管获取设备信息合集")
    public R gmGetDeviceMsgs(String areacode) {
        return iHomeService.GmgetDeviceMsgs1(areacode);
    }

    @PostMapping("/gmGetDeviceMsgs2")
    @PreAuthorize("@ss.hasLoginType('A')")
    @Operation(summary = " 普通管理员获取设备信息合集")
    public R gmGetDeviceMsgs2() {
        return iHomeService.GmgetDeviceMsgs2(SecurityUtils.getUserId());
    }
    @PostMapping("/getPowerChar")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "超管获取总功率图表")
    public R getPowerChar(@RequestBody @Validated PowerCharDto powerCharDto) {
        return iHomeService.getPowerChar(powerCharDto);
    }
    @PostMapping("/gmGetPowerChar")
    @PreAuthorize("@ss.hasLoginType('A')")
    @Operation(summary = "普通管理员获取总功率图表")
    public R gmGetPowerChar(@RequestBody @Validated PowerCharDto powerCharDto) {
        return iHomeService.gmGetPowerChar(SecurityUtils.getUserId(),powerCharDto);
    }
    @PostMapping("/userGetPowerChar")
    @PreAuthorize("@ss.hasLoginType('B')")
    @Operation(summary = "普通用户获取总功率图表")
    public R userGetPowerChar(@RequestBody @Validated PowerCharDto powerCharDto) {
        return iHomeService.userGetPowerChar(SecurityUtils.getUserId(),powerCharDto);
    }
}
