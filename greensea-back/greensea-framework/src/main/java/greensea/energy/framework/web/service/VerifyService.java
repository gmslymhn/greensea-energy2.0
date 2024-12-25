package greensea.energy.framework.web.service;

import greensea.energy.common.constant.KeyConstants;
import greensea.energy.common.constant.UserConstants;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.*;
import greensea.energy.common.utils.http.IpUtil;
import greensea.energy.common.utils.http.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VerifyService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-03 21:22
 * @Version: 1.0
 **/
@Component
@Slf4j
public class VerifyService {
    @Autowired
    private RedisUtils redisUtils;

    private Integer lockTime=10;
    @Autowired
    private MailUtil mailUtil;


    public R mayControls(String userAccount){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        Integer controlsFrequency = (Integer) redisUtils.getCacheObject(getMayControlsKey(ip,userAccount));
        if (ObjectUtils.isNull(controlsFrequency)||controlsFrequency==0){
            return R.error(410,"未验证，请进行身份验证！");
        }
        return R.success();
    }

    private String getControlsKey(String ip,String userAccount){
        return KeyConstants.CONTROLS_VERIFCATION_CODE+ip+"_"+userAccount+"_";
    }
    private String getControlsFrequencyKey(String ip,String userAccount){
        return KeyConstants.CONTROLS_VERIFCATION_FREQUENCY+ip+"_"+userAccount;
    }
    private String getMayControlsKey(String ip,String userAccount){
        return KeyConstants.MAY_CONTROLS+ip+"_"+userAccount;
    }

    public R addControlsVerify(String userAccount,String  userEmail){
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        String controlsCode = RandomUtils.createCode1(UserConstants.VERIFCATION_CODE_LENGTH);
        if (redisUtils.getExpirationTime(getControlsKey(ip,userAccount))>290){
            return R.error("验证频率太高,请稍候再试！");
        }
        redisUtils.setCacheObject(getControlsKey(ip,userAccount), controlsCode,5, TimeUnit.MINUTES);
        Integer controlsFrequency = (Integer) redisUtils.getCacheObject(getControlsFrequencyKey(ip,userAccount));
        if (ObjectUtils.isNotNull(controlsFrequency)&&controlsFrequency>=5){
            return R.error("验证次数过多，请"+lockTime+"分钟后再试！");
        }
        if (ObjectUtils.isNull(controlsFrequency)){
            controlsFrequency = 0;
        }
        controlsFrequency++;
        redisUtils.setCacheObject(getControlsFrequencyKey(ip,userAccount),controlsFrequency,10,TimeUnit.MINUTES);
        mailUtil.sendSampleMail(userEmail, "格熙光伏电池数据分析平台验证码", controlsCode);
        System.out.println("******执行发送验证码邮件成功******");
        return R.success("发送成功！");
    }

    public R controlsVerify(String userAccount,String code) {
        HttpServletRequest request = ServletUtils.getRequest();
        String ip = IpUtil.getIpAddress(request);
        String controlsCode = String.valueOf(redisUtils.getCacheObject(getControlsKey(ip, userAccount)));
        if (StringUtils.isNotBlank(controlsCode) && controlsCode.equals(code)) {
            redisUtils.setCacheObject(getMayControlsKey(ip,userAccount), 1,5, TimeUnit.MINUTES);
            return R.success();
        }
        return R.error( "验证码错误！");
    }
}
