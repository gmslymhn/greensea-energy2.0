package greensea.energy.upload.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.webmvc.core.fn.SpringdocRouteBuilder;

import java.time.LocalDateTime;

/**
 * @ClassName: UpdateVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 12:52
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVo {
    private Boolean whetherUpdate;
    private Float latestVersion;

    private LocalDateTime updateTime;

    private String updateFileUrl;
}
