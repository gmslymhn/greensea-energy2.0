package greensea.energy.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import greensea.energy.common.domain.R;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.entity.ProductEntity;
import greensea.energy.framework.domain.entity.ResourceEntity;
import greensea.energy.framework.domain.param.ProductParam;
import greensea.energy.framework.domain.vo.ProductVo;
import greensea.energy.framework.mapper.ProductMapper;
import greensea.energy.framework.mapper.ResourceMapper;
import greensea.energy.framework.service.IProductService;
import greensea.energy.framework.web.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProductServiceimpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 15:18
 * @Version: 1.0
 **/
@Service
public class ProductServiceimpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FileServiceImpl fileService;
    @Override
    public R addProduct(ProductEntity productEntity){
        ResourceEntity resourceEntity = resourceMapper.selectById(productEntity.getProductImageId());
        if (ObjectUtils.isNull(resourceEntity)){
            return R.error("图片不存在！");
        }
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_name",productEntity.getProductName());
        ProductEntity productEntity1 = productMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNotNull(productEntity1)){
            return R.error("设备名已存在！");
        }
        productEntity.setProductId(null);
        productEntity.setState(false);
        productEntity.setDelFlag(0);
        productEntity.setCreateUser(SecurityUtils.getUserAccount());
        productMapper.insert(productEntity);
        return R.success("添加成功！");
    }
    @Override
    public R updateProduct(ProductEntity productEntity){
        ProductEntity productEntity1 = productMapper.selectById(productEntity.getProductId());
        if (ObjectUtils.isNull(productEntity1)){
            return R.error("设备不存在！");
        }
        if (ObjectUtils.isNotNull(productEntity.getProductImageId())){
            ResourceEntity resourceEntity = resourceMapper.selectById(productEntity.getProductImageId());
            if (ObjectUtils.isNull(resourceEntity)){
                return R.error("图片不存在！");
            }
        }
        productEntity.setDelFlag(0);
        productEntity.setUpdateUser(SecurityUtils.getUserAccount());
        productMapper.updateById(productEntity);
        return R.success("修改成功！");
    }

    @Override
    public R getProductList(ProductParam productParam){
        Page<ProductEntity> page = new Page<>(productParam.getPageNum(),productParam.getPageSize());
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNoneBlank(productParam.getProductName()),"product_name",productParam.getProductName())
                .eq(StringUtils.isNoneBlank(productParam.getProductType()),"product_type",productParam.getProductType())
                .eq(ObjectUtils.isNotNull(productParam.getState()),"state",productParam.getState())
                .orderByAsc("sort");
        IPage<ProductEntity> productEntityIPage = productMapper.selectPage(page, queryWrapper);
        List<ProductEntity> productEntityList = productEntityIPage.getRecords();
        for(ProductEntity productEntity:productEntityList){
            productEntity.setProductImageUrl(fileService.getTemporaryUrl(productEntity.getProductImageId()));
            if (ObjectUtils.isNotNull(productEntity.getProductPdfId())){
                productEntity.setProductPdfUrl(fileService.getTemporaryUrl(productEntity.getProductPdfId()));
            }
        }
        productEntityIPage.setRecords(productEntityList);
        return R.success(productEntityIPage);
    }
    @Override
    public R getProducts(PageParam param){
        Page<ProductEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",true)
                .orderByAsc("sort");
        IPage<ProductEntity> productEntityIPage = productMapper.selectPage(page, queryWrapper);
        List<ProductVo> productVos = new ArrayList<>();
        productEntityIPage.getRecords().forEach(productEntity -> {
            ProductVo productVo = new ProductVo();
            productVo.setProductName(productEntity.getProductName());
            productVo.setProductType(productEntity.getProductType());
            productVo.setProductChart(productEntity.getProductChart());
            productVo.setProductIntro(productEntity.getProductIntro());
            productVo.setProductImageUrl(fileService.getTemporaryUrl(productEntity.getProductImageId()));
            if (ObjectUtils.isNotNull(productEntity.getProductPdfId())){
                productVo.setProductPdfUrl(fileService.getTemporaryUrl(productEntity.getProductPdfId()));
            }
            productVos.add(productVo);
        });
        Page<ProductVo> productVoPage = new Page<>(productEntityIPage.getCurrent(),productEntityIPage.getSize(),productEntityIPage.getTotal());
        productVoPage.setRecords(productVos);
        return R.success(productVoPage);

    }
}
