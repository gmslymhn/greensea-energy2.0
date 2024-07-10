package greensea.energy.controller;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.entity.CarsouselEntity;
import greensea.energy.framework.service.ICarsouselService;
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
 * @ClassName: ForeignController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 14:13
 * @Version: 1.0
 **/
@RestController
@Tag(name = "轮播图管理")
@RequestMapping("foreign")
@Slf4j
public class ForeignController {
    @Autowired
    private ICarsouselService iCarsouselService;
    @PostMapping("/carsousel")
    @Operation(summary = "前台轮播图获取",description= "获取轮播图")
    public R getyCarsousels() {
        return iCarsouselService.getCarsousels();
    }
}
