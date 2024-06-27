package greensea.energy.device.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: ChartVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 19:30
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备图表信息")
public class ChartVo {
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 数值1
     */
    private Float values1;
    /**
     * 数值2
     */
    private Float values2;
    /**
     * 数值3
     */
    private Float values3;

}
