package greensea.energy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.dto.ChartDto;
import greensea.energy.device.domain.dto.PowerCharDto;
import greensea.energy.device.domain.entity.DeviceEntity;
import greensea.energy.device.domain.entity.DevicePowerEntity;
import greensea.energy.device.domain.entity.DeviceUploadEntity;
import greensea.energy.device.domain.vo.PowerChartVo;
import greensea.energy.device.header.DeviceTableNameHandler;
import greensea.energy.device.mapper.DeviceMapper;
import greensea.energy.device.mapper.DevicePowerMapper;
import greensea.energy.device.mapper.DeviceUpload1Mapper;
import greensea.energy.framework.domain.entity.GmEntity;
import greensea.energy.framework.jwt.JwtUtil;
import greensea.energy.framework.mapper.GmMapper;
import greensea.energy.framework.mapper.UserGmMapper;
import greensea.energy.upload.domain.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @ClassName: Tset1
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 17:24
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Tset1 {
    @Autowired
    private GmMapper gmMapper;
    @Autowired
    private UserGmMapper userGmMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceUpload1Mapper deviceUpload1Mapper;
    @Autowired
    private DevicePowerMapper devicePowerMapper;
    @Test
    public void testInsert() {
        GmEntity gmEntity = new GmEntity();
        gmEntity.setGmAccount("321");
        gmMapper.insert(gmEntity);
    }
    @Test
    public void testPage() {
        Page<GmEntity> page = new Page<>(1, 4);
        IPage<GmEntity> userIPage = gmMapper.selectPage(page, new QueryWrapper<GmEntity>());
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数: " + userIPage.getTotal());
        System.out.println("当前页数: " + userIPage.getCurrent());
        System.out.println("当前每页显示数: " + userIPage.getSize());
        System.out.println("记录列表: " + userIPage.getRecords());
    }
    @Test
    public void testJwt(){
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(null);
        for (DeviceEntity deviceEntity:deviceEntityList){
            Device device = new Device();
            device.setDeviceNumber(deviceEntity.getDeviceNumber());
            device.setDeviceId(deviceEntity.getDeviceId());
            redisUtils.setCacheObject("device_number:"+deviceEntity.getDeviceNumber(),device);
        }
//        String token = jwtUtil.generateToken("321");
//        System.out.println(token);
//        System.out.println(jwtUtil.getToken(token));
    }
    @Test
    public void test1() throws Exception {
        PowerCharDto powerCharDto = new PowerCharDto();
        powerCharDto.setAreacode("CN");
        powerCharDto.setPageSize(1000);
        powerCharDto.setPrecision(10);
        powerCharDto.setPageNum(1);
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
        System.out.println(powerChartVos);
//        DeviceTableNameHandler.setData("1");
//        List<DevicePowerEntity> list = devicePowerMapper.selectList(null);
////        // 用完即销毁
//        DeviceTableNameHandler.removeData();
//        System.out.println(list);
//        DeviceTableNameHandler.setData("1");
//        DeviceUploadEntity deviceUploadEntity = new DeviceUploadEntity();
//        deviceUploadEntity.setSysFaultLvl(randomInteger(0,15));
//        deviceUploadEntity.setSysFaultCode(randomInteger(0,10000));
//        deviceUploadEntity.setFcStatus(randomInteger(0,15));
//        deviceUploadEntity.setFcStackPower(randomFloat(-100f,200f));
//        deviceUploadEntity.setFcStackVolt(randomFloat(-100f,1000f));
//        deviceUploadEntity.setFcStackCur(randomFloat(0f,1000f));
//        deviceUploadEntity.setFcStackCur(randomFloat(0f,1000f));
//        deviceUploadEntity.setFcDCDCVoltOut(randomFloat(0f,1000f));
//        deviceUploadEntity.setFcDCDCCurOut(randomFloat(0f,1000f));
//        deviceUploadEntity.setFcSysPower(randomFloat(-100f,200f));
//        deviceUploadEntity.setFckWh(randomFloat(0f,100f));
//        deviceUploadEntity.setPemh2Status(randomInteger(0,15));
//        deviceUploadEntity.setPemh2DCDCVoltOut(randomFloat(0f,1000f));
//        deviceUploadEntity.setPemh2DCDCCurOut(randomFloat(0f,1000f));
//        deviceUploadEntity.setPemh2Pro(randomFloat(0f,10000f));
//        deviceUploadEntity.setLbmsVolt(randomFloat(0f,1000f));
//        deviceUploadEntity.setLbmsCur(randomFloat(0f,1000f));
//        deviceUploadEntity.setLbmsSOC(randomFloat(0f,100f));
//        deviceUploadEntity.setSbmsVolt(randomFloat(0f,1000f));
//        deviceUploadEntity.setSbmsCur(randomFloat(0f,1000f));
//        deviceUploadEntity.setSbmsSOC(randomFloat(0f,100f));
//        deviceUpload1Mapper.insert(deviceUploadEntity);
//        // 用完即销毁
//        DeviceTableNameHandler.removeData();
    }
    public float randomFloat(float min,float max) throws Exception {
//        float min = 1f;
//        float max = 10f;
        float floatBounded = min + new Random().nextFloat() * (max - min);
        return floatBounded;
    }
    public int randomInteger(int min,int max) throws Exception {
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        return intBounded;
    }

    @Test
    public void test11(){
        QueryWrapper<DeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("areacode")
                .or().eq("areacode","");
        List<DeviceEntity> deviceEntityList = deviceMapper.selectList(queryWrapper);
        System.out.println(deviceEntityList);
    }
}
