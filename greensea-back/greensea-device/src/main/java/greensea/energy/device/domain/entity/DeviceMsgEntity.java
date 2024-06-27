package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: DeviceMsgEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:11
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备详细信息")
@TableName("gre_device_msg")
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
    /**
     * 逻辑删除(1删除 0未删除)
     */
    @JsonIgnore
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;
}
