package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DeviceEntity
 * @Description: 设备信息
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 01:40
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dev_")
@Schema(description = "设备图表信息")
public class DeviceUploadEntity extends DeviceBaseEntity {

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;
}
