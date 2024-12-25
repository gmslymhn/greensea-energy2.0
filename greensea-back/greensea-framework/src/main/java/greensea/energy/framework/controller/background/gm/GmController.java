package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.annotation.LoginLogAnnotation;
import greensea.energy.common.annotation.SysLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.dto.*;
import greensea.energy.framework.domain.dto.param.LoginTokenParam;
import greensea.energy.framework.service.IDirectoryService;
import greensea.energy.framework.service.IGmService;
import greensea.energy.framework.web.SecurityUtils;
import greensea.energy.framework.web.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GmController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 19:06
 * @Version: 1.0
 **/
@RestController
@Tag(name = "管理员")
@RequestMapping("background/gm")
@Slf4j
public class GmController {
    @Autowired
    private IGmService iGmService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private IDirectoryService iDirectoryService;
    @PostMapping("/login")
    @LoginLogAnnotation(loginType = "A")
    @Operation(summary = "管理员登陆",description= "用于管理员登陆")
    public R login(@RequestBody @Validated GmLoginDto gmLoginDto) {
        R verifyr = loginService.mayLogin(gmLoginDto.getGmAccount());
        if (verifyr.getCode()!=200){
            return verifyr;
        }
        R r = iGmService.loginGm(gmLoginDto);
        return r;
    }
    @PostMapping("/logout")
    @Operation(summary = "管理员登出")
    @PreAuthorize("@ss.hasLoginType('A')")
    public R logout() {
        R r = iGmService.logoutGm();
        return r;
    }

    @PostMapping("/logout/user")
    @Operation(summary = "登出用户")
    @PreAuthorize("@ss.hasPermission('admin')")
    @SysLogAnnotation(operModul = "用户管理>>登陆管理", operType = "强退", operDesc = "强退用户")
    public R logoutByToken(@RequestParam("token")String token) {
        R r = iGmService.logoutBytoken(token);
        return r;
    }

    @PreAuthorize("@ss.hasLoginType('A')")
    @PostMapping("/getselfdirectory")
    @Operation(summary = "获取自己的后台目录",description = "获取管理员自己的后台目录")
    public R getSelfDirectory() {
            if (SecurityUtils.getPermission().equals("admin")){
                return iDirectoryService.getDirectory(1);
            }else if(SecurityUtils.getPermission().equals("manager")){
                return iDirectoryService.getDirectory(2);
            }
        return R.error("系统异常！");
    }

    @PreAuthorize("@ss.hasPermission('admin')")
    @SysLogAnnotation(operModul = "用户管理>>管理员用户管理", operType = "新增", operDesc = "新增管理员")
    @PostMapping("/addgm")
    @Operation(summary = "添加管理员")
    public R addGm(@RequestBody @Validated AddGmDto addGmDto) {
        if (addGmDto.getGmType()==1){
            return R.error("非法入侵！");
        }
        R r = iGmService.addGm(addGmDto);
        return r;
    }

    @PreAuthorize("@ss.hasLoginType('A')")
    @PostMapping("/getselfmag")
    @Operation(summary = "获取自己的登陆信息",description = "登陆成功后第一时间通过token调取,可以获得管理员自己的信息")
    public R getSelfMsg() {
        return iGmService.getGmSelfMsg();
    }

    @PostMapping("/updateSelfMsg")
    @PreAuthorize("@ss.hasLoginType('A')")
    @Operation(summary = "管理员修改自己信息",description= "管理员修改自己信息")
    public R updateGmMsg(@RequestBody @Validated UpdateGmDto updateGmDto) {
        updateGmDto.setGmId(SecurityUtils.getUserId());
        updateGmDto.setGmPassword(null);
        updateGmDto.setState(null);
        return iGmService.updateGmMsg(updateGmDto);
    }

    @PostMapping("/updateSelfPassword")
    @PreAuthorize("@ss.hasLoginType('A')")
    @Operation(summary = "管理员修改自己密码",description= "管理员修改自己密码")
    public R updateGmMsg(@RequestBody @Validated GmUpdatePasswordDto gmUpdatePasswordDto) {
        gmUpdatePasswordDto.setGmId(SecurityUtils.getUserId());
        return iGmService.updateGmPassword(gmUpdatePasswordDto);
    }
}
