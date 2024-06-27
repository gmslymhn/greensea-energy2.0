package greensea.energy.device.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: AddDeviceDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 13:08
 * @Version: 1.0
 **/
@Schema(description = "添加设备dto")
@Data
public class AddDeviceDto {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 设备名
     */
    @Schema(description = "设备名")
    @TableField("device_name")
    private String deviceName;
    /**
     * 设备类型
     */
    @TableField("device_type")
    @Schema(description = "设备类型")
    private String deviceType;
}
