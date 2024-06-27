package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import greensea.energy.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: DeviceEntiity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 04:58
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备列表返回信息")
@TableName("gre_device")
public class DeviceEntity extends BaseEntity {
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
    /**
     * 设备ip
     */
    @Schema(description = "设备ip")
    @TableField("device_ip")
    private String deviceIp;
    /**
     * 设备地址
     */
    @Schema(description = "设备地址")
    @TableField("device_address")
    private String deviceAddress;
    /**
     * 绑定时间
     */
    @Schema(description = "绑定时间")
    @TableField("bind_time")
    private LocalDateTime bindTime;
    /**
     * 保修时间
     */
    @Schema(description = "保修时间")
    @TableField("warranty_time")
    private LocalDateTime warrantyTime;
    /**
     * 设备mac
     */
    @Schema(description = "设备mac")
    @TableField("device_mac")
    private String deviceMac;
    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    @TableField("device_state")
    private Integer deviceState;
}
