package greensea.energy.device.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName: UpdateDeviceDto2
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-06 08:53
 * @Version: 1.0
 **/
@Data
public class UpdateDeviceDto2 {
    /**
     * 设备id
     */
    @TableId(value = "device_id",type = IdType.AUTO)
    @Schema(description = "设备id")
    @NotNull(message = "设备id不能为空")
    private Integer deviceId;
    /**
     * 系统启停信号
     */
    @TableField("sys_start_stop")
    private Integer sysStartStop;
    /**
     * 故障复位信号
     */
    @TableField("fault_reset")
    private Integer faultReset;
}
