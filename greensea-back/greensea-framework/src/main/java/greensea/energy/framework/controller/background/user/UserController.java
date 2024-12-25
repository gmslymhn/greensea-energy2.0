package greensea.energy.framework.controller.background.user;

import eu.bitwalker.useragentutils.UserAgent;
import greensea.energy.common.annotation.LoginLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.http.AddressUtil;
import greensea.energy.common.utils.http.IpUtil;
import greensea.energy.framework.domain.dto.*;
import greensea.energy.framework.service.IDirectoryService;
import greensea.energy.framework.service.IUserService;
import greensea.energy.framework.web.SecurityUtils;
import greensea.energy.framework.web.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-20 10:58
 * @Version: 1.0
 **/
@RestController
@Tag(name = "用户")
@RequestMapping("background/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private IDirectoryService iDirectoryService;

    @PostMapping("/login")
    @Operation(summary = "用户登陆")
    @LoginLogAnnotation(loginType = "B")
    public R login(@RequestBody @Validated UserLoginDto userLoginDto) {
        R verifyr = loginService.mayLogin(userLoginDto.getUserAccount());
        if (verifyr.getCode()!=200){
            return verifyr;
        }
        R r = iUserService.loginUser(userLoginDto);
        return r;
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    @PreAuthorize("@ss.hasLoginType('B')")
    public R logout() {
        R r = iUserService.logoutUser();
        return r;
    }
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public R register(@RequestBody @Validated AddUserDto addUserDto) {
        R verifyr = loginService.mayRegister(addUserDto.getUserAccount(), addUserDto.getUserEmail(),addUserDto.getVerificationCode());
        if (verifyr.getCode()!=200){
            return verifyr;
        }
        R r = iUserService.addUser(addUserDto);
        return r;
    }

    @PostMapping("/register/verify")
    @Operation(summary = "用户注册验证")
    public R verifyRegister(@RequestBody @Validated VerifyRegisterDto verifyRegister) {
        R verifyr = loginService.mayRegisterVerify();
        if (verifyr.getCode()!=200){
            return verifyr;
        }

        R r = iUserService.verifyRegister(verifyRegister.getUserAccount(),verifyRegister.getUserEmail());
        return r;
    }

    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/getselfdirectory")
    @Operation(summary = "获取自己的后台目录",description = "获取用户自己的后台目录")
    public R getSelfDirectory() {
        if (SecurityUtils.getPermission().equals("enterprise")){
            return iDirectoryService.getDirectory(3);
        }else if(SecurityUtils.getPermission().equals("personal")){
            return iDirectoryService.getDirectory(4);
        }
        return R.error("系统异常！");
    }

    @PreAuthorize("@ss.hasLoginType('B')")
    @PostMapping("/getselfmag")
    @Operation(summary = "获取自己的登陆信息",description = "登陆成功后第一时间通过token调取，可以获得用户自己的信息")
    public R getSelfMsg() {
        return iUserService.getUserSelfMsg();
    }



    @PostMapping("/updateSelfMsg")
    @PreAuthorize("@ss.hasLoginType('B')")
    @Operation(summary = "用户修改自己信息",description= "用户修改自己信息")
    public R updateUserMsg(@RequestBody @Validated UpdateUserDto updateUserDto) {
        updateUserDto.setUserId(SecurityUtils.getUserId());
        updateUserDto.setUserPassword(null);
        updateUserDto.setState(null);
        return iUserService.updateUserMsg(updateUserDto);
    }

    @PostMapping("/updateSelfPassword")
    @PreAuthorize("@ss.hasLoginType('B')")
    @Operation(summary = "用户修改自己密码",description= "用户修改自己密码")
    public R updateGmMsg(@RequestBody @Validated UserUpdatePasswordDto userUpdatePasswordDto) {
        userUpdatePasswordDto.setUserId(SecurityUtils.getUserId());
        return iUserService.updateUserPassword(userUpdatePasswordDto);
    }

}
