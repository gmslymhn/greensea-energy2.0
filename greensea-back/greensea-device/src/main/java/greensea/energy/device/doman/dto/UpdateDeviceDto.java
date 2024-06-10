package greensea.energy.device.doman.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: UpdateDeviceDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 14:02
 * @Version: 1.0
 **/
@Schema(description = "删除设备dto")
@Data
public class UpdateDeviceDto {
    public interface Bind {
    }
    public interface Update {
    }
    /**
     * 设备id
     */
    @Schema(description = "设备id")
    @NotNull(message = "设备id不能为null",groups = {Bind.class,Update.class})
    private Integer deviceId;
    /**
     * 设备ip
     */
    @Schema(description = "设备ip")
    @NotNull(message = "设备ip不能为null",groups = Bind.class)
    @TableField("device_ip")
    private String deviceIp;
    /**
     * 设备地址
     */
    @Schema(description = "设备地址")
    @NotNull(message = "设备地址不能为null",groups = Bind.class)
    @TableField("device_address")
    private String deviceAddress;
    /**
     * 设备mac
     */
    @Schema(description = "设备mac")
    @TableField("device_mac")
    @NotNull(message = "设备mac不能为null",groups = Bind.class)
    private String deviceMac;
    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    @NotNull(message = "设备状态不能为null", groups = Update.class)
    @TableField("device_state")
    private Integer deviceState;
}
