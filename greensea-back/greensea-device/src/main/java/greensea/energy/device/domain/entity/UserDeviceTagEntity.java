package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: UserDeviceTagEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 16:40
 * @Version: 1.0
 **/
@Data
@TableName("gre_user_device_tag")
public class UserDeviceTagEntity {
    @Schema(description = "用户id")
    @TableField("user_id")
    private Integer userId;
    @Schema(description = "设备id")
    @TableId(value = "device_id")
    private Integer deviceId;
}
