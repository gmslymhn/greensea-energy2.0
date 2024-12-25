package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.dto.DevUpdateDeliveryDto;
import greensea.energy.device.domain.entity.DevUpdateEntity;
import greensea.energy.device.domain.param.DevUpdateParam;
import greensea.energy.device.domain.vo.UpdateFileVo;
import greensea.energy.device.mapper.DevUpdateMapper;
import greensea.energy.device.service.IDevUpdateService;
import greensea.energy.framework.domain.entity.ResourceEntity;
import greensea.energy.framework.mapper.ResourceMapper;
import greensea.energy.framework.service.impl.FileServiceImpl;
import greensea.energy.framework.web.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DevUpdateServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-11-05 20:18
 * @Version: 1.0
 **/
@Service
public class DevUpdateServiceimpl implements IDevUpdateService {
    @Autowired
    private DevUpdateMapper devUpdateMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FileServiceImpl fileServicel;

    @Override
    public R getDevUpdateList(DevUpdateParam devUpdateParam){
        Page<DevUpdateEntity> page = new Page<>(devUpdateParam.getPageNum(),devUpdateParam.getPageSize());
        QueryWrapper<DevUpdateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(devUpdateParam.getFileType()),"file_type",devUpdateParam.getFileType())
                .eq(StringUtils.isNotBlank(devUpdateParam.getProjectName()),"project_name",devUpdateParam.getProjectName());
        IPage<DevUpdateEntity> devUpdateEntityIPage = devUpdateMapper.selectPage(page, queryWrapper);
        devUpdateEntityIPage.getRecords().forEach(DevUpdateEntity -> {
            if (ObjectUtils.isNotNull(DevUpdateEntity.getFileUrlId())){
                DevUpdateEntity.setFileUrl(fileServicel.getTemporaryUrl(DevUpdateEntity.getFileUrlId()));
            }
        });
        return R.success(devUpdateEntityIPage);
    }

    @Override
    public R addDevUpdate(DevUpdateEntity devUpdateEntity){
        ResourceEntity resourceEntity = resourceMapper.selectById(devUpdateEntity.getFileUrlId());
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("图片不存在！");
        }
        QueryWrapper<DevUpdateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(devUpdateEntity.getFileType()),"file_type",devUpdateEntity.getFileType())
                .eq(StringUtils.isNotBlank(devUpdateEntity.getProjectName()),"project_name",devUpdateEntity.getProjectName())
                .eq(ObjectUtils.isNotNull(devUpdateEntity.getFileVersion()),"file_version",devUpdateEntity.getFileVersion());
        DevUpdateEntity devUpdateEntity1 = devUpdateMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(devUpdateEntity1)){
            return R.error("该更新信息已存在！");
        }
        devUpdateEntity.setUpdataId(null);
        devUpdateEntity.setCreateUser(SecurityUtils.getUserAccount());
        devUpdateMapper.insert(devUpdateEntity);
        return R.success("添加成功！");
    }

    @Override
    public R updateDevUpdate(DevUpdateEntity devUpdateEntity){
        DevUpdateEntity devUpdateEntity1 = devUpdateMapper.selectById(devUpdateEntity.getUpdataId());
        if (ObjectUtils.isNull(devUpdateEntity1)){
            return R.error("设备不存在！");
        }
        if(ObjectUtils.isNotNull(devUpdateEntity.getFileUrlId())){
            ResourceEntity resourceEntity = resourceMapper.selectById(devUpdateEntity.getFileUrlId());
            if (ObjectUtils.isNull(resourceEntity)){
                return R.error("图片不存在！");
            }
        }
        devUpdateEntity.setFileType(null) ;
        devUpdateEntity.setFileVersion( null);
        devUpdateEntity.setProjectName(null);
        devUpdateEntity.setUpdateUser(SecurityUtils.getUserAccount());
        devUpdateMapper.updateById(devUpdateEntity);
        return R.success("修改成功！");
    }

    @Override
    public R deleteDevUpdateById(Integer devUpdateId){
        DevUpdateEntity devUpdateEntity = devUpdateMapper.selectById(devUpdateId);
        if (ObjectUtils.isNotNull(devUpdateEntity)){
            devUpdateMapper.deleteById(devUpdateId);
            return R.success("删除成功！");
        }
        return R.success("已删除！");
    }

    @Override
    public R updateDelivery(DevUpdateDeliveryDto devUpdateDeliveryDto){
        UpdateFileVo updateFileVo1 = getUpdateFileVo("bin", devUpdateDeliveryDto.getProjectName());
        UpdateFileVo updateFileVo2 = getUpdateFileVo("hex", devUpdateDeliveryDto.getProjectName());
        UpdateFileVo updateFileVo3 = getUpdateFileVo("mot", devUpdateDeliveryDto.getProjectName());
        List<UpdateFileVo> updateFileVoList = new ArrayList<>();
        updateFileVoList.add(updateFileVo1);
        updateFileVoList.add(updateFileVo2);
        updateFileVoList.add(updateFileVo3);
        return R.success(updateFileVoList);
    }

    private UpdateFileVo getUpdateFileVo(String fileType,String projectName){
        Page<DevUpdateEntity> page = new Page<>(1,1);
        QueryWrapper<DevUpdateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",fileType).orderByDesc("file_version");
        IPage<DevUpdateEntity> devUpdateEntityIPage = devUpdateMapper.selectPage(page, queryWrapper);
        DevUpdateEntity devUpdateEntity = devUpdateEntityIPage.getRecords().get(0);
        if (ObjectUtils.isNotNull(devUpdateEntity.getFileUrlId())){
            devUpdateEntity.setFileUrl(fileServicel.getTemporaryUrl(devUpdateEntity.getFileUrlId()));
        }
        UpdateFileVo updateFileVo = new UpdateFileVo(devUpdateEntity);
        return updateFileVo;
    }

    @Override
    public R devUpdateDelivery(DevUpdateDeliveryDto devUpdateDeliveryDto){

        Page<DevUpdateEntity> page = new Page<>(1,1);
        QueryWrapper<DevUpdateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_type",devUpdateDeliveryDto.getFileType())
                .eq("project_name",devUpdateDeliveryDto.getProjectName())
                .orderByDesc("file_version");
        IPage<DevUpdateEntity> devUpdateEntityIPage = devUpdateMapper.selectPage(page, queryWrapper);
        DevUpdateEntity devUpdateEntity = devUpdateEntityIPage.getRecords().get(0);
        if (ObjectUtils.isNotNull(devUpdateEntity.getFileUrlId())){
            devUpdateEntity.setFileUrl(fileServicel.getTemporaryUrl(devUpdateEntity.getFileUrlId()));
        }
        UpdateFileVo updateFileVo = new UpdateFileVo(devUpdateEntity);
        return R.success(updateFileVo);
    }
}
