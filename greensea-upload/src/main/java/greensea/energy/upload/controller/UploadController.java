package greensea.energy.upload.controller;

import greensea.energy.upload.annotation.AbnormalLogAnnotation;
import greensea.energy.upload.domain.R;
import greensea.energy.upload.domain.dto.TokenDto;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.dto.UploadDto2;
import greensea.energy.upload.domain.dto.UploadMsgDto;
import greensea.energy.upload.domain.model.Device;
import greensea.energy.upload.domain.model.DeviceToken;
import greensea.energy.upload.domain.vo.UpdateVo;
import greensea.energy.upload.domain.vo.UpdatesVo;
import greensea.energy.upload.service.IUploadService;
import greensea.energy.upload.service.impl.DeliveryService;
import greensea.energy.upload.service.impl.TokenService;
import greensea.energy.upload.utils.JwtUtil;
import greensea.energy.upload.utils.ObjectUtils;
import greensea.energy.upload.utils.http.IpUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: UploadController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 16:51
 * @Version: 1.0
 **/
@RestController
@Tag(name = "管理员")
@RequestMapping()
@Slf4j
public class UploadController {
    @Autowired
    private IUploadService iUploadService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DeliveryService deliveryService;
    @PostMapping("/upload")
    @AbnormalLogAnnotation()
    @Operation(summary = "上传数据" ,description= "设备数据上传")
    public R upload(@RequestBody @Validated UploadDto uploadDto,HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {

        String token = request.getHeader(jwtUtil.gerHeader());
        // 未获取到token，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以可以放行
        // 没有token相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (ObjectUtils.isNull(token)) {
            return R.error("非法上传！");
        }
        log.info(token);
        Claims claims = jwtUtil.getClaimsByToken(token);
        if (claims == null) {
            return R.error("非法上传！");
        }
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            return R.error("Token过期！");
        }
        DeviceToken deviceToken = tokenService.getDeviceToken(request);
        if (ObjectUtils.isNull(deviceToken)){
            return R.error("设备不存在！");
        }
        if (IpUtil.getIpAddress(request).equals(deviceToken.getDeviceIp())){
            uploadDto.setDeviceNumber(deviceToken.getDeviceNumber());
            tokenService.updateDeviceMsg(uploadDto);
//            return R.error("数据异常！");
            return R.success(iUploadService.upload(uploadDto).get());
        }
        return R.error("非法上传！");
    }
    @PostMapping("/uploadAddress")
    @AbnormalLogAnnotation()
    @Operation(summary = "上传数据2" ,description= "设备地址经纬°等数据上传")
    public R upload2(@RequestBody @Validated UploadDto2 uploadDto2,HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {

        String token = request.getHeader(jwtUtil.gerHeader());
        // 未获取到token，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以可以放行
        // 没有token相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (ObjectUtils.isNull(token)) {
            return R.error("非法上传！");
        }
        log.info(token);
        Claims claims = jwtUtil.getClaimsByToken(token);
        if (claims == null) {
            return R.error("非法上传！");
        }
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            return R.error("Token过期！");
        }
        DeviceToken deviceToken = tokenService.getDeviceToken(request);
        if (ObjectUtils.isNull(deviceToken)){
            return R.error("设备不存在！");
        }
        if (IpUtil.getIpAddress(request).equals(deviceToken.getDeviceIp())){
            uploadDto2.setDeviceNumber(deviceToken.getDeviceNumber());
            tokenService.updateDeviceAddress(uploadDto2);
//            return R.error("数据异常！");
            return R.success(iUploadService.upload2(uploadDto2).get());
        }
        return R.error("非法上传！");
    }
    @PostMapping("/updateMessage")
    @AbnormalLogAnnotation()
    @Operation(summary = "上传数据3" ,description= "上传原始报文信息")
    public R upload3(@RequestBody @Validated UploadMsgDto uploadMsgDto, HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {

        String token = request.getHeader(jwtUtil.gerHeader());
        // 未获取到token，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以可以放行
        // 没有token相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (ObjectUtils.isNull(token)) {
            return R.error("非法上传！");
        }
        log.info(token);
        Claims claims = jwtUtil.getClaimsByToken(token);
        if (claims == null) {
            return R.error("非法上传！");
        }
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            return R.error("Token过期！");
        }
        DeviceToken deviceToken = tokenService.getDeviceToken(request);
        if (ObjectUtils.isNull(deviceToken)){
            return R.error("设备不存在！");
        }
        if (IpUtil.getIpAddress(request).equals(deviceToken.getDeviceIp())){
            uploadMsgDto.setDeviceNumber(deviceToken.getDeviceNumber());
//            return R.error("数据异常！");
            return R.success(iUploadService.upload3(uploadMsgDto).get());
        }
        return R.error("非法上传！");
    }

    @PostMapping("/dataDelivery")
    @AbnormalLogAnnotation()
    @Operation(summary = "下发数据" ,description= "云端下发数据")
    public R delivery(HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {

        String token = request.getHeader(jwtUtil.gerHeader());
        // 未获取到token，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以可以放行
        // 没有token相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (ObjectUtils.isNull(token)) {
            return R.error("非法上传！");
        }
        log.info(token);
        Claims claims = jwtUtil.getClaimsByToken(token);
        if (claims == null) {
            return R.error("非法上传！");
        }
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            return R.error("Token过期！");
        }
        DeviceToken deviceToken = tokenService.getDeviceToken(request);
        if (ObjectUtils.isNull(deviceToken)){
            return R.error("设备不存在！");
        }
        if (IpUtil.getIpAddress(request).equals(deviceToken.getDeviceIp())){
            return  deliveryService.delivery(deviceToken.getDeviceNumber());
        }
        return R.error("非法上传！");
    }

    @PostMapping("/token")
    @Operation(summary = "设备获取token" ,description= "设备获取token")
    public R getToken(@RequestBody @Validated TokenDto tokenDto, HttpServletRequest request) {
        Device device =tokenService.getDevice(tokenDto.getDeviceNumber());
        if (ObjectUtils.isNull(device)){
            return R.error("设备不存在！");
        }
        String token = tokenService.createToken(device);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.success(map);
    }

    @PostMapping("/update")
    @Operation(summary = "获取更新信息" ,description= "取更新信息")
    public R getUpdateUrl(HttpServletRequest request) {
        UpdateVo updateVo = new UpdateVo(true, 1.01F,LocalDateTime.now(),"http://greensea.oss-cn-beijing.aliyuncs.com/greensea/2024/11/05/a676e6237fdf423d806707e26cba32f4.bin?Expires=1730807981&OSSAccessKeyId=LTAI5tJyz6SuKSCZb1hz75P9&Signature=gTxLMZJckBkirQA%2BukXBd52BbJA%3D");
        return R.success(updateVo);
    }

    @PostMapping("/update2")
    @Operation(summary = "获取更新信息" ,description= "取更新信息")
    public R getUpdatesUrl(HttpServletRequest request) {
        UpdatesVo updatesVo = new UpdatesVo(true, 1.01F,LocalDateTime.now(),
                new String[]{"http://greensea.oss-cn-beijing.aliyuncs.com/greensea/2024/11/05/a676e6237fdf423d806707e26cba32f4.bin?Expires=1730807981&OSSAccessKeyId=LTAI5tJyz6SuKSCZb1hz75P9&Signature=gTxLMZJckBkirQA%2BukXBd52BbJA%3D",
                "http://greensea.oss-cn-beijing.aliyuncs.com/greensea/2024/11/05/d76d5da6f28d4be18a5770a3a92ed0e2.bin?Expires=1730808158&OSSAccessKeyId=LTAI5tJyz6SuKSCZb1hz75P9&Signature=0P3%2Fk0nFYZy56OjI4c0fyfiIFSM%3D",
                "http://greensea.oss-cn-beijing.aliyuncs.com/greensea/2024/11/05/72b108caea5f49489cb67243f1d9694c.bin?Expires=1730808170&OSSAccessKeyId=LTAI5tJyz6SuKSCZb1hz75P9&Signature=LKU2iu0sT%2BnZbQW7aMkU6yc08iM%3D"});
        return R.success(updatesVo);
    }




