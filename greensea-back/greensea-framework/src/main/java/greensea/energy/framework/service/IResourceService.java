package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: IResourceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-22 03:23
 * @Version: 1.0
 **/
public interface IResourceService {


    R addResource(MultipartFile file, String fileDescription, Integer type);

    R getResourceById(int resourceId);
}
