package greensea.energy.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: OSSConfig
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-06 23:32
 * @Version: 1.0
 **/
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
@Data
public class OSSConfig {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private Integer expiration;

}