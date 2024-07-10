package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.entity.CarsouselEntity;
import greensea.energy.framework.domain.entity.ProductEntity;
import greensea.energy.framework.domain.param.ProductParam;
import greensea.energy.framework.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ProductController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 16:56
 * @Version: 1.0
 **/
@RestController
@Tag(name = "产品管理")
@RequestMapping("background/gm")
@Slf4j
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @PostMapping("/addProduct")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "添加产品",description= "超级管理员state字段不会起作用，添加完成之后可以修改state")
    public R addProduct(@RequestBody @Validated ProductEntity productEntityy) {
        return iProductService.addProduct(productEntityy);
    }
    @PostMapping("/updateProduct")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "修改产品",description= "超级管理员state字段不会起作用，添加完成之后可以修改state")
    public R updateProduct(@RequestBody @Validated ProductEntity productEntity) {
        return iProductService.updateProduct(productEntity);
    }
    @PostMapping("/getProductList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取产品列表",description= "超级管理员state字段不会起作用，添加完成之后可以修改state")
    public R getProductList(@RequestBody @Validated ProductParam productParam) {
        return iProductService.getProductList(productParam);
    }
}
