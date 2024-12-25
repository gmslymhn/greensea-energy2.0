package greensea.energy.framework.domain.dto;

import greensea.energy.common.constant.UserConstants;
import greensea.energy.common.validator.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @ClassName: UpdateUserDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-01 10:11
 * @Version: 1.0
 **/
@Data
public class UpdateUserDto {
    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    @Schema(description = "用户密码")
    @Xss(message = "用户密码不能包含脚本字符")
    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间")
    private String userPassword;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @Size(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "昵称必须在2~20字符之间")
    private String userNickname;
    /**
     * 电话
     */
    @Schema(description = "管理员电话")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Size(min=11, max=11,message="电话长度必须在为11位")
    private String userPhone;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式异常")
    @Schema(description = "管理员邮箱")
    private String userEmail;
    /**
     * 头像
     */
    @Schema(description = "头像id")
    private Integer avatarId;

    @Schema(description = "账号状态")
    private Boolean state;
}
