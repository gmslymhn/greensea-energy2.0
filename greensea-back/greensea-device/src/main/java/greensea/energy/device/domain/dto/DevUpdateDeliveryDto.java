package greensea.energy.device.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: DevUpdateDelivery
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 21:47
 * @Version: 1.0
 **/
@Schema(description = "更新数据下发类")
@Data
public class DevUpdateDeliveryDto {

    /**
     * 文件名
     */
    @Schema(description = "文件类型")
    @TableField("file_type")
    @Size(min=1, max=20,message="文件类型长度必须在 1 ~ 20 字符之间")
    private String fileType;
    /**
     * 文件名
     */
    @Schema(description = "所属项目")
    @TableField("project_name")
    @Size(min=1, max=50,message="所属项目长度必须在 1 ~ 50 字符之间")
    private String projectName;
}
