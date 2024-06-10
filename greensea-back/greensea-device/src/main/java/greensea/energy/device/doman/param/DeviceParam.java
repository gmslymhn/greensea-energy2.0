package greensea.energy.device.doman.param;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: DeviceParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 14:18
 * @Version: 1.0
 **/
@Schema(description = "搜索设备Param")
@Data
public class DeviceParam extends PageParam {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 设备类型
     */
    @TableField("device_type")
    @Schema(description = "设备类型")
    private String deviceType;
    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    @TableField("device_state")
    private Integer deviceState;
}
