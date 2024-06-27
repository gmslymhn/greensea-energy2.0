package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SBMSInfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:51
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "小锂电池信息")
public class SBMSInfo {
    /**
     * 小锂电池电压/V
     */
    @TableField("sbms_volt")
    @Schema(description = "小锂电池电压/V")
    Float sbmsVolt;
    /**
     * 小锂电池电流/A
     */
    @TableField("sbms_cur")
    @Schema(description = "小锂电池电流/A")
    Float sbmsCur;
    /**
     * 小锂电池SOC
     */
    @TableField("sbms_soc")
    @Schema(description = "小锂电池SOC")
    Float sbmsSOC;
}
