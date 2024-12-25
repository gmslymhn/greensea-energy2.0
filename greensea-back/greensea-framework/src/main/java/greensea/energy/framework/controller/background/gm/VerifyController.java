package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.http.IpUtil;
import greensea.energy.common.utils.http.ServletUtils;
import greensea.energy.framework.domain.model.LoginUser;
import greensea.energy.framework.web.SecurityUtils;
import greensea.energy.framework.web.service.VerifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: VerifyService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-03 22:04
 * @Version: 1.0
 **/
@RestController
@Tag(name = "操作验证")
@RequestMapping("background/")
@Slf4j
public class VerifyController {
    @Autowired
    private VerifyService verifyService;

    @PostMapping("/mayControls")
    @Operation(summary = "是否可操作判断",description= "判断是否可以进行操作")
    public R mayControls(){
        String userAccount = SecurityUtils.getUserAccount();
        return verifyService.mayControls(userAccount);
    }

    @PostMapping("/getVerifyCode")
    @Operation(summary = "获取验证码",description= "获取验证码")
    public R addControlsVerify(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return verifyService.addControlsVerify(loginUser.getUserAccount(),loginUser.getUserEmail());
    }
    @PostMapping("/controlsVerify")
    @Operation(summary = "用验证码验证",description= "用验证码验证")
    public R controlsVerify(@RequestParam("code")String code){
        String userAccount = SecurityUtils.getUserAccount();
        return verifyService.controlsVerify(userAccount,code);
    }
}
