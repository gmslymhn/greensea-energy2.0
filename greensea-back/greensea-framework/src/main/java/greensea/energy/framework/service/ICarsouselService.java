package greensea.energy.framework.service;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.entity.CarsouselEntity;

/**
 * @ClassName: ICarsouselService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-07 17:36
 * @Version: 1.0
 **/
public interface ICarsouselService {
    R addCarsousel(CarsouselEntity carsouselEntity);

    R updateCarsousel(CarsouselEntity carsouselEntity);

    R getCarsouselList(PageParam param);

    R getCarsousels();
}
