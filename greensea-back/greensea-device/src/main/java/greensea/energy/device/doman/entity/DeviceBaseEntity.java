package greensea.energy.device.doman.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * @ClassName: DeviceBaseEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:11
 * @Version: 1.0
 **/
public class DeviceBaseEntity {
    /**
     * 系统故障等级
     */
    @Schema(description = "系统故障等级")
    @TableField("sys_fault_lvl")
    Integer sysFaultLvl;
    /**
     * 系统故障码
     */
    @Schema(description = "系统故障码")
    @TableField("sys_fault_code")
    Integer sysFaultCode;
    /**
     * 燃料电池状态
     */
    @Schema(description = "燃料电池状态")
    @TableField("fc_status")
    Integer fcStatus;
    /**
     * 燃料电池电堆功率/Kw
     */
    @Schema(description = "燃料电池电堆功率/Kw")
    @TableField("fc_sys_power")
    Float fcStackPower;
    /**
     * 燃料电池电压/V
     */
    @Schema(description = "燃料电池电压/V")
    @TableField("fc_stack_volt")
    Float fcStackVolt;
    /**
     * 燃料电池电流/A
     */
    @Schema(description = "燃料电池电流/A")
    @TableField("fc_stack_cur")
    Float fcStackCur;
    /**
     * 燃料电池DCDC输出电压/V
     */
    @Schema(description = "燃料电池DCDC输出电压/V")
    @TableField("fc_dcdc_volt_out")
    Float fcDCDCVoltOut;
    /**
     * 燃料电池DCDC输出电流/A
     */
    @Schema(description = "燃料电池DCDC输出电流/A")
    @TableField("fc_dcdc_cur_out")
    Float fcDCDCCurOut;
    /**
     * 燃料电池系统功率/kW
     */
    @Schema(description = "燃料电池系统功率/kW")
    @TableField("fc_sys_power")
    Float fcSysPower;
    /**
     * 燃料电池年累计发电量/kWh
     */
    @Schema(description = "燃料电池年累计发电量/kWh")
    @TableField("fc_kwh")
    Float fckWh;
    /**
     * 电解槽状态
     */
    @Schema(description = "电解槽状态")
    @TableField("pemh2_status")
    Integer pemh2Status;
    /**
     * 电解槽DCDC的输出电压/V
     */
    @Schema(description = "电解槽DCDC的输出电压/V")
    @TableField("pemh2_dcdc_volt_out")
    Float pemh2DCDCVoltOut;
    /**
     *电解槽DCDC的输出电流/A
     */
    @Schema(description = "电解槽DCDC的输出电流/A")
    @TableField("pemh2_dcdc_cur_out")
    Float pemh2DCDCCurOut;
    /**
     * 电解槽年累计制氢量/Nm3
     */
    @Schema(description = "电解槽年累计制氢量/Nm3")
    @TableField("pemh2_pro")
    Float pemh2Pro;
    /**
     * 大锂电池电压/V
     */
    @Schema(description = "大锂电池电压/V")
    @TableField("lbms_volt")
    Float lbmsVolt;
    /**
     * 大锂电池电流/A
     */
    @Schema(description = "大锂电池电流/A")
    @TableField("lbms_cur")
    Float lbmsCur;
    /**
     * 大锂电池SOC
     */
    @Schema(description = "大锂电池SOC")
    @TableField("lbms_soc")
    Float lbmsSOC;
    /**
     * 小锂电池电压/V
     */
    @Schema(description = "小锂电池电压/V")
    @TableField("sbms_volt")
    Float sbmsVolt;
    /**
     * 小锂电池电流/A
     */
    @Schema(description = "")
    @TableField("sbms_cur")
    Float sbmsCur;
    /**
     * 小锂电池SOC
     */
    @Schema(description = "小锂电池SOC")
    @TableField("sbms_soc")
    Float sbmsSOC;
    /**
     * 上传时间
     */
    @Schema(description = "上传时间")
    @TableField("upload_time")
    LocalDateTime uploadTime;
}
