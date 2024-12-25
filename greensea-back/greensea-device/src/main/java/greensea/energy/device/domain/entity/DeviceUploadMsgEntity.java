package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: DeviceUploadMsgMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 15:51
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("msg_")
public class DeviceUploadMsgEntity {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    Integer id;

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
    /**
     * 上传时间
     */
    @Schema(description = "上传时间")
    @TableField("upload_time")
    private LocalDateTime uploadTime;
}
