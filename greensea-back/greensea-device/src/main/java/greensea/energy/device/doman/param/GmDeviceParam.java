package greensea.energy.device.doman.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: GmDeviceParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-26 22:57
 * @Version: 1.0
 **/
@Schema(description = "搜索设备Param")
@Data
public class GmDeviceParam extends DeviceParam{
    private Integer gmId;
}
