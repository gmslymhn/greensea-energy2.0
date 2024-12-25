package greensea.energy.upload.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: AbnormalLogAnnotation
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-13 12:56
 * @Version: 1.0
 **/
@Target(ElementType.METHOD)//注解放置的目标位置即方法级别
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented
public @interface AbnormalLogAnnotation {
}
