package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.entity.DeviceUploadMsgEntity;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceUploadMsgMapper;
import greensea.energy.device.service.IDeviceUploadMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DeviceUploadMsgServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 16:03
 * @Version: 1.0
 **/
@Service
public class DeviceUploadMsgServiceimpl implements IDeviceUploadMsgService {
    @Autowired
    private DeviceUploadMsgMapper deviceUploadMsgMapper;

    @Override
    public R getUploadMsgList(ChartDto chartDto){
        Page<DeviceUploadMsgEntity > page = new Page<>(chartDto.getPageNum(),chartDto.getPageSize());
        QueryWrapper<DeviceUploadMsgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        DeviceTableNameHandler.setData(String.valueOf(chartDto.getDeviceId()));
        IPage<DeviceUploadMsgEntity> deviceUploadMsgEntityIPage = deviceUploadMsgMapper.selectPage(page, queryWrapper);
        // 用完即销毁
        DeviceTableNameHandler.removeData();
        return R.success(deviceUploadMsgEntityIPage);

    }
}
