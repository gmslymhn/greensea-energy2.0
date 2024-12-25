package greensea.energy.device.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: UpdateAddressDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-30 10:54
 * @Version: 1.0
 **/
@Schema(description = "修改设备地址")
@Data
public class UpdateAddressDto extends Address {

    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 设备IP
     */
    @Schema(description = "设备IP")
    @TableField("device_ip")
    private String deviceIp;
}
