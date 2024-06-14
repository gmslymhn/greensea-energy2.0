package greensea.energy.upload.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.upload.domain.entity.UploadEntity;
import greensea.energy.upload.domain.entity.UploadEntity2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

/**
 * @ClassName: UploadMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 15:11
 * @Version: 1.0
 **/
@Mapper
public interface UploadMapper extends BaseMapper<UploadEntity> {


    @Insert("insert into ${tableName}(sys_fault_lvl,sys_fault_code,fc_status,fc_stack_power,fc_stack_volt,fc_stack_cur,fc_dcdc_volt_out,fc_dcdc_cur_out,fc_sys_power" +
            ",fc_kwh,pemh2_status,pemh2_dcdc_volt_out,pemh2_dcdc_cur_out,pemh2_pro,lbms_volt,lbms_cur,lbms_soc,sbms_volt,sbms_cur,sbms_soc) values(#{uploadEntity.sysFaultLvl},#{uploadEntity.sysFaultCode},#{uploadEntity.fcStatus}" +
            ",#{uploadEntity.fcStackPower},#{uploadEntity.fcStackVolt},#{uploadEntity.fcStackCur},#{uploadEntity.fcDCDCVoltOut},#{uploadEntity.fcDCDCCurOut}," +
            "#{uploadEntity.fcSysPower},#{uploadEntity.fckWh},#{uploadEntity.pemh2Status},#{uploadEntity.pemh2DCDCVoltOut},#{uploadEntity.pemh2DCDCCurOut}," +
            "#{uploadEntity.pemh2Pro},#{uploadEntity.lbmsVolt},#{uploadEntity.lbmsCur},#{uploadEntity.lbmsSOC},#{uploadEntity.sbmsVolt},#{uploadEntity.sbmsCur},#{uploadEntity.sbmsSOC})")
    Integer uplpad1(String tableName,UploadEntity uploadEntity);

    @Insert("insert into ${tableName}(inv_day_output_plant,inv_month_output_plant,inv_year_output_plant,inv_total_output_plant,day_revenue,month_revenue,year_revenue," +
            "total_revenue,day_pg_buy_plant,day_pg_sell_plant,total_pg_buy_plant,total_pg_sell_plant,user_day_use_plant,user_total_use_plant,day_pv_output_plant," +
            "total_pv_output_plant,pg_pha_volt,pg_pha_cur,pg_output_power,inv_pha_volt,inv_pha_cur,inv_output_power,pv_power,pv_dc_volt,pv_dc_cur,inv_state,inv_fault) " +
            "values(#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant}," +
            "#{uploadEntity2.dayRevenue},#{uploadEntity2.monthRevenue},#{uploadEntity2.yearRevenue},#{uploadEntity2.totalRevenue}," +
            "#{uploadEntity2.dayPGBuyPlant},#{uploadEntity2.dayPGSellPlant},#{uploadEntity2.totalPGBuyPlant},#{uploadEntity2.totalPGSellPlant}," +
            "#{uploadEntity2.userDayUsePlant},#{uploadEntity2.userTotalUsePlant},#{uploadEntity2.dayPVOutputPlant},#{uploadEntity2.totalPVOutputPlant}," +
            "#{uploadEntity2.pgPhaVolt},#{uploadEntity2.pgPhaCur},#{uploadEntity2.pgOutputPower},#{uploadEntity2.invPhaVolt}," +
            "#{uploadEntity2.invPhaCur},#{uploadEntity2.invOutputPower},#{uploadEntity2.pvPower},#{uploadEntity2.pvDCVolt}," +
            "#{uploadEntity2.pvDCCur},#{uploadEntity2.invState},#{uploadEntity2.invFault})")
    Integer uplpad2(String tableName, UploadEntity2 uploadEntity2);

}








