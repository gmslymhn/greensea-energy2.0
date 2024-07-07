package greensea.energy.framework.controller;

import greensea.energy.common.domain.R;
import greensea.energy.framework.service.IResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: ResourceController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-07 00:28
 * @Version: 1.0
 **/
@Slf4j
@RestController
@Tag(name = "文件上传")
@RequestMapping("/upload")
public class ResourceController {

    @Autowired
    private IResourceService iResourceService;
    @Operation(summary = "添加图片",description ="添加资源仅支持.jpg.png 文件，调用本接口后需根据返回信息调用添加资源接口")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Parameter(name="file",description="上传文件",required=true)
    @Parameter(name="description",description="文件描述")
    @PostMapping("/resource/addImage")
    public R handleFileUploadImage(MultipartFile file, String description) {
        return iResourceService.addResource(file, description, 1);
    }
    @Operation(summary = "获取资源")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Parameter(name="resourceId",description="文件id")
    @PostMapping("/resource/getResource")
    public R handleFileUploadImage(int resourceId) {
        return iResourceService.getResourceById(resourceId);
    }
}
