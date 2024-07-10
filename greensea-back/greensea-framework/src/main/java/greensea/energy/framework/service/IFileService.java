package greensea.energy.framework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: IFileService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-06 23:33
 * @Version: 1.0
 **/
public interface IFileService {
    String upload(MultipartFile file);

    String getTemporaryUrl(Integer resourceId);
}
