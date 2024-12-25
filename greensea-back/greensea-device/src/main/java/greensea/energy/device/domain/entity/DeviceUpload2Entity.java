package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.dto.UploadDto2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DeviceUpload2Entity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 10:28
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceUpload2Entity {
    /**
     * 时间
     */
    @TableField("time_stamp")
    private String timeStamp;

    /**
     * 城市名
     */
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
    @TableField("time_lnfo")
    private String timelnfo;
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
