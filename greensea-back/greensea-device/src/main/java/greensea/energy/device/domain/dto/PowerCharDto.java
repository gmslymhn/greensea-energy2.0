package greensea.energy.device.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: PowerCharDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 21:01
 * @Version: 1.0
 **/
@Schema(description = "获取总功率图表类")
@Data
public class PowerCharDto {
    @Schema(description = "国家code")
    @NotNull(message = "国家code不能为空")
    @Size(min = 2,max = 2,message = "国家code长度必须为2")
    String areacode;

    /**
     * 设备id
     */
    @Schema(description = "图表精度")
    @NotNull(message = "图表精度不能为null")
    private Integer precision;

    @Schema(description = "页码")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNum;

    @Schema(description = "每页条数")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 10000, message = "每页条数最大值为 10000")
    private Integer pageSize;
}
