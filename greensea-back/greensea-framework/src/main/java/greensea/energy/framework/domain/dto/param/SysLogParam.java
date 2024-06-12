package greensea.energy.framework.domain.dto.param;

import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: SysLogParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-12 21:43
 * @Version: 1.0
 **/
@Data
@Schema(description = "系统分页信息")
public class SysLogParam extends PageParam {
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
