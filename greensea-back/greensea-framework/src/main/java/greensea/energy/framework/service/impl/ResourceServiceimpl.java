package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.DateUtils;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RandomUtils;
import greensea.energy.framework.domain.entity.ResourceEntity;
import greensea.energy.framework.domain.vo.ResourceVo;
import greensea.energy.framework.mapper.ResourceMapper;
import greensea.energy.framework.service.IFileService;
import greensea.energy.framework.service.IResourceService;
import greensea.energy.framework.web.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @ClassName: ResourceServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-22 03:23
 * @Version: 1.0
 **/
@Service
public class ResourceServiceimpl implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private IFileService iFileService;

    @Override
    public R addResource(MultipartFile file, String fileDescription, Integer type){
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String imagePath = null;
        if(type==1 && (suffixName.equals(".jpg") || suffixName.equals(".png")||suffixName.equals(".jpeg"))){
            imagePath ="Image/";
        }else if(type==2 && suffixName.equals(".mp4")){
            imagePath ="Video/";
        }else if(type == 3 && suffixName.equals(".md")){
            imagePath ="Markdown/";
        }else if(type == 4 && suffixName.equals(".pdf")){
            imagePath ="PDF/";
        }else {
            return R.error("文件格式错误！");
        }
        //重新生成文件名
        //重新生成文件名
        Date date = new Date();
        String fileName1 = DateUtils.format(date) + RandomUtils.createCode(5);
        String fileName2 = fileName1 + suffixName;

        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File("greensea-resources/"+imagePath + fileName2)));
            out.write(file.getBytes());
            out.flush();
            out.close();
            String url = iFileService.upload(file);
            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setResourcePath(imagePath + fileName2);
            resourceEntity.setResourceName(fileName1);
            resourceEntity.setResourceType(type);
            resourceEntity.setCreateUser(SecurityUtils.getUserAccount());
            resourceEntity.setResourceUrl(url);
            resourceEntity.setResourceState(true);
            resourceEntity.setResourceDescription(fileDescription);
            resourceEntity.setCreateUser(SecurityUtils.getUserAccount());
            resourceEntity.setDelFlag(0);
            resourceMapper.insert(resourceEntity);
            QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_path", resourceEntity.getResourcePath());
            ResourceEntity resourceEntity1 =resourceMapper.selectOne(queryWrapper);
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setResourceId(resourceEntity1.getResourceId());
            resourceVo.setResourceName(fileName1);
            resourceVo.setResourceDescription(resourceEntity1.getResourceDescription());
            resourceVo.setResourceType(type);
            resourceVo.setResourceUrl(iFileService.getTemporaryUrl(resourceEntity1.getResourceId()));
            return R.success(resourceVo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("文件添加失败！");
        }
    }

    @Override
    public R getResourceById(int resourceId){
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("资源不存在！");
        }
        ResourceVo resourceVo = new ResourceVo();
        resourceVo.setResourceId(resourceEntity.getResourceId());
        resourceVo.setResourceName(resourceEntity.getResourceName());
        resourceVo.setResourceDescription(resourceEntity.getResourceDescription());
        resourceVo.setResourceType(resourceEntity.getResourceType());
        resourceVo.setResourceUrl(iFileService.getTemporaryUrl(resourceId));
        return R.success(resourceVo);
    }

}
