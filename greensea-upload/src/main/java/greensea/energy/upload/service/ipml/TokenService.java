package greensea.energy.upload.service.ipml;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import greensea.energy.upload.domain.model.Device;
import greensea.energy.upload.domain.model.DeviceToken;
import greensea.energy.upload.utils.*;
import greensea.energy.upload.utils.http.IpUtil;
import greensea.energy.upload.utils.http.ServletUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TokenService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 13:33
 * @Version: 1.0
 **/
@Component
@Slf4j
public class TokenService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     *  设备token
     */
    public static final String DEVICE_YOKEN = "device_token:";
    /**
     *  设备序列号
     */
    public static final String DEVICE_NUMBER = "device_number:";


    /**
     * 创建令牌
     *
     * @return 令牌
     */
    public String createToken(Device device) {
            delDeviceToken(device.getDeviceToken());
        String tokenkey = RandomUtils.createCode(10);
        log.info("唯一标识符："+tokenkey);
        DeviceToken deviceToken =new DeviceToken();
        deviceToken.setDeviceToken(tokenkey);
        deviceToken.setDeviceNumber(device.getDeviceNumber());
        deviceToken.setDeviceIp(IpUtil.getIpAddress(ServletUtils.getRequest()));
        return refreshToken(device,deviceToken);
    }

    /**
     * 刷新令牌有效期
     *
     */
    public String refreshToken(Device device, DeviceToken deviceToken) {
        device.setDeviceToken(deviceToken.getDeviceToken());
        device.setDeviceIp(deviceToken.getDeviceIp());
        redisUtils.setCacheObject(getTokenKey(deviceToken.getDeviceToken()), deviceToken, 24, TimeUnit.HOURS);
        //设置loginUser缓存
        redisUtils.setCacheObject(getDeviceKey(deviceToken.getDeviceNumber()), device);

        String tokenkey = deviceToken.getDeviceToken();
        log.info("tokenkey: "+tokenkey);
        return createToken(tokenkey);
    }

    /**
     * 删除用户身份信息Token
     */
    public void delDeviceToken(String tokenkey) {
        if (StringUtils.isNotEmpty(tokenkey)) {
            String tokenKey = getTokenKey(tokenkey);
            if (redisUtils.hasKey(tokenKey)){
                redisUtils.deleteObject(tokenKey);
            }
        }
    }
    /**
     * 删除用户身份信息LoginUser
     */
    public void delDevice(String deviceNumber) {
        if (ObjectUtils.isNotNull(deviceNumber)) {
            String deviceKey = getDeviceKey(deviceNumber);
            redisUtils.deleteObject(deviceKey);
        }
    }
    /**
     * 从数据声明生成令牌
     *
     * @return 令牌
     */
    public String createToken(String tokenkey) {
        return jwtUtil.generateToken(tokenkey);
    }

    /**
     * 获取用户Token身份信息
     *
     * @return 用户信息
     */
    public DeviceToken getDeviceToken(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                String tokenkey = EncryptUtils.Base64Decrypt(getKeyFromToken(token));
                log.info("tokenKey: "+getTokenKey(tokenkey));
                if (redisUtils.hasKey(getTokenKey(tokenkey))){
                    //获取用户id
                    DeviceToken deviceToken = (DeviceToken)redisUtils.getCacheObject(getTokenKey(tokenkey));
                    return deviceToken;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public Device getDevice(String deviceNumber) {
        if (StringUtils.isNotEmpty(deviceNumber)) {
            try {
                String deviceKey = getDeviceKey(deviceNumber);
                if (redisUtils.hasKey(deviceKey)){
//                    System.out.println(redisUtils.getCacheObject(getTokenKey(deviceKey)).getClass());
//                    JSONObject json =JSONObject.parseObject((String) redisUtils.getCacheObject(getTokenKey(deviceKey)));
                    //获取用户id
                    Device device = (Device)redisUtils.getCacheObject(deviceKey);
                    return device;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        return jwtUtil.getClaimsByToken(token);
    }

    /**
     * 从令牌中获取tokenkey
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getKeyFromToken(String token) {
        Claims claims = parseToken(token);
        log.info(String.valueOf(claims));
        log.info(claims.get("token", String.class));
        return claims.get("token", String.class);
    }
    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(jwtUtil.gerHeader());
        return token;
    }

    public String getTokenKey(String token) {
        return DEVICE_YOKEN + token;
    }

    public String getDeviceKey(String deviceNumber) {
        return DEVICE_NUMBER + deviceNumber;
    }

}
