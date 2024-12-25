package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: InvInfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:52
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "逆变器信息")
public class InvInfo {
    /**
     * 逆变器当日发电量/kWh
     */
    @TableField("inv_day_output_plant")
    @Schema(description = "逆变器当日发电量/kWh")
    Float invDayOutputPlant;
    /**
     * 逆变器当月发电量/kWh
     */
    @TableField("inv_month_output_plant")
    @Schema(description = "逆变器当月发电量/kWh")
    Float invMonthOutputPlant;
    /**
     * 逆变器当年发电量/MWh
     */
    @TableField("inv_year_output_plant")
    @Schema(description = "逆变器当年发电量/MWh")
    Float invYearOutputPlant;
    /**
     * 逆变器累计总发电量/MWh
     */
    @TableField("inv_total_output_plant")
    @Schema(description = "逆变器累计总发电量/MWh")
    Float invTotalOutputPlant;
    /**
     * 当日收益/€
     */
    @TableField("day_revenue")
    @Schema(description = "当日收益/€")
    Float dayRevenue;
    /**
     * 当月收益/€
     */
    @TableField("month_revenue")
    @Schema(description = "当月收益/€")
    Float monthRevenue;
    /**
     * 当年收益/€
     */
    @TableField("year_revenue")
    @Schema(description = "当年收益/€")
    Float yearRevenue;
    /**
     * 累计总收益/€
     */
    @TableField("total_revenue")
    @Schema(description = "累计总收益/€")
    Float totalRevenue;
    /**
     * 电网当日购电量
     */
    @TableField("day_pg_buy_plant")
    @Schema(description = "电网当日购电量")
    Float dayPGBuyPlant;
    /**
     * 电网当日卖电量/kWh
     */
    @TableField("day_pg_sell_plant")
    @Schema(description = "电网当日卖电量/kWh")
    Float dayPGSellPlant;
    /**
     * 电网累计购电量/MWh
     */
    @TableField("total_pg_buy_plant")
    @Schema(description = "电网累计购电量/MWh")
    Float totalPGBuyPlant;
    /**
     * 电网累计卖电量/MWh
     */
    @TableField("total_pg_sell_plant")
    @Schema(description = "电网累计卖电量/MWh")
    Float totalPGSellPlant;
    /**
     * 用户当日用电量/kWh
     */
    @TableField("user_day_use_plant")
    @Schema(description = "用户当日用电量/kWh")
    Float userDayUsePlant;
    /**
     * 用户累计用电量/MWh
     */
    @TableField("user_total_use_plant")
    @Schema(description = "用户累计用电量/MWh")
    Float userTotalUsePlant;
    /**
     * 当日PV发电量/kWh
     */
    @TableField("day_pv_output_plant")
    @Schema(description = "当日PV发电量/kWh")
    Float dayPVOutputPlant;
    /**
     * PV累计发电量/MWh
     */
    @TableField("total_pv_output_plant")
    @Schema(description = "PV累计发电量/MWh")
    Float totalPVOutputPlant;
    /**
     * 电网侧相电压/V
     */
    @TableField("pg_pha_volt")
    @Schema(description = "电网侧相电压/V")
    Float pgPhaVolt;
    /**
     * 电网侧相电流/A
     */
    @TableField("pg_pha_cur")
    @Schema(description = "电网侧相电流/A")
    Float pgPhaCur;
    /**
     * 电网侧输出功率/kW
     */
    @TableField("pg_output_power")
    @Schema(description = "电网侧输出功率/kW")
    Float pgOutputPower;
    /**
     * 逆变器侧相电压/V
     */
    @TableField("inv_pha_volt")
    @Schema(description = "逆变器侧相电压/V")
    Float invPhaVolt;
    /**
     * 逆变器侧相电流/A
     */
    @TableField("inv_pha_cur")
    @Schema(description = "逆变器侧相电流/A")
    Float invPhaCur;
    /**
     * 逆变器侧输出功率/kW
     */
    @TableField("inv_output_power")
    @Schema(description = "逆变器侧输出功率/kW")
    Float invOutputPower;
    /**
     * 光伏输入功率/kW
     */
    @TableField("pv_power")
    @Schema(description = "光伏输入功率/kW")
    Float pvPower;
    /**
     * 光伏直流电压/V
     */
    @TableField("pv_dc_volt")
    @Schema(description = "光伏直流电压/V")
    Float pvDCVolt;
    /**
     * 光伏直流电流/A
     */
    @TableField("pv_dc_cur")
    @Schema(description = "光伏直流电流/A")
    Float pvDCCur;
    /**
     * 逆变器运行状态
     */
    @TableField("inv_state")
    @Schema(description = "逆变器运行状态")
    Float invState;
    /**
     * 逆变器故障信息
     */
    @TableField("inv_fault")
    @Schema(description = "逆变器故障信息")
    Float invFault;
}
