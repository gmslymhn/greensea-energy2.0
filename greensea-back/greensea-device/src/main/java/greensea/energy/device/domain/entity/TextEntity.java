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
 * @ClassName: TextEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 17:45
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gre_text")
@Schema(description = "文字信息")
public class TextEntity {
    @Schema(description = "文字id")
    @TableId(value = "text_id",type = IdType.AUTO)
    Integer texId;
    @Schema(description = "文字名称")
    @Size(min=1, max=20,message="文字名称长度必须在 1 ~ 20 字符之间")
    @TableField(value = "text_name")
    String textName;

    @Schema(description = "文字类型")
    @Size(min=1, max=20,message="文字名称长度必须在 1 ~ 20 字符之间")
    @TableField(value = "text_type")
    String textType;

    @Schema(description = "文字key")
    @Size(min=1, max=20,message="文字key长度必须在 1 ~ 20 字符之间")
    @TableField(value = "text_key")
            @JsonIgnore
    String textKey;
    @Schema(description = "文字简介")
    @Size(min=1, max=20,message="文字简介长度必须在 1 ~ 200 字符之间")
    @TableField(value = "text_introduce")
    String textIntroduce;
    @Schema(description = "文字内容")
    @TableField(value = "text_content")
    String textContent;
    @Schema(description = "修改管理员")
    @TableField(value = "update_gm")
    String updateGm;
    @Schema(description = "修改时间")
    @TableField(value = "update_time")
    String updateTime;
}
