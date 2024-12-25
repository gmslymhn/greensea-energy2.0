package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AbnormalEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-13 12:46
 * @Version: 1.0
 **/
@TableName("gre_abnormal_log")
@Data
@Schema(description = "异常实体类")
@NoArgsConstructor
public class AbnormalEntity {
    /**
     * 异常id
     */
    @Schema(description = "异常id")
    @TableId(value = "abnormal_id",type = IdType.AUTO)
    private Integer abnormalId;
    /**
     * ip
     */
    @Schema(description = "ip")
    @TableField(value = "ip")
    private String ip;
    /**
     * 设备序列号
     */
    @Schema(description = "设备序列号")
    @TableField(value = "device_number")
    private String deviceNumber;
    /**
     * 请求内容
     */
    @Schema(description = "请求内容")
    @TableField(value = "request_content")
    private String requestContent;
    /**
     * 返回信息
     */
    @Schema(description = "返回信息")
    @TableField(value = "result_message")
    private String resultMessage;
    /**
     * 请求时间
     */
    @Schema(description = "请求时间")
    @TableField(value = "request_time")
    private String requestTime;
}
