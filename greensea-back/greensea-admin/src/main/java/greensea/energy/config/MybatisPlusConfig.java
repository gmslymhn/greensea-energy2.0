package greensea.energy.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import greensea.energy.device.header.DeviceTableNameHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfig
 * @Description: MybatisPlus配置
 * @Author: gmslymhn
 * @CreateTime: 2024-05-18 15:03
 * @Version: 1.0
 **/
@Configuration
@MapperScan(basePackages = {"greensea.energy.framework.mapper","greensea.energy.device.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
//    @Bean
//    public PaginationInnerInterceptor paginationInterceptor() {
//        return new PaginationInnerInterceptor();
//    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //动态表名
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        //可以传多个表名参数，指定哪些表使用DayTableNameHandler处理表名称
        dynamicTableNameInnerInterceptor.setTableNameHandler(new DeviceTableNameHandler("dev_","inv_"));
        //以拦截器的方式处理表名称
        //可以传递多个拦截器，即：可以传递多个表名处理器TableNameHandler
        mybatisPlusInterceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}