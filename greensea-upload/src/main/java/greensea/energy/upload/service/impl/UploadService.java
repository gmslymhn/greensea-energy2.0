package greensea.energy.upload.service.impl;

import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.dto.UploadMsgDto;
import greensea.energy.upload.domain.entity.MessageEntity;
import greensea.energy.upload.domain.entity.UploadEntity;
import greensea.energy.upload.domain.entity.UploadEntity3;
import greensea.energy.upload.domain.model.*;
import greensea.energy.upload.mapper.UploadMapper;
import greensea.energy.upload.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: UploadService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 15:44
 * @Version: 1.0
 **/
@Service
public class UploadService implements IUploadService {
    @Autowired
    private UploadMapper uploadMapper;
    @Autowired
    private TokenService tokenService;
    @Async
    @Override
    public CompletableFuture<String> upload(UploadDto uploadDto){
        Device device = tokenService.getDevice(uploadDto.getDeviceNumber());
        String tableName1 = "dev_"+device.getDeviceId();
        String tableName2 = "inv_"+device.getDeviceId();
        FaultInfo faultInfo = uploadDto.getFaultInfo();
        FCInfo fcInfo = uploadDto.getFcInfo();
        PEMH2Info pemh2Info = uploadDto.getPemh2Info();
        LBMSInfo lbmsInfo = uploadDto.getLbmsInfo();
        SBMSInfo sbmsInfo = uploadDto.getSbmsInfo();
        UploadEntity uploadEntity = new UploadEntity(faultInfo.getSysFaultLvl(),faultInfo.getSysFaultCode(),fcInfo.getFcStatus(),fcInfo.getFcStackPower(),
                fcInfo.getFcStackVolt(),fcInfo.getFcStackCur(),fcInfo.getFcDCDCVoltOut(),fcInfo.getFcDCDCCurOut(),fcInfo.getFcSysPower(),fcInfo.getFckWh(),
                pemh2Info.getPemh2Status(),pemh2Info.getPemh2DCDCVoltOut(),pemh2Info.getPemh2DCDCCurOut(),pemh2Info.getPemh2Pro(),
                lbmsInfo.getLbmsVolt(),lbmsInfo.getLbmsCur(),lbmsInfo.getLbmsSOC(),
                sbmsInfo.getSbmsVolt(),sbmsInfo.getSbmsCur(),sbmsInfo.getSbmsSOC());
        Integer uploadState1 = uploadMapper.upload1(tableName1, uploadEntity);
        if (null==uploadState1||uploadState1==0){
            return CompletableFuture.completedFuture("上传"+tableName1+"出现问题！");
        }
        Integer uploadState2 = uploadMapper.upload2(tableName2, uploadDto.getInvInfo());
        if (null==uploadState2||uploadState2==0){
            return CompletableFuture.completedFuture("上传"+tableName2+"出现问题！");
        }
        return CompletableFuture.completedFuture("上传成功！");
    }
    @Async
    @Override
    public CompletableFuture<String> upload2(UploadDto2 uploadDto2){
        Device device = tokenService.getDevice(uploadDto2.getDeviceNumber());
        UploadEntity3 uploadEntity3 = new UploadEntity3(uploadDto2);
        String tableName = "add_"+device.getDeviceId();
        Integer uploadState = uploadMapper.upload3(tableName, uploadEntity3);
        if (null==uploadState||uploadState==0){
            return CompletableFuture.completedFuture("上传"+tableName+"出现问题！");
        }
        return CompletableFuture.completedFuture("上传成功！");
    }

    @Async
    @Override
    public CompletableFuture<String> upload3(UploadMsgDto uploadMsgDto){
        Device device = tokenService.getDevice(uploadMsgDto.getDeviceNumber());
        MessageEntity messageEntity = new MessageEntity(uploadMsgDto);
        String tableName = "msg_"+device.getDeviceId();
        Integer uploadState = uploadMapper.upload4(tableName, messageEntity);
        if (null==uploadState||uploadState==0){
            return CompletableFuture.completedFuture("上传"+tableName+"出现问题！");
        }
        return CompletableFuture.completedFuture("上传成功！");
    }
//
//    @Async
//    @Override
//    public CompletableFuture<String> uploadAsync(String tableName, UploadEntity uploadEntity){
//        Integer uploadState = uploadMapper.uplpad1(tableName, uploadEntity);
//        if (null==uploadState||uploadState==0){
//            return new CompletableFuture<>();
//        }
//        return new CompletableFuture<>();
//    }
//    @Async
//    @Override
//    public CompletableFuture<String> uploadAsync2(String tableName, UploadEntity2 uploadEntity2){
//        Integer uploadState = uploadMapper.uplpad2(tableName, uploadEntity2);
//        if (null==uploadState||uploadState==0){
//            return new CompletableFuture<>();
//        }
//        return new CompletableFuture<>();
//    }


}
