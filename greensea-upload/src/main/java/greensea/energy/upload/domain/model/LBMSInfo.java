package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LBMSInfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:51
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "大锂电池信息")
public class LBMSInfo {
    /**
     * 大锂电池电压/V
     */
    @TableField("lbms_volt")
    @Schema(description = "大锂电池电压/V")
    Float lbmsVolt;
    /**
     * 大锂电池电流/A
     */
    @TableField("lbms_cur")
    @Schema(description = "大锂电池电流/A")
    Float lbmsCur;
    /**
     * 大锂电池SOC
     */
    @TableField("lbms_soc")
    @Schema(description = "大锂电池SOC")
    Float lbmsSOC;
}
