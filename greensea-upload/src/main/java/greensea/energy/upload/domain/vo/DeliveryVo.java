package greensea.energy.upload.domain.vo;

import greensea.energy.upload.domain.model.Timelnfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: DeliveryVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 15:43
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryVo {

    private String timeStamp;
    private Integer sysStartStop;

    private Integer faultReset;
    private Timelnfo timelnfo;
}
