package greensea.energy.device.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import greensea.energy.device.domain.entity.DeviceBaseEntity;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.model.Device;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName: DeviceMsgEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:11
 * @Version: 1.0
 **/
@Data
@Schema(description = "设备详细信息")
@TableName("gre_device_msg")
public class DeviceMsg {

    private Device device;

    private UploadDto uploadDto;

    private UploadDto2 uploadDto2;
}
