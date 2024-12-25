package greensea.energy.upload.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Timelnfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 14:35
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "时间信息")
public class Timelnfo {

    @Schema(description = "年")
    private Integer year;
    @Schema(description = "月")
    private Integer month;
    @Schema(description = "日")
    private Integer day;
    @Schema(description = "小时")
    private Integer hour;

}
