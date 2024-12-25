package greensea.energy.framework.controller.background.gm;

import greensea.energy.common.domain.R;
import greensea.energy.framework.domain.PageParam;
import greensea.energy.framework.service.IContactService;
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
 * @ClassName: ContactController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-19 16:08
 * @Version: 1.0
 **/
@RestController
@Tag(name = "留言板")
@RequestMapping("background/gm")
@Slf4j
public class ContactController {
    @Autowired
    private IContactService iContactService;
    @PostMapping("/getContactList")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "获取留言列表",description= "超级管理员")
    public R getCarsouselList(@RequestBody @Validated PageParam pageParam) {
        return iContactService.getContactUsList(pageParam);
    }
}
