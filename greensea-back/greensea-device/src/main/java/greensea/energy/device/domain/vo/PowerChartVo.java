package greensea.energy.device.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: PowerChartVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 21:23
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备功率信息")
public class PowerChartVo {
    /**
     * 功率
     */
    @Schema(description = "功率")
    @TableField(exist = false)
    private Float power;
    /**
     * 时间戳
     */
    private Long timestamp;
}
