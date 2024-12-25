package greensea.energy.upload.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: DeviceEntitiy
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 14:18
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备列表返回信息")
@TableName("gre_device")
public class DeviceEntity {
    /**
     * 设备id
     */
    @TableId(value = "device_id",type = IdType.AUTO)
    @Schema(description = "设备id")
    private Integer deviceId;
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
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
