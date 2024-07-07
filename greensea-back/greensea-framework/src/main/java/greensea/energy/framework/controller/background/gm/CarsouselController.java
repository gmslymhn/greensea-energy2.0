package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.annotation.LoginLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.dto.GmLoginDto;
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
 * @ClassName: CarsouselController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-07 18:16
 * @Version: 1.0
 **/
@RestController
@Tag(name = "轮播图管理")
@RequestMapping("background/gm")
@Slf4j
public class CarsouselController {
    @Autowired
    private ICarsouselService iCarsouselService;
    @PostMapping("/addCarsousel")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "添加轮播图",description= "超级管理员state字段不会起作用，添加完成之后可以修改state")
    public R addCarsousel(@RequestBody @Validated CarsouselEntity carsouselEntity) {
        return iCarsouselService.addCarsousel(carsouselEntity);
    }
    @PostMapping("/updateCarsousel")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "修改轮播图",description= "超级管理员")
    public R updateCarsousel(@RequestBody @Validated CarsouselEntity carsouselEntity) {
        return iCarsouselService.updateCarsousel(carsouselEntity);
    }
    @PostMapping("/getCarsouselList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取轮播图",description= "超级管理员")
    public R getCarsouselList(@RequestBody @Validated PageParam pageParam) {
        return iCarsouselService.getCarsouselList(pageParam);
    }
}
