package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Token
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 16:08
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Token")
public class DeviceToken {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 设备Token
     */
    @Schema(description = "设备Token")
    @TableField("device_token")
    private String deviceToken;
    /**
     * 设备IP
     */
    @Schema(description = "设备IP")
    @TableField("device_ip")
    private String deviceIp;
}
