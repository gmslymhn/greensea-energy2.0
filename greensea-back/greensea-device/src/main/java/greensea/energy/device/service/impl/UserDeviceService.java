package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.doman.entity.DeviceEntity;
import greensea.energy.device.doman.entity.DeviceMsgEntity;
import greensea.energy.device.doman.entity.UserDeviceTagEntity;
import greensea.energy.device.doman.param.DeviceParam;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.DeviceMsgMapper;
import greensea.energy.device.mapper.UserDeviceTagMapper;
import greensea.energy.device.service.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserDeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-10 16:27
 * @Version: 1.0
 **/
@Service
public class UserDeviceService implements IUserDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceMsgMapper deviceMsgMapper;
    @Autowired
    private UserDeviceTagMapper userDeviceTagMapper;
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
            DeviceMsgEntity deviceMsgEntity = deviceMsgMapper.selectById(deviceId);
            if (ObjectUtils.isNotNull(deviceMsgEntity)){
                return R.success(deviceMsgEntity);
            }
            return R.error("设备不存在！");
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
