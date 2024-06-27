package greensea.energy.device.domain.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: UserDeviceParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-26 23:26
 * @Version: 1.0
 **/
@Schema(description = "搜索设备Param")
@Data
public class UserDeviceParam extends DeviceParam{
    private Integer userId;
}
