package greensea.energy.device.doman.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import greensea.energy.device.doman.entity.DeviceBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName: DeviceMsgEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:11
 * @Version: 1.0
 **/
@Schema(description = "设备详细信息")
public class DeviceMsgEntity extends DeviceBaseEntity {
    /**
     * 设备id
     */
    @TableId(value = "device_id")
    @Schema(description = "设备id")
    private Integer deviceId;
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
}
