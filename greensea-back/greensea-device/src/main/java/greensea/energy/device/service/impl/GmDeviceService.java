package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.dto.AddDeviceDto;
import greensea.energy.device.domain.dto.UpdateDeviceDto;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.domain.entity.DeviceMsgEntity;
import greensea.energy.device.domain.entity.GmDeviceTagEntity;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.mapper.*;
import greensea.energy.device.service.IGmDeviceService;
import greensea.energy.framework.web.SecurityUtils;
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
public class GmDeviceService implements IGmDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;
    @Autowired
    private DeviceUpload2Mapper deviceUpload2Mapper;
    @Autowired
    private DeviceMsgMapper deviceMsgMapper;
    @Autowired
    private GmDeviceTagMapper gmDeviceTagMapper;

    @Override
    public R getDeviceList(DeviceParam deviceParam){
        Page<DeviceEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .eq(StringUtils.isNotBlank(deviceParam.getDeviceType()),"device_type",deviceParam.getDeviceType())
                .eq(ObjectUtils.isNotNull(deviceParam.getDeviceState()),"device_state",deviceParam.getDeviceState());
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);
        return R.success(deviceIPage);
    }
    @Override
    public R getDeviceList1(Integer gmId, DeviceParam deviceParam){
        Page<DeviceEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .eq(StringUtils.isNotBlank(deviceParam.getDeviceType()),"device_type",deviceParam.getDeviceType())
                .eq(ObjectUtils.isNotNull(deviceParam.getDeviceState()),"device_state",deviceParam.getDeviceState())
                .inSql("device_id","select device_id from gre_gm_device_tag where gm_id = '"+gmId+"'");
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);
        return R.success(deviceIPage);
    }
    @Override
    public R addDevice(AddDeviceDto addDeviceDto){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_number",addDeviceDto.getDeviceNumber());
        DeviceEntity deviceEntity = deviceMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(deviceEntity)){
            return R.error("设备序列号已存在！");
        }
        DeviceEntity deviceEntity1 =new DeviceEntity();
        deviceEntity1.setDeviceNumber(addDeviceDto.getDeviceNumber());
        deviceEntity1.setDeviceName(addDeviceDto.getDeviceName());
        deviceEntity1.setDeviceType(addDeviceDto.getDeviceType());
        deviceEntity1.setCreateUser(SecurityUtils.getUserAccount());
        deviceEntity1.setDeviceState(0);
        deviceEntity1.setDelFlag(0);
        deviceMapper.insert(deviceEntity1);
        DeviceEntity deviceEntity2 = deviceMapper.selectOne(queryWrapper);
        DeviceMsgEntity deviceMsgEntity = new DeviceMsgEntity();
        deviceMsgEntity.setDeviceId(deviceEntity2.getDeviceId());
        deviceMsgEntity.setDeviceNumber(addDeviceDto.getDeviceNumber());
        deviceMsgEntity.setDelFlag(0);
        deviceMsgMapper.insert(deviceMsgEntity);
        deviceUpload1Mapper.createNewTable1("dev_"+deviceEntity2.getDeviceId());
        deviceUpload2Mapper.createNewTable2("inv_"+deviceEntity2.getDeviceId());
        return R.success("添加成功！");
    }

    @Override
    public R updateDevice(UpdateDeviceDto updateDeviceDto){
        DeviceEntity deviceEntity = get1(updateDeviceDto);
        DeviceEntity deviceEntity1 = deviceMapper.selectById(deviceEntity.getDeviceId());
        if (ObjectUtils.isNotNull(deviceEntity1)){
            deviceMapper.updateById(deviceEntity);
            return R.success("修改成功！");
        }
        return R.error("设备不存在！");
    }
    private DeviceEntity get1(UpdateDeviceDto updateDeviceDto){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(updateDeviceDto.getDeviceId());
        deviceEntity.setDeviceState(updateDeviceDto.getDeviceState());
        deviceEntity.setDeviceIp(updateDeviceDto.getDeviceIp());
        deviceEntity.setDeviceMac(updateDeviceDto.getDeviceMac());
        return deviceEntity;
    }
    @Override
    public R getDeviceMsgById(Integer deviceId){
        DeviceMsgEntity deviceMsgEntity = deviceMsgMapper.selectById(deviceId);
        if (ObjectUtils.isNotNull(deviceMsgEntity)){
            return R.success(deviceMsgEntity);
        }
        return R.error("设备不存在！");
    }
    @Override
    public R getDeviceMsgById1(Integer gmId, Integer deviceId){
        QueryWrapper<GmDeviceTagEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gm_id",gmId).eq("device_id",deviceId);
        GmDeviceTagEntity gmDeviceTagEntity = gmDeviceTagMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(gmDeviceTagEntity)){
            DeviceMsgEntity deviceMsgEntity = deviceMsgMapper.selectById(deviceId);
            if (ObjectUtils.isNotNull(deviceMsgEntity)){
                return R.success(deviceMsgEntity);
            }
            return R.error("设备不存在！");
        }
        return R.error("权限不足，无法获取！");
    }
    @Override
    public R deleteDevice(Integer deviceId, String deviceNumber){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id",deviceId)
                .eq("device_number",deviceNumber);
        DeviceEntity deviceEntity = deviceMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(deviceEntity)){
            deviceMapper.deleteById(deviceId);
            deviceMsgMapper.deleteById(deviceId);
            return R.success("删除成功！");
        }
        return R.error("设备不存在！");
    }
}
