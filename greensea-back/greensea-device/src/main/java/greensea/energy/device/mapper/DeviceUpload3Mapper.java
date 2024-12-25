package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.domain.entity.DeviceUpload2Entity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: DeviceUpload3Mapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 10:25
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceUpload3Mapper extends BaseMapper<DeviceUpload2Entity> {

  /**
   * 动态建表
   *
   * @param tableName
   * @return int
   */
  @Insert(
          "    CREATE TABLE `${tableName}`  (\n" +
                  "            `id` int NOT NULL AUTO_INCREMENT,\n" +
                  "  `time_stamp` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                  "  `city_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                  "  `lon` float NULL DEFAULT NULL,\n" +
                  "  `lat` float NULL DEFAULT NULL,\n" +
                  "  `time_lnfo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                  "  `weather` int NULL DEFAULT NULL,\n" +
                  "  `electrovalence` float NULL DEFAULT NULL,\n" +
                  "  `upload_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
                  "    PRIMARY KEY (`id`) USING BTREE\n" +
                  ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
  int createNewTable3(@Param("tableName") String tableName);
}
