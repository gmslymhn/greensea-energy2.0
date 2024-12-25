package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: DevicePowerEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 20:48
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dev_")
@Schema(description = "设备功率信息")
public class DevicePowerEntity {
    /**
     * 大锂电池电压/V
     */
    @Schema(description = "大锂电池电压/V")
    @TableField("lbms_volt")
    private Float lbmsVolt;
    /**
     * 大锂电池电流/A
     */
    @Schema(description = "大锂电池电流/A")
    @TableField("lbms_cur")
    private Float lbmsCur;

    /**
     * 上传时间
     */
    @Schema(description = "上传时间")
    @TableField("upload_time")
    private LocalDateTime uploadTime;
}
