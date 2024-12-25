package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.dto.UpdateAddressDto;
import greensea.energy.device.domain.entity.DeviceUpload1Entity;
import greensea.energy.device.domain.entity.DeviceUploadEntity;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.DeviceUpload1Mapper;
import greensea.energy.device.mapper.DeviceUpload2Mapper;
import greensea.energy.device.service.IDeviceService;
import greensea.energy.upload.domain.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

/**
 * @ClassName: DeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-20 19:26
 * @Version: 1.0
 **/
@Service
public class DeviceServiceimpl implements IDeviceService {
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;
    @Autowired
    private DeviceUpload2Mapper deviceUpload2Mapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public R getyChart1(ChartDto chartDto){
        Page<DeviceUploadEntity> page = new Page<>(chartDto.getPageNum(),chartDto.getPageSize());
        QueryWrapper<DeviceUploadEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MOD(id, "+chartDto.getPrecision()+")", 0)
                .orderByDesc("id");
        DeviceTableNameHandler.setData(String.valueOf(chartDto.getDeviceId()));
        IPage<DeviceUploadEntity> deviceUploadEntityIPage = deviceUpload1Mapper.selectPage(page, queryWrapper);
        // 用完即销毁
        DeviceTableNameHandler.removeData();
        deviceUploadEntityIPage.getRecords().forEach(deviceUploadEntity -> {
            deviceUploadEntity.setTimestamp(deviceUploadEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        });
        return R.success(deviceUploadEntityIPage);
    }

    @Override
    public R getyChart2(ChartDto chartDto){
        Page<DeviceUpload1Entity> page = new Page<>(chartDto.getPageNum(),chartDto.getPageSize());
        QueryWrapper<DeviceUpload1Entity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MOD(id, "+chartDto.getPrecision()+")", 0)
                .orderByDesc("id");
        DeviceTableNameHandler.setData(String.valueOf(chartDto.getDeviceId()));
        IPage<DeviceUpload1Entity> deviceUpload1EntityIPage = deviceUpload2Mapper.selectPage(page, queryWrapper);
        // 用完即销毁
        DeviceTableNameHandler.removeData();
        deviceUpload1EntityIPage.getRecords().forEach(deviceUpload1Entity -> {
            deviceUpload1Entity.setTimestamp(deviceUpload1Entity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        });
        return R.success(deviceUpload1EntityIPage);
    }

    @Override
    public R updateDeviceAddress(UpdateAddressDto updateAddressDto){
        Device device  = (Device) redisUtils.getCacheObject("device_number:"+updateAddressDto.getDeviceNumber());
        if (ObjectUtils.isNotNull(device)){
            if (device.getDeviceIp().equals(updateAddressDto.getDeviceIp())){
                device.setAddress(updateAddressDto);
                redisUtils.setCacheObject("device_number:"+updateAddressDto.getDeviceNumber(),device);
                return R.success("ok");
            }
            return R.error("设备ip错误！");
        }
        return R.error("设备不存在！");
    }



}
