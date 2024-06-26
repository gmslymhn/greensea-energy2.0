package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.doman.entity.DeviceUploadEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: DeviceMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 01:47
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceUpload1Mapper extends BaseMapper<DeviceUploadEntity> {
    /**
     * 动态建表
     *
     * @param tableName
     * @return int
     */
    @Insert(
            "CREATE TABLE `${tableName}`  (\n" +
            "  `id` int NOT NULL AUTO_INCREMENT,\n" +
            "  `sys_fault_lvl` int NULL DEFAULT NULL COMMENT '系统故障等级',\n" +
            "  `sys_fault_code` int NULL DEFAULT NULL COMMENT '系统故障码',\n" +
            "  `fc_status` int NULL DEFAULT NULL COMMENT '燃料电池状态',\n" +
            "  `fc_stack_power` float NULL DEFAULT NULL COMMENT '燃料电池电堆功率/Kw',\n" +
            "  `fc_stack_volt` float NULL DEFAULT NULL COMMENT '燃料电池电压/V',\n" +
            "  `fc_stack_cur` float NULL DEFAULT NULL COMMENT '燃料电池电流/A',\n" +
            "  `fc_dcdc_volt_out` float NULL DEFAULT NULL COMMENT '燃料电池DCDC输出电压/V',\n" +
            "  `fc_dcdc_cur_out` float NULL DEFAULT NULL COMMENT '燃料电池DCDC输出电流/A',\n" +
            "  `fc_sys_power` float NULL DEFAULT NULL COMMENT '燃料电池系统功率/kW',\n" +
            "  `fc_kwh` float NULL DEFAULT NULL COMMENT '燃料电池年累计发电量/kWh',\n" +
            "  `pemh2_status` int NULL DEFAULT NULL COMMENT '电解槽状态',\n" +
            "  `pemh2_dcdc_volt_out` float NULL DEFAULT NULL COMMENT '电解槽DCDC的输出电压/V',\n" +
            "  `pemh2_dcdc_cur_out` float NULL DEFAULT NULL COMMENT '电解槽DCDC的输出电流/A',\n" +
            "  `pemh2_pro` float NULL DEFAULT NULL COMMENT '电解槽年累计制氢量/Nm3',\n" +
            "  `lbms_volt` float NULL DEFAULT NULL COMMENT '大锂电池电压/V',\n" +
            "  `lbms_cur` float NULL DEFAULT NULL COMMENT '大锂电池电流/A',\n" +
            "  `lbms_soc` float NULL DEFAULT NULL COMMENT '大锂电池SOC',\n" +
            "  `sbms_volt` float NULL DEFAULT NULL COMMENT '小锂电池电压/V',\n" +
            "  `sbms_cur` float NULL DEFAULT NULL COMMENT '小锂电池电流/A',\n" +
            "  `sbms_soc` float NULL DEFAULT NULL COMMENT '大锂电池SOC',\n" +
            "  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间精确到秒',\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;\n")
    int createNewTable1(@Param("tableName") String tableName);
}
