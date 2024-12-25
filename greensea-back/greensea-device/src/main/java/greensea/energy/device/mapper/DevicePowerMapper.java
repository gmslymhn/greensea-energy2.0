package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.domain.entity.DevicePowerEntity;

/**
 * @ClassName: DevicePowerMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 20:54
 * @Version: 1.0
 **/
@DS("slave")
public interface DevicePowerMapper extends BaseMapper<DevicePowerEntity> {
}
