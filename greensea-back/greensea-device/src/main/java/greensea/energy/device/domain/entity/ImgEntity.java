package greensea.energy.device.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ImgEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 13:39
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gre_img")
@Schema(description = "图片信息")
public class ImgEntity {

    @Schema(description = "图片id")
    @TableId(value = "img_id",type = IdType.AUTO)
    Integer imgId;
    @Schema(description = "图片名称")
    @Size(min=1, max=20,message="图片名称长度必须在 1 ~ 20 字符之间")
    @TableField(value = "img_name")
    String imgName;

    @Schema(description = "图片类型")
    @Size(min=1, max=20,message="图片名称长度必须在 1 ~ 20 字符之间")
    @TableField(value = "img_type")
    String imgType;

    @Schema(description = "图片key")
    @Size(min=1, max=20,message="图片key长度必须在 1 ~ 20 字符之间")
    @TableField(value = "img_key")
    @JsonIgnore
    String imgKey;
    @Schema(description = "图片简介")
    @Size(min=1, max=20,message="图片简介长度必须在 1 ~ 200 字符之间")
    @TableField(value = "img_introduce")
    String imgIntroduce;
    @Schema(description = "图片内容")
    @TableField(value = "resource_id")
    Integer resourceId;
    @Schema(description = "图片url")
    @TableField(exist = false)
    String resourceUrl;
    @Schema(description = "修改管理员")
    @TableField(value = "update_gm")
    String updateGm;
    @Schema(description = "修改时间")
    @TableField(value = "update_time")
    String updateTime;
}
