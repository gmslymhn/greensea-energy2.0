package greensea.energy.upload.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TokenDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 13:20
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Token信息")
public class TokenDto {

    /**
 * 设备序列号
 */
@Schema(description = "设备序列号")
@TableField("device_number")
private String deviceNumber;

}
