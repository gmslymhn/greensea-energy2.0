package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DeviceUpload1Entity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 20:36
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("inv_")
@Schema(description = "设备图表信息1")
public class DeviceUpload1Entity {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;
}
