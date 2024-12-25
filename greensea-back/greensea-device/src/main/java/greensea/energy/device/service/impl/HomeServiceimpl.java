package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.dto.PowerCharDto;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.domain.entity.DevicePowerEntity;
import greensea.energy.device.domain.model.DeviceMsg;
import greensea.energy.device.domain.vo.PowerChartVo;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.DevicePowerMapper;
import greensea.energy.device.service.IHomeService;
import greensea.energy.framework.domain.entity.GmEntity;
import greensea.energy.framework.mapper.GmMapper;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HomeServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-02 12:42
 * @Version: 1.0
 **/
@Service
public class HomeServiceimpl implements IHomeService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private GmMapper gmMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DevicePowerMapper devicePowerMapper;

    @Override
    public R GmgetDeviceMsgs1(String areacode){
        List<DeviceEntity> deviceEntityList = new ArrayList<>();
        if (ObjectUtils.isNotNull(areacode)){
            QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("areacode",areacode);
            deviceEntityList = deviceMapper.selectList(queryWrapper);
        }
        List<DeviceMsg> deviceMsgList = new ArrayList<>();
        for (DeviceEntity deviceEntity:deviceEntityList){
            UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
            Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
            DeviceMsg deviceMsg = new DeviceMsg();
            deviceMsg.setDevice(device);
            deviceMsg.setUploadDto(uploadDto);
            deviceMsgList.add(deviceMsg);
        }
        return R.success(deviceMsgList);
    }
    @Override
    public R GmgetDeviceMsgs2(Integer gmId){
        GmEntity gmEntity = gmMapper.selectById(gmId);
        List<DeviceEntity> deviceEntityList = new ArrayList<>();
        if (ObjectUtils.isNotNull(gmEntity.getAreacode())){
            QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("areacode",gmEntity.getAreacode());
            deviceEntityList = deviceMapper.selectList(queryWrapper);
        }
        List<DeviceMsg> deviceMsgList = new ArrayList<>();
        for (DeviceEntity deviceEntity:deviceEntityList){
                    UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
                    Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
                    DeviceMsg deviceMsg = new DeviceMsg();
                    deviceMsg.setDevice(device);
                    deviceMsg.setUploadDto(uploadDto);
                    deviceMsgList.add(deviceMsg);
        }
        return R.success(deviceMsgList);
    }
    @Override
    public R UsergetDeviceMsgs(Integer userId){
            QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.inSql("device_id","select device_id from gre_user_device_tag where user_id = '"+userId+"'");
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(queryWrapper);
        List<DeviceMsg> deviceMsgList = new ArrayList<>();
        for (DeviceEntity deviceEntity:deviceEntityList){
            UploadDto uploadDto = (UploadDto) redisUtils.getCacheObject("device_message:"+deviceEntity.getDeviceNumber());
            Device device  = (Device) redisUtils.getCacheObject("device_number:"+deviceEntity.getDeviceNumber());
            DeviceMsg deviceMsg = new DeviceMsg();
            deviceMsg.setDevice(device);
            deviceMsg.setUploadDto(uploadDto);
            deviceMsgList.add(deviceMsg);
        }
        return R.success(deviceMsgList);
    }

    @Override
    public R userGetPowerChar(Integer userId, PowerCharDto powerCharDto){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("device_id","select device_id from gre_user_device_tag where user_id = '"+userId+"'");
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(queryWrapper);
        List<PowerChartVo> powerChartVos = new ArrayList<PowerChartVo>(powerCharDto.getPageSize());
        for (DeviceEntity deviceEntity:deviceEntityList){
            Page<DevicePowerEntity> page = new Page<>(powerCharDto.getPageNum(),powerCharDto.getPageSize());
            QueryWrapper<DevicePowerEntity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("MOD(id, "+powerCharDto.getPrecision()+")", 0)
                    .orderByDesc("id");
            DeviceTableNameHandler.setData(String.valueOf(deviceEntity.getDeviceId()));
            IPage<DevicePowerEntity> devicePowerEntityIPage = devicePowerMapper.selectPage(page, queryWrapper1);
            // 用完即销毁
            DeviceTableNameHandler.removeData();
            List<DevicePowerEntity> devicePowerEntityList = devicePowerEntityIPage.getRecords();
            int i = 0;
            for (DevicePowerEntity devicePowerEntity:devicePowerEntityList){
                if (i >= powerChartVos.size()){
                    PowerChartVo powerChartVo = new PowerChartVo();
                    powerChartVo.setPower(devicePowerEntity.getLbmsCur()*devicePowerEntity.getLbmsVolt());
                    powerChartVo.setTimestamp(devicePowerEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    powerChartVos.add(powerChartVo);
                }else {
                    PowerChartVo powerChartVo = new PowerChartVo();
                    powerChartVo.setPower(devicePowerEntity.getLbmsCur()*devicePowerEntity.getLbmsVolt()+powerChartVos.get(i).getPower());
                    powerChartVo.setTimestamp(devicePowerEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    powerChartVos.set(i,powerChartVo);
                }
                i++;
            }
        }
        return R.success(powerChartVos);

    }
    @Override
    public R gmGetPowerChar(Integer gmId, PowerCharDto powerCharDto){
        GmEntity gmEntity = gmMapper.selectById(gmId);
        powerCharDto.setAreacode(gmEntity.getAreacode());
        return getPowerChar(powerCharDto);
    }

    @Override
    public R getPowerChar(PowerCharDto powerCharDto){

        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("areacode",powerCharDto.getAreacode());
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(queryWrapper);
        List<PowerChartVo> powerChartVos = new ArrayList<PowerChartVo>(powerCharDto.getPageSize());
        for (DeviceEntity deviceEntity:deviceEntityList){
            Page<DevicePowerEntity> page = new Page<>(powerCharDto.getPageNum(),powerCharDto.getPageSize());
            QueryWrapper<DevicePowerEntity> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("MOD(id, "+powerCharDto.getPrecision()+")", 0)
                    .orderByDesc("id");
            DeviceTableNameHandler.setData(String.valueOf(deviceEntity.getDeviceId()));
            IPage<DevicePowerEntity> devicePowerEntityIPage = devicePowerMapper.selectPage(page, queryWrapper1);
            // 用完即销毁
            DeviceTableNameHandler.removeData();
            List<DevicePowerEntity> devicePowerEntityList = devicePowerEntityIPage.getRecords();
            int i = 0;
            for (DevicePowerEntity devicePowerEntity:devicePowerEntityList){
                if (i >= powerChartVos.size()){
                    PowerChartVo powerChartVo = new PowerChartVo();
                    powerChartVo.setPower(devicePowerEntity.getLbmsCur()*devicePowerEntity.getLbmsVolt());
                    powerChartVo.setTimestamp(devicePowerEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    powerChartVos.add(powerChartVo);
                }else {
                    PowerChartVo powerChartVo = new PowerChartVo();
                    powerChartVo.setPower(devicePowerEntity.getLbmsCur()*devicePowerEntity.getLbmsVolt()+powerChartVos.get(i).getPower());
                    powerChartVo.setTimestamp(devicePowerEntity.getUploadTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    powerChartVos.set(i,powerChartVo);
                }
                i++;
            }
        }

        return R.success(powerChartVos);

    }
}
