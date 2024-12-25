package greensea.energy.upload.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import greensea.energy.upload.domain.entity.DeviceEntity;

/**
 * @ClassName: DeviceMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-04 15:37
 * @Version: 1.0
 **/
@DS("slave")
public interface DeviceMapper extends BaseMapper<DeviceEntity> {
}
