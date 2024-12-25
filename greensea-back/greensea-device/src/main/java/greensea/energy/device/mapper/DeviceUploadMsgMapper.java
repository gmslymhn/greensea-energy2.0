package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.domain.entity.DeviceUpload2Entity;
import greensea.energy.device.domain.entity.DeviceUploadMsgEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: DeviceUploadMsgMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 15:50
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceUploadMsgMapper extends BaseMapper<DeviceUploadMsgEntity> {
    /**
     * 动态建表
     *
     * @param tableName
     * @return int
     */
    @Insert(
            "    CREATE TABLE `${tableName}`  (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `time_stamp` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                    "  `frame_id` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                    "  `frame_type` int NULL DEFAULT NULL,\n" +
                    "  `CAN_data` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,\n" +
                    "  `upload_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  PRIMARY KEY (`id`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;"
    )
    int createNewTable4(@Param("tableName") String tableName);
}
