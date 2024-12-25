package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.domain.entity.AbnormalEntity;

/**
 * @ClassName: AbnormalMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-13 13:01
 * @Version: 1.0
 **/
@DS("slave")
public interface AbnormalMapper extends BaseMapper<AbnormalEntity> {
}
