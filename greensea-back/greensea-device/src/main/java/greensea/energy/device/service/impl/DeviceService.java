package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.device.doman.entity.DeviceEntity;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.DeviceMsgMapper;
import greensea.energy.device.mapper.DeviceUploadMapper;
import greensea.energy.device.service.IDeviceService;
import greensea.energy.framework.domain.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:34
 * @Version: 1.0
 **/
@Service
public class DeviceService implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceUploadMapper deviceUploadMapper;
    @Autowired
    private DeviceMsgMapper deviceMsgMapper;

    public R getDeviceList(PageParam pageParam){
        Page<DeviceEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);
        return R.success(deviceIPage);
    }
}
