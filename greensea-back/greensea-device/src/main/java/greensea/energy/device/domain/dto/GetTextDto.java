package greensea.energy.device.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @ClassName: GetTextDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 13:24
 * @Version: 1.0
 **/
@Schema(description = "获取文字类")
@Data
public class GetTextDto {
    @Schema(description = "文字密钥")
    @NotBlank(message = "文字密钥不能为空")
    @Size(min=1,max = 20,message = "文字密钥在1~20字符之间")
    private String textKey;

    @Schema(description = "文字类型")
    @NotBlank(message = "文字类型不能为空")
    @Size(min=1,max = 20,message = "文字类型在1~20字符之间")
    private String textType;
}
