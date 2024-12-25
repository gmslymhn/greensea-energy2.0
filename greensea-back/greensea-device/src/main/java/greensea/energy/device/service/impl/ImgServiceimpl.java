package greensea.energy.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.device.domain.dto.GetImgDto;
import greensea.energy.device.domain.entity.ImgEntity;
import greensea.energy.device.mapper.ImgMapper;
import greensea.energy.device.service.IImgService;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.service.impl.FileServiceImpl;
import greensea.energy.framework.web.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ImgServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 13:43
 * @Version: 1.0
 **/
@Service
public class ImgServiceimpl implements IImgService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private FileServiceImpl fileServicel;


    @Override
    public R getImg(GetImgDto getImgDto){
        Integer privacyPolicy = (Integer) redisUtils.getCacheObject(getImgDto.getImgKey()+getImgDto.getImgType());
        if (ObjectUtils.isNotNull(privacyPolicy)){
            String url = fileServicel.getTemporaryUrl(privacyPolicy);
            return R.success(url);
        }
        QueryWrapper<ImgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("img_key",getImgDto.getImgKey())
                .eq("img_type",getImgDto.getImgType());
        ImgEntity imgEntity = imgMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(imgEntity.getResourceId())){
            redisUtils.setCacheObject(getImgDto.getImgKey()+getImgDto.getImgType(),imgEntity.getResourceId());
            return R.success(fileServicel.getTemporaryUrl(imgEntity.getResourceId()));
        }
        return R.error("文字异常！");
    }

    @Override
    public R getImgList(PageParam param){
        Page<ImgEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        IPage<ImgEntity> imgEntityIPage = imgMapper.selectPage(page, null);
        imgEntityIPage.getRecords().forEach(imgEntity -> {
            if (ObjectUtils.isNotNull(imgEntity.getResourceId())){
                imgEntity.setResourceUrl(fileServicel.getTemporaryUrl(imgEntity.getResourceId()));
            }
        });
        return R.success(imgEntityIPage);
    }

    @Override
    public R updateImg(ImgEntity imgEntity){
        ImgEntity imgEntity1 = imgMapper.selectById(imgEntity.getImgId());
        if (ObjectUtils.isNull(imgEntity1)){
            return R.error("系统异常！");
        }
        imgEntity.setImgKey(null);
        imgEntity.setUpdateTime(null);
        imgEntity.setUpdateGm(SecurityUtils.getUserAccount());
        imgMapper.updateById(imgEntity);
        imgEntity.setResourceUrl(fileServicel.getTemporaryUrl(imgEntity1.getResourceId()));
        redisUtils.setCacheObject(imgEntity1.getImgKey()+imgEntity.getImgType(),imgEntity.getResourceId());
        return R.success("修改成功！");
    }

}
