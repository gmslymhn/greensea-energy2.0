package greensea.energy.framework.domain.dto.param;

import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: LoginLogDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 21:31
 * @Version: 1.0
 **/
@Data
@Schema(description = "登陆分页信息")
public class LoginLogParam extends PageParam {
    /**
     * 登陆类型
     */
    @Schema(description = "登陆类型")
    private String loginType;
    /**
     * 账号
     */
    @Schema(description = "用户账号")
    private String userAccount;
    /**
     * 登录IP
     */
    @Schema(description = "登陆ip")
    private String loginIp;
}
