package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.constant.KeyConstants;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.common.utils.http.AddressUtil;
import greensea.energy.common.utils.http.AreacodeUtil;
import greensea.energy.device.domain.dto.AddDeviceDto;
import greensea.energy.device.domain.dto.UpdateDeviceDto;
import greensea.energy.device.domain.dto.UpdateDeviceDto2;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.domain.model.DeviceMsg;
import greensea.energy.device.domain.param.DeviceParam;
import greensea.energy.device.mapper.*;
import greensea.energy.device.service.IGmDeviceService;
import greensea.energy.framework.domain.entity.GmEntity;
import greensea.energy.framework.domain.entity.UserEntity;
import greensea.energy.framework.mapper.GmMapper;
import greensea.energy.framework.mapper.UserMapper;
import greensea.energy.framework.web.SecurityUtils;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DeviceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-08 05:34
 * @Version: 1.0
 **/
@Service
public class GmDeviceServiceimpl implements IGmDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;
    @Autowired
    private DeviceUpload2Mapper deviceUpload2Mapper;
    @Autowired
    private DeviceUpload3Mapper deviceUpload3Mapper;
    @Autowired
    private DeviceUploadMsgMapper deviceUploadMsgMapper;
    @Autowired
    private GmMapper gmMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public R getDeviceList(DeviceParam deviceParam){
        distributionDevice();
        Page<DeviceEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .eq(StringUtils.isNotBlank(deviceParam.getDeviceType()),"device_type",deviceParam.getDeviceType())
                .eq(ObjectUtils.isNotNull(deviceParam.getDeviceState()),"device_state",deviceParam.getDeviceState());
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);

        //deviceIPage.getRecords().forEach(deviceEntity -> {此处由于甲方不合理要求，时间空间综合之下的无奈搞法，有待优化
        deviceIPage.getRecords().forEach(deviceEntity -> {
            if (ObjectUtils.isNotNull(deviceEntity.getAreacode())){
                QueryWrapper<GmEntity> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("areacode",deviceEntity.getAreacode());
                GmEntity gmEntity = gmMapper.selectOne(queryWrapper1);
                if (ObjectUtils.isNotNull(gmEntity)){
                    deviceEntity.setDeviceGmName(gmEntity.getGmNickname());
                }
            }
            QueryWrapper<UserEntity> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.inSql("user_id","select user_id from gre_user_device_tag where device_id = "+deviceEntity.getDeviceId());
            UserEntity userEntity = userMapper.selectOne(queryWrapper2);
            if (ObjectUtils.isNotNull(userEntity)){
                deviceEntity.setDeviceUserName(userEntity.getUserNickname());
            }
        });
        return R.success(deviceIPage);
    }
    @Override
    public R getDeviceList1(Integer gmId, DeviceParam deviceParam){
        GmEntity gmEntity = gmMapper.selectById(gmId);
        if (ObjectUtils.isNull(gmEntity)||ObjectUtils.isNull(gmEntity.getAreacode())){
            return R.error("管理员不存在或管理员没有管理设备权限！");
        }
        distributionDevice();
        Page<DeviceEntity> page = new Page<>(deviceParam.getPageNum(),deviceParam.getPageSize());
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(deviceParam.getDeviceNumber()),"device_number",deviceParam.getDeviceNumber())
                .eq(StringUtils.isNotBlank(deviceParam.getDeviceType()),"device_type",deviceParam.getDeviceType())
                .eq(ObjectUtils.isNotNull(deviceParam.getDeviceState()),"device_state",deviceParam.getDeviceState())
                .eq("areacode",gmEntity.getAreacode());
