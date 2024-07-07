package greensea.energy.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import greensea.energy.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CarsouselEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-07 17:32
 * @Version: 1.0
 **/
@TableName("gre_carsousel")
@Data
@Schema(description = "轮播图实体类")
@NoArgsConstructor
public class CarsouselEntity extends BaseEntity {
    /**
     * 轮播图id
     */
    @TableId(value = "carousel_id",type = IdType.AUTO)
    private Integer carouselId;
    /**
     * 轮播图名称
     */
    @TableField(value = "carousel_name")
    @Size(min = 1, max = 30, message = "轮播图名称必须在1~30字符之间")
    private Integer carouselName;
    /**
     * 图片id
     */
    @TableField("carousel_image_id")
    private Integer carouselImageId;
    /**
     * 状态
     */
    @TableField("state")
    private Boolean state;
    /**
     * 权重
     */
    @TableField("sort")
    private Integer sort;
}
