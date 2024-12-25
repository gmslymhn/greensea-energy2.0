package greensea.energy.upload.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DevUpdateEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 20:09
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gre_dev_update")
@Schema(description = "设备更新信息")
public class DevUpdateEntity{
    /**
     * id
     */
    @TableId(value = "updata_id",type = IdType.AUTO)
    Integer updataId;

    /**
     * 文件名
     */
    @Schema(description = "文件名")
    @TableField("file_name")
    @Size(min=1, max=30,message="文件名长度必须在 1 ~ 30 字符之间")
    private String fileName;


    /**
     * 文件名
     */
    @Schema(description = "文件类型")
    @TableField("file_type")
    @Size(min=1, max=20,message="文件类型长度必须在 1 ~ 20 字符之间")
    private String fileType;

    /**
     * 文件名
     */
    @Schema(description = "文件版本")
    @TableField("file_version")
    private Integer fileVersion;

    /**
     * 文件名
     */
    @Schema(description = "所属项目")
    @TableField("project_name")
    @Size(min=1, max=50,message="所属项目长度必须在 1 ~ 50 字符之间")
    private String projectName;


    /**
     * 文件名
     */
    @Schema(description = "文件urlid")
    @TableField("file_url_id")
    private Integer fileUrlId;

    /**
     * 文件名
     */
    @Schema(description = "文件url")
    @TableField(exist = false)
    private String fileUrl;

    /**
     * 创建用户
     */
    @TableField("create_gm")
    @JsonIgnore
    private String createUser;
    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField("create_time")
    private String createTime;
    /**
     * 修改用户
     */
    @JsonIgnore
    @TableField("update_gm")
    private String updateUser;
    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField("update_time")
    private String updateTime;
    /**
     * 逻辑删除(6删除 0未删除)
     */
    @JsonIgnore
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;
}
