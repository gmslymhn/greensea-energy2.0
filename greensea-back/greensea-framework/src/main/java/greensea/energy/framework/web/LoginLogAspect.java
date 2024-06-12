package greensea.energy.framework.web;

import eu.bitwalker.useragentutils.UserAgent;
import greensea.energy.common.annotation.LoginLogAnnotation;
import greensea.energy.common.annotation.SysLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.http.AddressUtil;
import greensea.energy.common.utils.http.IpUtil;
import greensea.energy.common.utils.http.ServletUtils;
import greensea.energy.framework.domain.dto.GmLoginDto;
import greensea.energy.framework.domain.dto.UserLoginDto;
import greensea.energy.framework.domain.entity.LoginLogEntity;
import greensea.energy.framework.domain.model.LoginUserToken;
import greensea.energy.framework.mapper.LoginLogMapper;
import greensea.energy.framework.web.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;

/**
 * @ClassName: LoginLogAspect
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 20:17
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class LoginLogAspect {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private TokenService tokenService;
    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(greensea.energy.common.annotation.LoginLogAnnotation)")
    public void loginLogPoinCut() {
    }
        /**
         * 记录操作日志
         * @param joinPoint 方法的执行点
         * @param result  方法返回值
         * @throws Throwable
         */
        @AfterReturning(returning = "result", value = "loginLogPoinCut()")
        public void saveOperLog(JoinPoint joinPoint, R result) throws Throwable {
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = ServletUtils.getRequest();
            try {
                LoginLogEntity loginLogEntity = new LoginLogEntity();
                // 从切面织入点处通过反射机制获取织入点处的方法
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                //获取切入点所在的方法
                Method method = signature.getMethod();
                //获取操作
                LoginLogAnnotation annotation = method.getAnnotation(LoginLogAnnotation.class);
                Object[] args = joinPoint.getArgs();
                String account = null;
                if (annotation.loginType().equals("A")){
                    GmLoginDto gmLoginDto = (GmLoginDto) args[0];
                    loginLogEntity.setLoginType("A");
                    account = gmLoginDto.getGmAccount();
                }else if (annotation.loginType().equals("B")){
                    UserLoginDto userLoginDto = (UserLoginDto) args[0];
                    loginLogEntity.setLoginType("B");
                    account = userLoginDto.getUserAccount();
                }
                if (SecurityUtils.isLogin()) {
                    LoginUserToken loginUserToken = tokenService.getLoginUserToken(request);
                    loginLogEntity.setLoginIp(loginUserToken.getIpaddr());
                    loginLogEntity.setBrowser(loginUserToken.getBrowser());
                    loginLogEntity.setLoginLocation(loginUserToken.getLoginLocation());
                    loginLogEntity.setOsName(loginUserToken.getOs());
                    loginLogEntity.setUserAccount(account);
                    loginLogEntity.setResultCode(result.getCode());
                    loginLogEntity.setResultMessage(result.getMessage());
                }else {
                    UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                    //获取IP地址
                    String ip = IpUtil.getIpAddress(request);
                    //获取操作系统
                    String osName = userAgent.getOperatingSystem().getName();
                    //获取浏览器类型
                    String browser = userAgent.getBrowser().getName();
                    //获取登录地址
                    String location = AddressUtil.getAddressByIP(ip);
                    loginLogEntity.setLoginIp(ip);
                    loginLogEntity.setBrowser(browser);
                    loginLogEntity.setLoginLocation(location);
                    loginLogEntity.setOsName(osName);
                    loginLogEntity.setUserAccount(account);
                    loginLogEntity.setResultCode(result.getCode());
                    loginLogEntity.setResultMessage(result.getMessage());
                }
                loginLogMapper.insert(loginLogEntity);

            } catch (Exception e) {
                e.printStackTrace();
                log.error("日志记录异常!");
            }
        }
    }
