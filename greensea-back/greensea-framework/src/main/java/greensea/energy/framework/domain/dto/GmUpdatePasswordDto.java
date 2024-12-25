package greensea.energy.framework.domain.dto;

import greensea.energy.common.validator.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UpdatePasswordDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-07 17:13
 * @Version: 1.0
 **/
@Data
public class GmUpdatePasswordDto {
    @Schema(description = "管理员id")
    private Integer gmId;

    @Schema(description = "管理员旧密码")
    @Xss(message = "用户旧密码不能包含脚本字符")
    @NotBlank(message = "管理员旧密码不能为空")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String oldPassword;
    @Schema(description = "管理员新密码")
    @Xss(message = "用户新密码不能包含脚本字符")
    @NotBlank(message = "管理员新密码不能为空")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String newPassword;
}
