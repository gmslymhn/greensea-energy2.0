package greensea.energy.common.constant;

/**
 * @ClassName: KeyConstants
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 22:19
 * @Version: 1.0
 **/
public class KeyConstants {
    /**
     * 登录用户 redis token key
     */
    public static final String LOGIN_TOKEN_KEY = "login_token:";
    /**
     * 登录用户 redis userId key
     */
    public static final String LOGIN_USER_KEY = "login_user:";
    /**
     * 验证频率 verify frequency key
     */
    public static final String VERIFY_FREQUENCY_KEY = "verify_frequency:";
    /**
     * 登陆失败频率 login fail key
     */
    public static final String LOGIN_FAIL_FREQUENCY_KEY = "login_fail_frequency:";
    /**
     * 登陆IP login ip key
     */
    public static final String LOGIN_IP_KEY = "login_ip:";
    /**
     * 验证频率 reset password key
     */
    public static final String RESET_PASSWORD_KEY = "reset_password:";

    /**
     * 注册验证码
     */
    public static final String REGISTER_VERIFCATION_CODE = "register_verifcation_code:";

    /**
     * 注册验证
     */
    public static final String REGISTER_VERIFCATION_FREQUENCY = "register_verifcation_frequency:";

    /**
     *  设备token
     */
    public static final String DEVICE_YOKEN = "device_token:";
    /**
     *  设备序列号
     */
    public static final String DEVICE_NUMBER = "device_number:";

    /**
     *  资源id
     */
    public static final String RESOURCE_ID = "resource_id:";
    /**
     *  操作
     */
    public static final String CONTROLS_VERIFCATION_CODE = "controls_verifcation_code:";

    /**
     * 操作验证次数
     */
    public static final String CONTROLS_VERIFCATION_FREQUENCY = "controls_verifcation_frequency:";

    /**
     * 可以操作
     */
    public static final String MAY_CONTROLS = "may_controls:";
}
