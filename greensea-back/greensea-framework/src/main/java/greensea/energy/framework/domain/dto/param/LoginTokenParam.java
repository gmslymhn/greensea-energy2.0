package greensea.energy.framework.domain.dto.param;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: LoginTokenParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-14 17:30
 * @Version: 1.0
 **/
@Data
@Schema(description = "登陆token分页信息")
public class LoginTokenParam extends PageParam {
}
