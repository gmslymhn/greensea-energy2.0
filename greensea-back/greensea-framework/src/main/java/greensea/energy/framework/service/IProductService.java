package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.entity.ProductEntity;
import greensea.energy.framework.domain.param.ProductParam;

/**
 * @ClassName: IProductService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 15:18
 * @Version: 1.0
 **/
public interface IProductService {
    R addProduct(ProductEntity productEntity);

    R updateProduct(ProductEntity productEntity);

    R getProductList(ProductParam productParam);

    R getProducts(ProductParam productParam);
}
