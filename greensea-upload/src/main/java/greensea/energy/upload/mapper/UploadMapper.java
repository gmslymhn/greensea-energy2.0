package greensea.energy.upload.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.upload.domain.entity.MessageEntity;
import greensea.energy.upload.domain.entity.UploadEntity;
import greensea.energy.upload.domain.entity.UploadEntity3;
import greensea.energy.upload.domain.model.InvInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
    Integer upload1(String tableName,UploadEntity uploadEntity);

//    @Insert("insert into ${tableName}(inv_day_output_plant,inv_month_output_plant,inv_year_output_plant,inv_total_output_plant,day_revenue,month_revenue,year_revenue," +
//            "total_revenue,day_pg_buy_plant,day_pg_sell_plant,total_pg_buy_plant,total_pg_sell_plant,user_day_use_plant,user_total_use_plant,day_pv_output_plant," +
//            "total_pv_output_plant,pg_pha_volt,pg_pha_cur,pg_output_power,inv_pha_volt,inv_pha_cur,inv_output_power,pv_power,pv_dc_volt,pv_dc_cur,inv_state,inv_fault) " +
//            "values(#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant},#{uploadEntity2.invDayOutputPlant}," +
//            "#{uploadEntity2.dayRevenue},#{uploadEntity2.monthRevenue},#{uploadEntity2.yearRevenue},#{uploadEntity2.totalRevenue}," +
//            "#{uploadEntity2.dayPGBuyPlant},#{uploadEntity2.dayPGSellPlant},#{uploadEntity2.totalPGBuyPlant},#{uploadEntity2.totalPGSellPlant}," +
//            "#{uploadEntity2.userDayUsePlant},#{uploadEntity2.userTotalUsePlant},#{uploadEntity2.dayPVOutputPlant},#{uploadEntity2.totalPVOutputPlant}," +
//            "#{uploadEntity2.pgPhaVolt},#{uploadEntity2.pgPhaCur},#{uploadEntity2.pgOutputPower},#{uploadEntity2.invPhaVolt}," +
//            "#{uploadEntity2.invPhaCur},#{uploadEntity2.invOutputPower},#{uploadEntity2.pvPower},#{uploadEntity2.pvDCVolt}," +
//            "#{uploadEntity2.pvDCCur},#{uploadEntity2.invState},#{uploadEntity2.invFault})")
//    Integer uplpad2(String tableName, UploadEntity2 uploadEntity2);
    @Insert("insert into ${tableName}(inv_day_output_plant,inv_month_output_plant,inv_year_output_plant,inv_total_output_plant,day_revenue,month_revenue,year_revenue," +
            "total_revenue,day_pg_buy_plant,day_pg_sell_plant,total_pg_buy_plant,total_pg_sell_plant,user_day_use_plant,user_total_use_plant,day_pv_output_plant," +
            "total_pv_output_plant,pg_pha_volt,pg_pha_cur,pg_output_power,inv_pha_volt,inv_pha_cur,inv_output_power,pv_power,pv_dc_volt,pv_dc_cur,inv_state,inv_fault) " +
            "values(#{invInfo.invDayOutputPlant},#{invInfo.invDayOutputPlant},#{invInfo.invDayOutputPlant},#{invInfo.invDayOutputPlant}," +
            "#{invInfo.dayRevenue},#{invInfo.monthRevenue},#{invInfo.yearRevenue},#{invInfo.totalRevenue}," +
            "#{invInfo.dayPGBuyPlant},#{invInfo.dayPGSellPlant},#{invInfo.totalPGBuyPlant},#{invInfo.totalPGSellPlant}," +
            "#{invInfo.userDayUsePlant},#{invInfo.userTotalUsePlant},#{invInfo.dayPVOutputPlant},#{invInfo.totalPVOutputPlant}," +
            "#{invInfo.pgPhaVolt},#{invInfo.pgPhaCur},#{invInfo.pgOutputPower},#{invInfo.invPhaVolt}," +
            "#{invInfo.invPhaCur},#{invInfo.invOutputPower},#{invInfo.pvPower},#{invInfo.pvDCVolt}," +
            "#{invInfo.pvDCCur},#{invInfo.invState},#{invInfo.invFault})")
    Integer upload2(String tableName, InvInfo invInfo);

    @Insert("insert into ${tableName}(time_stamp,city_name,lon,lat,time_lnfo,weather,electrovalence)"+
            "values(#{uploadEntity3.timeStamp},#{uploadEntity3.cityName},#{uploadEntity3.lon}," +
            "#{uploadEntity3.lat},#{uploadEntity3.timelnfo},#{uploadEntity3.weather},#{uploadEntity3.electrovalence})")
    Integer upload3(String tableName, UploadEntity3 uploadEntity3);



    @Insert("insert into ${tableName}(time_stamp,frame_id,frame_type,CAN_data)"+
            "values(#{messageEntity.timeStamp},#{messageEntity.frameId},#{messageEntity.frameType}," +
            "#{messageEntity.canData})")
    Integer upload4(String tableName, MessageEntity messageEntity);


}








