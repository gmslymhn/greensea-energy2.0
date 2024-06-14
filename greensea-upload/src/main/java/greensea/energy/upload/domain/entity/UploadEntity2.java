package greensea.energy.upload.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UploadEntity2
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-13 16:43
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadEntity2 {
    /**
     * 逆变器当日发电量/kWh
     */
    @TableField("inv_day_output_plant")
    Float invDayOutputPlant;
    /**
     * 逆变器当月发电量/kWh
     */
    @TableField("inv_month_output_plant")
    Float invMonthOutputPlant;
    /**
     * 逆变器当年发电量/MWh
     */
        @TableField("inv_year_output_plant")
    Float invYearOutputPlant;
    /**
     * 逆变器累计总发电量/MWh
     */
        @TableField("inv_total_output_plant")
    Float invTotalOutputPlant;
    /**
     * 当日收益/€
     */
    @TableField("day_revenue")
    Float dayRevenue;
    /**
     * 当月收益/€
     */
    @TableField("month_revenue")
    Float monthRevenue;
    /**
     * 当年收益/€
     */
    @TableField("year_revenue")
    Float yearRevenue;
    /**
     * 累计总收益/€
     */
    @TableField("total_revenue")
    Float totalRevenue;
    /**
     * 电网当日购电量
     */
    @TableField("day_pg_buy_plant")
    Float dayPGBuyPlant;
    /**
     * 电网当日卖电量/kWh
     */
    @TableField("day_pg_sell_plant")
    Float dayPGSellPlant;
    /**
     * 电网累计购电量/MWh
     */
    @TableField("total_pg_buy_plant")
    Float totalPGBuyPlant;
    /**
     * 电网累计卖电量/MWh
     */
    @TableField("total_pg_sell_plant")
    Float totalPGSellPlant;
    /**
     * 用户当日用电量/kWh
     */
    @TableField("user_day_use_plant")
    Float userDayUsePlant;
    /**
     * 用户累计用电量/MWh
     */
    @TableField("user_total_use_plant")
    Float userTotalUsePlant;
    /**
     * 当日PV发电量/kWh
     */
    @TableField("day_pv_output_plant")
    Float dayPVOutputPlant;
    /**
     * PV累计发电量/MWh
     */
    @TableField("total_pv_output_plant")
    Float totalPVOutputPlant;
    /**
     * 电网侧相电压/V
     */
    @TableField("pg_pha_volt")
    Float pgPhaVolt;
    /**
     * 电网侧相电流/A
     */
    @TableField("pg_pha_cur")
    Float pgPhaCur;
    /**
     * 电网侧输出功率/kW
     */
    @TableField("pg_output_power")
    Float pgOutputPower;
    /**
     * 逆变器侧相电压/V
     */
    @TableField("inv_pha_volt")
    Float invPhaVolt;
    /**
     * 逆变器侧相电流/A
     */
    @TableField("inv_pha_cur")
    Float invPhaCur;
    /**
     * 逆变器侧输出功率/kW
     */
    @TableField("inv_output_power")
    Float invOutputPower;
    /**
     * 光伏输入功率/kW
     */
    @TableField("pv_power")
    Float pvPower;
    /**
     * 光伏直流电压/V
     */
    @TableField("pv_dc_volt")
    Float pvDCVolt;
    /**
     * 光伏直流电流/A
     */
    @TableField("pv_dc_cur")
    Float pvDCCur;
    /**
     * 逆变器运行状态
     */
    @TableField("inv_state")
    Float invState;
    /**
     * 逆变器故障信息
     */
    @TableField("inv_fault")
    Float invFault;
}