//                .inSql("device_id","select device_id from gre_gm_device_tag where gm_id = '"+gmId+"'");
        IPage<DeviceEntity> deviceIPage = deviceMapper.selectPage(page, queryWrapper);
        deviceIPage.getRecords().forEach(deviceEntity -> {
            QueryWrapper<UserEntity> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.inSql("user_id","select user_id from gre_user_device_tag where device_id = "+deviceEntity.getDeviceId());
            UserEntity userEntity = userMapper.selectOne(queryWrapper2);
            if (ObjectUtils.isNotNull(userEntity)){
                deviceEntity.setDeviceUserName(userEntity.getUserNickname());
            }
        });
        return R.success(deviceIPage);
    }
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
        deviceUpload1Mapper.createNewTable1("dev_"+deviceEntity2.getDeviceId());
        deviceUpload2Mapper.createNewTable2("inv_"+deviceEntity2.getDeviceId());
        deviceUpload3Mapper.createNewTable3("add_"+deviceEntity2.getDeviceId());
        deviceUploadMsgMapper.createNewTable4("msg_"+deviceEntity2.getDeviceId());
        Device device = new Device();
        device.setDeviceNumber(addDeviceDto.getDeviceNumber());
        device.setDeviceId(deviceEntity2.getDeviceId());
        redisUtils.setCacheObject(KeyConstants.DEVICE_NUMBER+addDeviceDto.getDeviceNumber(),device);
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

    @Override
    public R updateDevice2(UpdateDeviceDto2 updateDeviceDto2){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(updateDeviceDto2.getDeviceId());
        deviceEntity.setSysStartStop(updateDeviceDto2.getSysStartStop());
        deviceEntity.setFaultReset(updateDeviceDto2.getFaultReset());
        DeviceEntity deviceEntity1 = deviceMapper.selectById(deviceEntity.getDeviceId());
        if (ObjectUtils.isNotNull(deviceEntity1)){
            deviceMapper.updateById(deviceEntity);
            if (ObjectUtils.isNotNull(updateDeviceDto2.getFaultReset())){
                if (updateDeviceDto2.getFaultReset()==1){
                    return R.success("设备启停信号修改为开启！");
                }else {
                    return R.success("设备启停信号修改为关闭！");
                }
            }
            if (ObjectUtils.isNotNull(updateDeviceDto2.getSysStartStop())){
                if (updateDeviceDto2.getSysStartStop()==1){
                    return R.success("故障复位信号修改为开启！");
                }else {
                    return R.success("故障复位信号修改为关闭！");
                }
            }
            return R.error("未修改！");
        }
        return R.error("设备不存在！");
    }
    private DeviceEntity get1(UpdateDeviceDto updateDeviceDto){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(updateDeviceDto.getDeviceId());
        deviceEntity.setDeviceState(updateDeviceDto.getDeviceState());
        deviceEntity.setDeviceIp(updateDeviceDto.getDeviceIp());
        return deviceEntity;
    }
    @Override
    public R getDeviceMsgById(Integer deviceId){
        DeviceEntity deviceEntity = deviceMapper.selectById(deviceId);
        if (ObjectUtils.isNull(deviceEntity)){
            return R.error("设备不存在！");
        }
        UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
        UploadDto2 uploadDto2 = (UploadDto2) redisUtils.getCacheObject("device_address:"+deviceEntity.getDeviceNumber());
        Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
        DeviceMsg deviceMsg = new DeviceMsg();
        deviceMsg.setDevice(device);
        deviceMsg.setUploadDto(uploadDto);
        deviceMsg.setUploadDto2(uploadDto2);

        return R.success(deviceMsg);
    }
    @Override
    public R getDeviceMsgById1(Integer gmId, Integer deviceId){
        GmEntity gmEntity = gmMapper.selectById(gmId);
        DeviceEntity deviceEntity = deviceMapper.selectById(deviceId);
        if (ObjectUtils.isNotNull(deviceEntity)){
            if (deviceEntity.getAreacode().equals(gmEntity.getAreacode())){
                UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
                Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
                UploadDto2 uploadDto2 = (UploadDto2) redisUtils.getCacheObject("device_address:"+deviceEntity.getDeviceNumber());
                DeviceMsg deviceMsg = new DeviceMsg();
                deviceMsg.setDevice(device);
                deviceMsg.setUploadDto(uploadDto);
                deviceMsg.setUploadDto2(uploadDto2);
                return R.success(deviceMsg);
            }
            return R.error("权限不足，无法获取！");
        }
        return R.error("设备不存在！");
    }
    @Override
    public R deleteDevice(Integer deviceId, String deviceNumber){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_id",deviceId)
                .eq("device_number",deviceNumber);
        DeviceEntity deviceEntity = deviceMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(deviceEntity)){
            deviceMapper.deleteById(deviceId);
            return R.success("删除成功！");
        }
        return R.error("设备不存在！");
    }
    private void distributionDevice(){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("areacode")
                .or().eq("areacode","");
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(queryWrapper);
        if (deviceEntityList.isEmpty()){
            return;
        }
        for (DeviceEntity deviceEntity:deviceEntityList){
            Device device = (Device) redisUtils.getCacheObject(KeyConstants.DEVICE_NUMBER+deviceEntity.getDeviceNumber());
            if (ObjectUtils.isNull(device)){
                Device device1 = new Device();
                device1.setDeviceNumber(deviceEntity.getDeviceNumber());
                device1.setDeviceId(deviceEntity.getDeviceId());
                redisUtils.setCacheObject(KeyConstants.DEVICE_NUMBER+deviceEntity.getDeviceNumber(),device1);
                continue;
            }
            String areacode = new String();
            String address = new String();
            if (StringUtils.isNotBlank(device.getDeviceIp())){
                areacode = AreacodeUtil.getAreacodeByIP(device.getDeviceIp());
                address = AddressUtil.getAddressByIP(device.getDeviceIp());
            }else {
                continue;
            }
            DeviceEntity deviceEntity1 = new DeviceEntity();
            deviceEntity1.setDeviceId(deviceEntity.getDeviceId());
            deviceEntity1.setDeviceIp(device.getDeviceIp());
            deviceEntity1.setDeviceAddress(address);
            deviceEntity1.setAreacode(areacode);
            deviceMapper.updateById(deviceEntity1);
        }
        return;
    }
}
