package greensea.energy.upload.service.ipml;

import greensea.energy.upload.domain.entity.UploadEntity;
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

    @Async
    @Override
    public CompletableFuture<String> uploadAsync(String tableName, UploadEntity uploadEntity){
        Integer uploadState = uploadMapper.uplpad1(tableName, uploadEntity);
        if (null==uploadState||uploadState==0){
            return new CompletableFuture<>();
        }
        return new CompletableFuture<>();
    }


}
