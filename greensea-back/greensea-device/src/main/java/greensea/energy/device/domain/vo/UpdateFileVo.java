package greensea.energy.device.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.device.domain.entity.DevUpdateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: UpdateFileVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 21:10
 * @Version: 1.0
 **/
@Data
public class UpdateFileVo {

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
    @Schema(description = "文件url")
    @TableField(exist = false)
    private String fileUrl;
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


    public UpdateFileVo(DevUpdateEntity devUpdateEntity){
        this.fileName = devUpdateEntity.getFileName();
        this.fileUrl = devUpdateEntity.getFileUrl();
        this.fileType = devUpdateEntity.getFileType();
        this.fileVersion = devUpdateEntity.getFileVersion();
        this.updateTime = devUpdateEntity.getUpdateTime();
        if (ObjectUtils.isNull(devUpdateEntity.getUpdateUser())){
            this.updateUser = devUpdateEntity.getCreateUser();
        }else {
            this.updateUser = devUpdateEntity.getUpdateUser();
        }
        this.projectName = devUpdateEntity.getProjectName();
    }
}
