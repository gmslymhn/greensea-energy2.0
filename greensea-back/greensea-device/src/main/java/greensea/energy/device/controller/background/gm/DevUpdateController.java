package greensea.energy.device.controller.background.gm;

import greensea.energy.common.annotation.SysLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.device.domain.entity.DevUpdateEntity;
import greensea.energy.device.domain.param.DevUpdateParam;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.service.IDevUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DevUpdateController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 20:17
 * @Version: 1.0
 **/
@RestController
@Tag(name = "设备更新管理")
@RequestMapping("background/gm")
@Slf4j
public class DevUpdateController {

    @Autowired
    private IDevUpdateService iDevUpdateService;

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/devUpdateList")
    @Operation(summary = "获取设备更新列表")
    public R getDevUpdateList(@RequestBody @Validated DevUpdateParam devUpdateParam){
        return iDevUpdateService.getDevUpdateList(devUpdateParam);
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/addDevUpdate")
    @Operation(summary = "添加设备更新信息")
    @SysLogAnnotation(operModul = "设备管理>>设备更新管理", operType = "新增", operDesc = "添加设备更新信息")
    public R addDevUpdate(@RequestBody @Validated DevUpdateEntity devUpdateEntity){
        return iDevUpdateService.addDevUpdate(devUpdateEntity);
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/updateDevUpdate")
    @Operation(summary = "修改设备更新信息")
    @SysLogAnnotation(operModul = "设备管理>>设备更新管理", operType = "修改", operDesc = "修改设备更新信息")
    public R updateDevUpdate(@RequestBody @Validated DevUpdateEntity devUpdateEntity){
        if (ObjectUtils.isNull(devUpdateEntity.getUpdataId())){
            return R.error("数据错误！");
        }
        return iDevUpdateService.updateDevUpdate(devUpdateEntity);
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @Parameter(name="devUpdateId",description="更新数据id")
    @PostMapping("/deleteDevUpdate")
    @SysLogAnnotation(operModul = "设备管理>>设备更新管理", operType = "删除", operDesc = "删除设备更新信息")
    @Operation(summary = "删除设备更新信息")
    public R deleteDevUpdate(int devUpdateId){
        return iDevUpdateService.deleteDevUpdateById(devUpdateId);
    }



}
