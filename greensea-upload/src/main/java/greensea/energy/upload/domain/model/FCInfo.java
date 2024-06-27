package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FCInfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:48
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "燃料电池信息")
public class FCInfo {
    /**
     * 燃料电池状态
     */
    @TableField("fc_status")
    @Schema(description = "燃料电池状态")
    Integer fcStatus;
    /**
     * 燃料电池电堆功率/Kw
     */
    @TableField("fc_sys_power")
    @Schema(description = "燃料电池电堆功率/Kw")
    Float fcStackPower;
    /**
     * 燃料电池电压/V
     */
    @TableField("fc_stack_volt")
    @Schema(description = "燃料电池电压/V")
    Float fcStackVolt;
    /**
     * 燃料电池电流/A
     */
    @TableField("fc_stack_cur")
    @Schema(description = "燃料电池电流/A")
    Float fcStackCur;
    /**
     * 燃料电池DCDC输出电压/V
     */
    @TableField("fc_dcdc_volt_out")
    @Schema(description = "燃料电池DCDC输出电压/V")
    Float fcDCDCVoltOut;
    /**
     * 燃料电池DCDC输出电流/A
     */
    @TableField("fc_dcdc_cur_out")
    @Schema(description = "燃料电池DCDC输出电流/A")
    Float fcDCDCCurOut;
    /**
     * 燃料电池系统功率/kW
     */
    @TableField("fc_sys_power")
    @Schema(description = "燃料电池系统功率/kW")
    Float fcSysPower;
    /**
     * 燃料电池年累计发电量/kWh
     */
    @TableField("fc_kwh")
    @Schema(description = "燃料电池年累计发电量/kWh")
    Float fckWh;
}
