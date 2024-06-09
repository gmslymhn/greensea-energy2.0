package greensea.energy.device.doman.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private String deviceNumber;
    /**
     * 设备名
     */
    @Schema(description = "设备名")
    private String deviceName;
    /**
     * 设备类型
     */
    @Schema(description = "设备类型")
    private String deviceType;
    /**
     * 设备ip
     */
    @Schema(description = "设备ip")
    private String deviceIp;
    /**
     * 设备地址
     */
    @Schema(description = "设备地址")
    private String deviceAddress;
    /**
     * 绑定时间
     */
    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;
    /**
     * 保修时间
     */
    @Schema(description = "保修时间")
    private LocalDateTime warrantyTime;
    /**
     * 设备mac
     */
    @Schema(description = "设备mac")
    private String deviceMac;
    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    private Integer deviceState;
}
