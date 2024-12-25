package greensea.energy.upload.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.model.Timelnfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UploadDto2
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 14:51
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadDto2 {
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
     * 城市名
     */
    @Size(max = 20,message = "城市名异常！")
    @TableField("city_name")
    private String cityName;

    /**
     * 经度
     */
    @TableField("lon")
    private float lon;
    /**
     * 纬度
     */
    @TableField("lat")
    private float lat;
    /**
     * 时间
     */
    private Timelnfo timelnfo;

    /**
     * 天气
     */
    @TableField("weather")
    private Integer weather;


    /**
     * 电价
     */
    @TableField("electrovalence")
    private float electrovalence;
}
