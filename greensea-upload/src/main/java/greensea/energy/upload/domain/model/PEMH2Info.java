package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PEMH2Info
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:50
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "电解槽信息")
public class PEMH2Info {
    /**
     * 电解槽状态
     */
    @TableField("pemh2_status")
    @Schema(description = "电解槽状态")
    Integer pemh2Status;
    /**
     * 电解槽DCDC的输出电压/V
     */
    @TableField("pemh2_dcdc_volt_out")
    @Schema(description = "电解槽DCDC的输出电压/V")
    Float pemh2DCDCVoltOut;
    /**
     * 电解槽DCDC的输出电流/A
     */
    @TableField("pemh2_dcdc_cur_out")
    @Schema(description = "电解槽DCDC的输出电流/A")
    Float pemh2DCDCCurOut;
    /**
     * 电解槽年累计制氢量/Nm3
     */
    @TableField("pemh2_pro")
    @Schema(description = "电解槽年累计制氢量/Nm3")
    Float pemh2Pro;
}
