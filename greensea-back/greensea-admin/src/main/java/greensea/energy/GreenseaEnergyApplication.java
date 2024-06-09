package greensea.energy;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot3.autoconfigure.properties.DruidStatProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {
        DruidDataSourceAutoConfigure.class
})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
public class GreenseaEnergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenseaEnergyApplication.class, args);
    }

}
