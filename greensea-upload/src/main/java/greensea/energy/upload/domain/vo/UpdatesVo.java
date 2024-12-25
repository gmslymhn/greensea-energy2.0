package greensea.energy.upload.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: UpdatesVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 12:56
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatesVo {
    private Boolean whetherUpdate;
    private Float latestVersion;

    private LocalDateTime updateTime;

    private String[] updateFileUrl;
}