package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.domain.entity.DeviceUpload1Entity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: DeviceUpload2Mapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 20:35
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceUpload2Mapper extends BaseMapper<DeviceUpload1Entity> {
    /**
     * 动态建表
     *
     * @param tableName
     * @return int
     */
    @Insert(
            "CREATE TABLE `${tableName}`  (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `inv_day_output_plant` float NULL DEFAULT NULL COMMENT '逆变器当日发电量/kWh',\n" +
                    "  `inv_month_output_plant` float NULL DEFAULT NULL COMMENT '逆变器当月发电量/kWh',\n" +
                    "  `inv_year_output_plant` float NULL DEFAULT NULL COMMENT '逆变器当年发电量/MWh',\n" +
                    "  `inv_total_output_plant` float NULL DEFAULT NULL COMMENT '逆变器累计总发电量/MWh',\n" +
                    "  `day_revenue` float NULL DEFAULT NULL COMMENT '当日收益/€',\n" +
                    "  `month_revenue` float NULL DEFAULT NULL COMMENT '当月收益/€',\n" +
                    "  `year_revenue` float NULL DEFAULT NULL COMMENT '当年收益/€',\n" +
                    "  `total_revenue` float NULL DEFAULT NULL COMMENT '累计总收益/€',\n" +
                    "  `day_pg_buy_plant` float NULL DEFAULT NULL COMMENT '电网当日购电量',\n" +
                    "  `day_pg_sell_plant` float NULL DEFAULT NULL COMMENT '电网当日卖电量/kWh',\n" +
                    "  `total_pg_buy_plant` float NULL DEFAULT NULL COMMENT '电网累计购电量/MWh',\n" +
                    "  `total_pg_sell_plant` float NULL DEFAULT NULL COMMENT '电网累计卖电量/MWh',\n" +
                    "  `user_day_use_plant` float NULL DEFAULT NULL COMMENT '用户当日用电量/kWh',\n" +
                    "  `user_total_use_plant` float NULL DEFAULT NULL COMMENT '用户累计用电量/MWh',\n" +
                    "  `day_pv_output_plant` float NULL DEFAULT NULL COMMENT '当日PV发电量/kWh',\n" +
                    "  `total_pv_output_plant` float NULL DEFAULT NULL COMMENT 'PV累计发电量/MWh',\n" +
                    "  `pg_pha_volt` float NULL DEFAULT NULL COMMENT '电网侧相电压/V',\n" +
                    "  `pg_pha_cur` float NULL DEFAULT NULL COMMENT '电网侧相电流/A',\n" +
                    "  `pg_output_power` float NULL DEFAULT NULL COMMENT '电网侧输出功率/kW',\n" +
                    "  `inv_pha_volt` float NULL DEFAULT NULL COMMENT '逆变器侧相电压/V',\n" +
                    "  `inv_pha_cur` float NULL DEFAULT NULL COMMENT '逆变器侧相电流/A',\n" +
                    "  `inv_output_power` float NULL DEFAULT NULL COMMENT '逆变器侧输出功率/kW',\n" +
                    "  `pv_power` float NULL DEFAULT NULL COMMENT '光伏输入功率/kW',\n" +
                    "  `pv_dc_volt` float NULL DEFAULT NULL COMMENT '光伏直流电压/V',\n" +
                    "  `pv_dc_cur` float NULL DEFAULT NULL COMMENT '光伏直流电流/A',\n" +
                    "  `inv_state` float NULL DEFAULT NULL COMMENT '逆变器运行状态',\n" +
                    "  `inv_fault` float NULL DEFAULT NULL COMMENT '逆变器故障信息',\n" +
                    "  `upload_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  PRIMARY KEY (`id`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    int createNewTable2(@Param("tableName") String tableName);
}
