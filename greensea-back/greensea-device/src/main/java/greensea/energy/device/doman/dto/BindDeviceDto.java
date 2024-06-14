package greensea.energy.device.doman.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: BindDeviceDto
 * @Description: 绑定
 * @Author: gmslymhn
 * @CreateTime: 2024-06-14 22:20
 * @Version: 1.0
 **/
@Schema(description = "添加设备dto")
@Data
public class BindDeviceDto {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    private String deviceNumber;
}
