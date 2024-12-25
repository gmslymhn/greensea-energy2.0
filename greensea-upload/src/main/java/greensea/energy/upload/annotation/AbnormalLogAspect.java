package greensea.energy.upload.annotation;

import greensea.energy.upload.domain.R;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.entity.AbnormalEntity;
import greensea.energy.upload.domain.model.DeviceToken;
import greensea.energy.upload.mapper.AbnormalMapper;
import greensea.energy.upload.service.impl.TokenService;
import greensea.energy.upload.utils.http.IpUtil;
import greensea.energy.upload.utils.http.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * @ClassName: AbnormalLogAspct
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-13 12:57
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class AbnormalLogAspect {
    @Autowired
    private AbnormalMapper abnormalMapper;
    @Autowired
    private TokenService tokenService;
    @Pointcut("@annotation(greensea.energy.upload.annotation.AbnormalLogAnnotation)")
    public void abnormalLogPoinCut() {
    }

    /**
     * 记录操作日志
     * @param joinPoint 方法的执行点
     * @param result  方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "abnormalLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, R result) throws Throwable {
        if (result.getCode()==200){
            return;
        }
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = ServletUtils.getRequest();
        try {
            AbnormalEntity abnormalEntity = new AbnormalEntity();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            AbnormalLogAnnotation annotation = method.getAnnotation(AbnormalLogAnnotation.class);
            Object[] args = joinPoint.getArgs();
            abnormalEntity.setIp(IpUtil.getIpAddress(request));
            abnormalEntity.setRequestContent(String.valueOf(args[0]));
            abnormalEntity.setResultMessage(result.getMessage());
            DeviceToken deviceToken = tokenService.getDeviceToken(request);
            if (ObjectUtils.isNotEmpty(deviceToken)){
                abnormalEntity.setDeviceNumber(deviceToken.getDeviceNumber());
            }
            abnormalMapper.insert(abnormalEntity);


        } catch (Exception e) {
            e.printStackTrace();
            log.error("日志记录异常!");
        }
    }
}
