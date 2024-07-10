package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.entity.CarsouselEntity;
import greensea.energy.framework.domain.entity.ResourceEntity;
import greensea.energy.framework.mapper.CarsouselMapper;
import greensea.energy.framework.mapper.ResourceMapper;
import greensea.energy.framework.service.ICarsouselService;
import greensea.energy.framework.web.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CarsouselServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-07 17:36
 * @Version: 1.0
 **/
@Service
public class CarsouselServiceimpl  implements ICarsouselService {
    @Autowired
    private CarsouselMapper carsouselMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FileServiceImpl fileServicel;
    @Override
    public R addCarsousel(CarsouselEntity carsouselEntity){
        ResourceEntity resourceEntity = resourceMapper.selectById(carsouselEntity.getCarouselImageId());
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("图片不存在！");
        }
        carsouselEntity.setCarouselId(null);
        carsouselEntity.setState(false);
        carsouselEntity.setDelFlag(0);
        carsouselEntity.setCreateUser(SecurityUtils.getUserAccount());
        carsouselMapper.insert(carsouselEntity);
        return R.success("添加成功！");
    }

    @Override
    public R updateCarsousel(CarsouselEntity carsouselEntity){
        CarsouselEntity carsouselEntity1 = carsouselMapper.selectById(carsouselEntity.getCarouselId());
        if (ObjectUtils.isNull(carsouselEntity1)){
            return R.error("轮播图不存在！");
        }
        if (ObjectUtils.isNotNull(carsouselEntity.getCarouselImageId())){
            ResourceEntity resourceEntity = resourceMapper.selectById(carsouselEntity.getCarouselImageId());
            if (ObjectUtils.isNull(resourceEntity)){
                return R.error("图片不存在！");
            }
        }
        carsouselEntity.setDelFlag(0);
        carsouselEntity.setUpdateUser(SecurityUtils.getUserAccount());
        carsouselMapper.updateById(carsouselEntity);
        return R.success("修改成功！");
    }
    @Override
    public R getCarsouselList(PageParam param){
        Page<CarsouselEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        QueryWrapper<CarsouselEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        IPage<CarsouselEntity> carsouselIPage = carsouselMapper.selectPage(page, queryWrapper);
        List<CarsouselEntity> carsouselEntityList = carsouselIPage.getRecords();
        for (CarsouselEntity carsouselEntity:carsouselEntityList){
            carsouselEntity.setCarouselImageUrl(fileServicel.getTemporaryUrl(carsouselEntity.getCarouselImageId()));
        }
        carsouselIPage.setRecords(carsouselEntityList);
        return R.success(carsouselIPage);
    }

    @Override
    public R getCarsousels(){
        QueryWrapper<CarsouselEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort")
                .eq("state",true);
        List<CarsouselEntity> carsouselEntityList = carsouselMapper.selectList(queryWrapper);
        String[] carsousels = new String[carsouselEntityList.size()];
        //使用for循环得到数组
        int i = 0;
        for (CarsouselEntity carsouselEntity:carsouselEntityList){
            String url = fileServicel.getTemporaryUrl(carsouselEntity.getCarouselImageId());
            carsousels[i] = url;
            i++;
        }
        return R.success(carsousels);
    }
}
