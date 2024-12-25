package greensea.energy.upload.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.dto.UploadMsgDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: MessageEntity
 * @Description:报文数据库类
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 15:11
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
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
    public MessageEntity(UploadMsgDto  uploadMsgDto){
        this.timeStamp = uploadMsgDto.getTimeStamp();
        this.frameId = uploadMsgDto.getFrameId();
        this.canData = uploadMsgDto.getCanData();
        this.frameType = uploadMsgDto.getFrameType();
    }
}
