package greensea.energy.upload.service;

import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.dto.UploadMsgDto;
import greensea.energy.upload.domain.entity.UploadEntity;
import greensea.energy.upload.domain.entity.UploadEntity2;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: IUploadService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 15:44
 * @Version: 1.0
 **/
public interface IUploadService {

    @Async
    CompletableFuture<String> upload(UploadDto uploadDto);

    @Async
    CompletableFuture<String> upload2(UploadDto2 uploadDto2);

    @Async
    CompletableFuture<String> upload3(UploadMsgDto uploadMsgDto);

//    @Async
//    CompletableFuture<String> uploadAsync(String tableName, UploadEntity uploadEntity);
//
//    @Async
//    CompletableFuture<String> uploadAsync2(String tableName, UploadEntity2 uploadEntity2);
}
