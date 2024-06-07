package greensea.energy.upload.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.upload.domain.entity.UploadEntity;
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
//    @Results({
//            @Result(property = "sysFaultLvl", column = "sys_fault_lvl"),
//    })

    @Insert("insert into ${tableName}(sys_fault_lvl,sys_fault_code,fc_status,fc_stack_power,fc_stack_volt,fc_stack_cur,fc_dcdc_volt_out,fc_dcdc_cur_out,fc_sys_power" +
            ",fc_kwh,pemh2_status,pemh2_dcdc_volt_out,pemh2_dcdc_cur_out,pemh2_pro,lbms_volt,lbms_cur,lbms_soc,sbms_volt,sbms_cur,sbms_soc) values(#{uploadEntity.sysFaultLvl},#{uploadEntity.sysFaultCode},#{uploadEntity.fcStatus}" +
            ",#{uploadEntity.fcStackPower},#{uploadEntity.fcStackVolt},#{uploadEntity.fcStackCur},#{uploadEntity.fcDCDCVoltOut},#{uploadEntity.fcDCDCCurOut}," +
            "#{uploadEntity.fcSysPower},#{uploadEntity.fckWh},#{uploadEntity.pemh2Status},#{uploadEntity.pemh2DCDCVoltOut},#{uploadEntity.pemh2DCDCCurOut}," +
            "#{uploadEntity.pemh2Pro},#{uploadEntity.lbmsVolt},#{uploadEntity.lbmsCur},#{uploadEntity.lbmsSOC},#{uploadEntity.sbmsVolt},#{uploadEntity.sbmsCur},#{uploadEntity.sbmsSOC})")
    Integer uplpad(String tableName,UploadEntity uploadEntity);
}








