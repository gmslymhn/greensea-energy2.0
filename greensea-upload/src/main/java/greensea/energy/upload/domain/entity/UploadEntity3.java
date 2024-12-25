package greensea.energy.upload.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.model.Timelnfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UploadEntity3
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 14:33
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadEntity3 {
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

    public UploadEntity3(UploadDto2 uploadDto2){
        this.timeStamp = uploadDto2.getTimeStamp();
        this.cityName = uploadDto2.getCityName();
        this.lon = uploadDto2.getLon();
        this.lat = uploadDto2.getLat();
        this.timelnfo = uploadDto2.getTimelnfo().toString();
        this.weather = uploadDto2.getWeather();
        this.electrovalence =uploadDto2.getElectrovalence();
    }

}
