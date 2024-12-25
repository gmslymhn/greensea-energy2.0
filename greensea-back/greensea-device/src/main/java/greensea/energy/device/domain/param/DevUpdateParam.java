package greensea.energy.device.domain.param;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: DevUpdateParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 20:20
 * @Version: 1.0
 **/
@Data
public class DevUpdateParam extends PageParam {

    /**
     * 文件名
     */
    @Schema(description = "文件类型")
    @Size(min=1, max=20,message="文件类型长度必须在 1 ~ 20 字符之间")
    @TableField("file_type")
    private String fileType;

    /**
     * 文件名
     */
    @Schema(description = "所属项目")
    @TableField("project_name")
    private String projectName;
}
