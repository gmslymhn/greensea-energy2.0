package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: DmDeviceTag
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 15:19
 * @Version: 1.0
 **/
@Data
@TableName("gre_gm_device_tag")
public class GmDeviceTagEntity {
    @Schema(description = "管理员id")
    @TableField("gm_id")
    private Integer gmId;
    @Schema(description = "设备id")
    @TableField("device_id")
    private Integer deviceId;
}