//    @PostMapping("/abc")
//    @Operation(summary = "csv" ,description= "用于管理员登陆")
//    public String submitOriginalFile(MultipartFile file) {
//        // 拿取前端标识的文件
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//        //获取文件后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        String imagePath = null;
//        if(suffixName.equals(".csv")){
//            imagePath ="Markdown\\";
//        }
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
//            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT);
//            CSVRecord csvRecord = csvParser.getRecords().get(6);
//            System.out.println(csvRecord);
//            UploadEntity uploadEntity = new UploadEntity(Integer.valueOf(csvRecord.get(2)),
//                    Integer.valueOf(csvRecord.get(3)),Integer.valueOf(csvRecord.get(4)),Float.valueOf(csvRecord.get(5))
//                    ,Float.valueOf(csvRecord.get(6)),Float.valueOf(csvRecord.get(7)),Float.valueOf(csvRecord.get(8)),Float.valueOf(csvRecord.get(9)),
//                    Float.valueOf(csvRecord.get(10)),Float.valueOf(csvRecord.get(11))
//            ,Integer.valueOf(csvRecord.get(12)),
//                    Float.valueOf(csvRecord.get(13)),Float.valueOf(csvRecord.get(14)),Float.valueOf(csvRecord.get(15)),Float.valueOf(csvRecord.get(16)),
//                    Float.valueOf(csvRecord.get(17)),Float.valueOf(csvRecord.get(18)),Float.valueOf(csvRecord.get(19)),Float.valueOf(csvRecord.get(20)),
//                    Float.valueOf(csvRecord.get(21)));
//            UploadEntity2 uploadEntity2 = new UploadEntity2(Float.valueOf(csvRecord.get(21)),Float.valueOf(csvRecord.get(22)),Float.valueOf(csvRecord.get(23)),
//                    Float.valueOf(csvRecord.get(24)),Float.valueOf(csvRecord.get(25)),Float.valueOf(csvRecord.get(26)),Float.valueOf(csvRecord.get(27)),
//                    Float.valueOf(csvRecord.get(28)),Float.valueOf(csvRecord.get(29)),Float.valueOf(csvRecord.get(30)),Float.valueOf(csvRecord.get(31)),
//                    Float.valueOf(csvRecord.get(32)),Float.valueOf(csvRecord.get(33)),Float.valueOf(csvRecord.get(34)),Float.valueOf(csvRecord.get(35)),
//                    Float.valueOf(csvRecord.get(36)),Float.valueOf(csvRecord.get(37)),Float.valueOf(csvRecord.get(38)),Float.valueOf(csvRecord.get(39)),
//                    Float.valueOf(csvRecord.get(40)),Float.valueOf(csvRecord.get(41)),Float.valueOf(csvRecord.get(42)),Float.valueOf(csvRecord.get(43)),
//                    Float.valueOf(csvRecord.get(44)),Float.valueOf(csvRecord.get(45)),Float.valueOf(csvRecord.get(46)),Float.valueOf(csvRecord.get(47)));
//            iUploadService.uploadAsync("dev_1",uploadEntity);
//            iUploadService.uploadAsync2("inv_1",uploadEntity2);
//            csvParser.close();
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "读取成功!";
//    }
}
