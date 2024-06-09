package greensea.energy.device.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.device.doman.entity.DeviceUploadEntity;

/**
 * @ClassName: DeviceMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 01:47
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceUploadMapper extends BaseMapper<DeviceUploadEntity> {
}
