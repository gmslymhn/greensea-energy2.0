package greensea.energy.upload.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FaultInfo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:48
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "故障信息")
public class FaultInfo {
    /**
     * 系统故障等级
     */
    @TableField("sys_fault_lvl")
    @Schema(description = "系统故障等级")
    Integer sysFaultLvl;
    /**
     * 系统故障码
     */
    @TableField("sys_fault_code")
    @Schema(description = "系统故障码")
    Integer sysFaultCode;
}
