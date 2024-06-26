package greensea.energy.framework.domain.dto.param;

import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: UserParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-26 22:13
 * @Version: 1.0
 **/
@Data
@Schema(description = "用户分页信息")
public class UserParam extends PageParam {
    private String userAccount;
    private Integer userType;
//    private String userEmail;
}
