package greensea.energy.framework.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import greensea.energy.common.constant.KeyConstants;
import greensea.energy.common.utils.ObjectUtils;
import greensea.energy.common.utils.RedisUtils;
import greensea.energy.framework.config.OSSConfig;
import greensea.energy.framework.domain.entity.ResourceEntity;
import greensea.energy.framework.mapper.ResourceMapper;
import greensea.energy.framework.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: FileServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-06 23:34
 * @Version: 1.0
 **/
@Service
@Slf4j
public class FileServiceImpl implements IFileService {

    @Autowired
    private OSSConfig ossConfig;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 阿里云OSS文件上传
     *
     * @param file
     */
    @Override
    public String upload(MultipartFile file) {

        //获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endPoint = ossConfig.getEndPoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //获取原生文件名
        String originalFilename = file.getOriginalFilename();
        //JDK8的日期格式
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //拼装OSS上存储的路径
        String folder = dft.format(time);
        String fileName = generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        //在OSS上bucket下的文件名
        String uploadFileName = "greensea/" + folder + "/" + fileName + extension;

        try {
            PutObjectResult result = ossClient.putObject(bucketName, uploadFileName, file.getInputStream());
            //拼装返回路径
            if (result != null) {
                return "https://"+bucketName+"."+endPoint+"/"+uploadFileName;
            }
        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
        } finally {
            //OSS关闭服务，不然会造成OOM
            ossClient.shutdown();
        }
        return null;
    }


    /**
     * 获取临时url
     *
     * @return
     */
    @Override
    public String getTemporaryUrl(Integer resourceId) {
        String key = KeyConstants.RESOURCE_ID+resourceId;
        String url = (String) redisUtils.getCacheObject(key);
        if (ObjectUtils.isNotNull(url)){
            return url;
        }
        //获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endPoint = ossConfig.getEndPoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        Integer expirationDay = ossConfig.getExpiration();

        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        // 设置过期时间
        Date expiration = new Date(System.currentTimeMillis() + expirationDay * 3600 * 1000); // 1 小时后过期
        // 生成临时访问 URL
        ResourceEntity resourceEntity = resourceMapper.selectById(resourceId);
        int numChars = "https://greensea.oss-cn-beijing.aliyuncs.com/".length();

        String uploadFileName = resourceEntity.getResourceUrl().substring(numChars);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, uploadFileName);
        request.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(request);
        String urlString = signedUrl.toString();
        //缓存过期时间比oss过期时间少一天
        redisUtils.setCacheObject(key,urlString, expirationDay - 1,TimeUnit.HOURS);
//        RedisUtil.StringOps.setEx(uploadFileName, urlString, expirationDay - 1, TimeUnit.DAYS);
        // 关闭 OSS 客户端

        ossClient.shutdown();

        return urlString;
    }
    /**
     * 获取随机字符串
     * @return
     */
    private String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}