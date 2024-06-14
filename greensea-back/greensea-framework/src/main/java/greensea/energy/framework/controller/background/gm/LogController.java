package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.annotation.SysLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.param.LoginLogParam;
import greensea.energy.framework.domain.dto.param.LoginTokenParam;
import greensea.energy.framework.domain.dto.param.SysLogParam;
import greensea.energy.framework.service.ILogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 * @ClassName: LogController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 21:47
 * @Version: 1.0
 **/
@RestController
@Tag(name = "超管日志查看")
@RequestMapping("background/gm")
@Slf4j
public class LogController {
    @Autowired
    private ILogService iLogService;
    @PostMapping("/loginlog")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "登陆日志",description= "获取登录日志")
    public R login(@RequestBody @Validated LoginLogParam loginLogParam) {
        return iLogService.getLoginLog(loginLogParam);
    }
    @PostMapping("/syslog")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "系统日志",description= "获取系统日志")
    public R login(@RequestBody @Validated SysLogParam sysLogParam) {
        return iLogService.getSysLog(sysLogParam);
    }
    @PreAuthorize("@ss.hasPermission('admin')")
    @PostMapping("/logintoken")
    @Operation(summary = "获取已登录的用户")
    public R getLoginToken(@RequestBody @Validated LoginTokenParam loginTokenParam) {
        R r = iLogService.getLoginToken(loginTokenParam);
        return r;
    }
}
