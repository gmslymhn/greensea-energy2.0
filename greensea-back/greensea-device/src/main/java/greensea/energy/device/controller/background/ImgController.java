package greensea.energy.device.controller.background;

import greensea.energy.common.annotation.SysLogAnnotation;
import greensea.energy.common.domain.R;
import greensea.energy.device.domain.entity.ImgEntity;
import greensea.energy.device.domain.entity.TextEntity;
import greensea.energy.device.service.IImgService;
import greensea.energy.framework.domain.PageParam;
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
 * @ClassName: ImgController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-21 14:00
 * @Version: 1.0
 **/
@RestController
@Tag(name = "修改前端图片")
@RequestMapping("background/img")
@Slf4j
public class ImgController {
    @Autowired
    private IImgService iImgService;
    @PostMapping("/getImgList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取文字列表",description= "超级管理员state字段不会起作用，添加完成之后可以修改state")
    public R getTextList(@RequestBody @Validated PageParam pageParam) {
        return iImgService.getImgList(pageParam);
    }
    @PostMapping("/updateImg")
    @PreAuthorize("@ss.hasPermission('admin')")
    @SysLogAnnotation(operModul = "前台管理>>前端图片管理", operType = "修改", operDesc = "修改前端图片")
    @Operation(summary = "修改前端文字")
    public R updateText(@RequestBody @Validated ImgEntity imgEntity) {
        return iImgService.updateImg(imgEntity);
    }
}
