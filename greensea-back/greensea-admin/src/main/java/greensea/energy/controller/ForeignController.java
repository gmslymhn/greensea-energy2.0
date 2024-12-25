package greensea.energy.controller;

import greensea.energy.common.domain.R;
import greensea.energy.common.utils.StringUtils;
import greensea.energy.device.domain.dto.DevUpdateDeliveryDto;
import greensea.energy.device.domain.dto.GetImgDto;
import greensea.energy.device.domain.dto.GetTextDto;
import greensea.energy.device.service.IDevUpdateService;
import greensea.energy.device.service.IImgService;
import greensea.energy.device.service.ITextService;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.domain.dto.AddContactDto;
import greensea.energy.framework.domain.param.ProductParam;
import greensea.energy.framework.service.ICarsouselService;
import greensea.energy.framework.service.IContactService;
import greensea.energy.framework.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ITextService iTextService;
    @Autowired
    private IContactService iContactService;
    @Autowired
    private IImgService iImgService;
    @Autowired
    private IDevUpdateService iDevUpdateService;
    @PostMapping("/carsousel")
    @Operation(summary = "前台轮播图获取",description= "获取轮播图")
    public R getyCarsousels() {
        return iCarsouselService.getCarsousels();
    }
    @PostMapping("/product")
    @Operation(summary = "s前台设备获取",description= "获取轮播图")
    public R getyProducts(@RequestBody @Validated ProductParam productParam) {
        return iProductService.getProducts(productParam);
    }

    @PostMapping("/getPrivacyPolicy")
    @Operation(summary = "获取隐私政策",description= "获取隐私政策")
    public R getPrivacyPolicy() {
        return iTextService.getText("text:privacy_policy");
    }

    @PostMapping("/getCompanyIntro")
    @Operation(summary = "获取公司简介",description= "获取公司简介")
    public R getCompanyIntro() {
        return iTextService.getText("text:company_intro");
    }

    @PostMapping("/getProductIntroduction")
    @Operation(summary = "最新产品简介",description= "最新产品简介")
    public R getProductIntroduction() {
        return iTextService.getText("text:product_msg");
    }
    @PostMapping("/getUserLogin")
    @Operation(summary = "获取用户登陆地址",description= "获取用户登陆地址")
    public R getUserLogin() {
        return iTextService.getText("text:user_login");
    }

    @PostMapping("/getTextByKey")
    @Operation(summary = "获取文字",description= "获取通过key获取文字")
    public R getTextByKey(@RequestBody @Validated GetTextDto getTextDto) {
        return iTextService.getText1(getTextDto);
    }

    @PostMapping("/getImgByKey")
    @Operation(summary = "获取图片",description= "获取通过key获取图片")
    public R getTextByKey(@RequestBody @Validated GetImgDto getImgDto) {
        return iImgService.getImg(getImgDto);
    }




    @PostMapping("/addContactUs")
    @Operation(summary = "添加联系我们",description= "添加联系我们")
    public R addContactUs(@RequestBody @Validated AddContactDto addContactDto) {
        return iContactService.addContactUs(addContactDto);
    }

    @PostMapping("/devUpdateDelivery")
    @Operation(summary = "设备获取更新信息",description= "添加联系我们")
    public R devUpdateDelivery(@RequestBody @Validated DevUpdateDeliveryDto devUpdateDeliveryDto) {
        if (StringUtils.isNotBlank(devUpdateDeliveryDto.getFileType())){
            return iDevUpdateService.devUpdateDelivery(devUpdateDeliveryDto);
        }else {
            return iDevUpdateService.updateDelivery(devUpdateDeliveryDto);
        }
    }

}
