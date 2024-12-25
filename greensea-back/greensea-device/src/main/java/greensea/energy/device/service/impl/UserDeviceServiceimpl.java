package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.domain.model.DeviceMsg;
import greensea.energy.device.domain.entity.UserDeviceTagEntity;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.UserDeviceTagMapper;
import greensea.energy.device.service.IUserDeviceService;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName: UserDeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 16:27
 * @Version: 1.0
 **/
@Service
public class UserDeviceServiceimpl implements IUserDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UserDeviceTagMapper userDeviceTagMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public R getDeviceList2(Integer userId, DeviceParam deviceParam){
        Page<DeviceEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .eq(StringUtils.isNotBlank(deviceParam.getDeviceType()),"device_type",deviceParam.getDeviceType())
                .eq(ObjectUtils.isNotNull(deviceParam.getDeviceState()),"device_state",deviceParam.getDeviceState())
                .inSql("device_id","select device_id from gre_user_device_tag where user_id = '"+userId+"'");
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);
        return R.success(deviceIPage);
    }
    @Override
    public R getDeviceMsgById2(Integer userId, Integer deviceId){
        QueryWrapper<UserDeviceTagEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("device_id",deviceId);
        UserDeviceTagEntity userDeviceTagEntity = userDeviceTagMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(userDeviceTagEntity)){
            DeviceEntity deviceEntity = deviceMapper.selectById(deviceId);
            if (ObjectUtils.isNull(deviceEntity)){
                return R.error("设备不存在！");
            }
            UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
            Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
            DeviceMsg deviceMsg = new DeviceMsg();
            deviceMsg.setDevice(device);
            deviceMsg.setUploadDto(uploadDto);

            return R.success(deviceMsg);
        }
        return R.error("权限不足，无法获取！");
    }
    @Override
    public R bindDevice(Integer userId,String deviceNumber){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_number",deviceNumber);
        DeviceEntity deviceEntity = deviceMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(deviceEntity)){
            QueryWrapper<UserDeviceTagEntity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("device_id",deviceEntity.getDeviceId());
            UserDeviceTagEntity userDeviceTagEntity = userDeviceTagMapper.selectOne(queryWrapper1);
            if (ObjectUtils.isNotNull(userDeviceTagEntity)){
                return R.error("设备已被绑定！");
            }
            UserDeviceTagEntity userDeviceTagEntity1 = new UserDeviceTagEntity();
            userDeviceTagEntity1.setUserId(userId);
            userDeviceTagEntity1.setDeviceId(deviceEntity.getDeviceId());
            userDeviceTagMapper.insert(userDeviceTagEntity1);
            DeviceEntity deviceEntity1 = new DeviceEntity();
            deviceEntity1.setDeviceId(deviceEntity.getDeviceId());
            LocalDateTime currentDateTime = LocalDateTime.now();
            deviceEntity1.setBindTime(currentDateTime);
            deviceEntity1.setWarrantyTime(currentDateTime.plusYears(1));
            deviceMapper.updateById(deviceEntity1);
            return R.success("绑定成功！");
        }
        return R.error("设备不存在！");
    }
    @Override
    public R untieDevice(Integer userId, String deviceNumber){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_number",deviceNumber);
        DeviceEntity deviceEntity = deviceMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(deviceEntity)){
            QueryWrapper<UserDeviceTagEntity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("device_id",deviceEntity.getDeviceId())
                    .eq("user_id",userId);
            UserDeviceTagEntity userDeviceTagEntity = userDeviceTagMapper.selectOne(queryWrapper1);
            if (ObjectUtils.isNull(userDeviceTagEntity)){
                return R.error("设备未绑定！");
            }
            userDeviceTagMapper.deleteById(deviceEntity.getDeviceId());
            return R.success("解绑成功！");
        }
        return R.error("设备不存在！");
    }
}
