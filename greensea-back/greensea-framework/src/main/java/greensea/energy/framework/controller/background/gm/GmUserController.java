package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.param.GmParam;
import greensea.energy.framework.domain.dto.param.SysLogParam;
import greensea.energy.framework.domain.dto.param.UserParam;
import greensea.energy.framework.service.IGmService;
import greensea.energy.framework.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GmUserController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-26 22:09
 * @Version: 1.0
 **/
@RestController
@Tag(name = "用户管理")
@RequestMapping("background/gm")
@Slf4j
public class GmUserController {
    @Autowired
    private IGmService iGmService;
    @Autowired
    private IUserService iUserService;

    @PostMapping("/gmlist")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "管理员列表",description= "获取管理员列表")
    public R gmList(@RequestBody @Validated GmParam gmParam) {
        return iGmService.gmList(gmParam);
    }

    @PostMapping("/gmmsg")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "管理员信息",description= "获取管理员详细信息")
    public R gmMsg(@RequestParam("gmId")Integer gmId) {
        return iGmService.getGmMsg(gmId);
    }

//    @PostMapping("/updategm")
//    @PreAuthorize("@ss.hasPermission('admin')")
//    @Operation(summary = "修改管理员",description= "修改管理员信息")
//    public R updateMsg(@RequestParam("gmId")Integer gmId) {
//        return iGmService.getGmMsg(gmId);
//    }
    @PostMapping("/userlist")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "用户列表",description= "获取用户列表")
    public R userList(@RequestBody @Validated UserParam userParam) {
        return iUserService.userList(userParam);
    }

    @PostMapping("/usermsg")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "用户信息",description= "获取用户详细信息")
    public R userMsg(@RequestParam("userId")Integer userId) {
        return iUserService.getUserMsg(userId);
    }
}
