package greensea.energy.upload.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.model.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UploadDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-27 12:53
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "上传信息")
public class UploadDto {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 上传时间
     */
    @Schema(description = "上传时间")
    private String timeStamp;
    /**
     * 故障信息
     */
    @Schema(description = "故障信息")
    private FaultInfo faultInfo;
    /**
     * 燃料电池信息
     */
    @Schema(description = "燃料电池信息")
    private FCInfo fcInfo;
    /**
     * 电解槽信息
     */
    @Schema(description = "电解槽信息")
    private PEMH2Info pemh2Info;
    /**
     * 大锂电池信息
     */
    @Schema(description = "大锂电池信息")
    private LBMSInfo lbmsInfo;
    /**
     * 小锂电池信息
     */
    @Schema(description = "小锂电池信息")
    private SBMSInfo sbmsInfo;
    /**
     * 逆变器信息
     */
    @Schema(description = "逆变器信息")
    private InvInfo invInfo;

}
