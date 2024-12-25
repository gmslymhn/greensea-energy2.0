package greensea.energy.device.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: GetImgDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 13:42
 * @Version: 1.0
 **/
@Schema(description = "获取图片类")
@Data
public class GetImgDto {
    @Schema(description = "图片密钥")
    @NotBlank(message = "图片密钥不能为空")
    @Size(min=1,max = 20,message = "文字密钥在1~20字符之间")
    private String imgKey;

    @Schema(description = "图片类型")
    @NotBlank(message = "图片类型不能为空")
    @Size(min=1,max = 20,message = "文字类型在1~20字符之间")
    private String imgType;
}
