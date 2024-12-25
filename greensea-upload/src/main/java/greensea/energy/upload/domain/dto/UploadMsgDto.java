package greensea.energy.upload.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UploadMsgDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 15:22
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadMsgDto {
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField("device_number")
    private String deviceNumber;
    /**
     * 时间
     */
    @TableField("time_stamp")
    private String timeStamp;


    /**
     * id
     */
    @TableField("frame_id")
    private String frameId;

    /**
     * frameType 帧类型
     */
    @TableField("frame_type")
    private Integer frameType;

    /**
     * CANData 报文
     */
    @TableField("CAN_data")
    private String canData;
}
