package greensea.energy.upload.controller;

import greensea.energy.upload.domain.R;
import greensea.energy.upload.domain.dto.TokenDto;
import greensea.energy.upload.domain.dto.UploadDto;
import greensea.energy.upload.domain.model.Device;
import greensea.energy.upload.domain.model.DeviceToken;
import greensea.energy.upload.service.IUploadService;
import greensea.energy.upload.service.ipml.TokenService;
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

import java.util.HashMap;
import java.util.Map;

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
    @PostMapping("/upload")
    @Operation(summary = "json" ,description= "设备数据上传")
    public R upload(@RequestBody @Validated UploadDto uploadDto,HttpServletRequest request, HttpServletResponse response) {

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
            return R.error("非法上传！");
        }
        DeviceToken deviceToken = tokenService.getDeviceToken(request);
        if (ObjectUtils.isNull(deviceToken)){
            return null;
        }
        if (IpUtil.getIpAddress(request).equals(deviceToken.getDeviceIp())){
            return R.success("上传成功！");
        }
        return null;
    }
    @PostMapping("/token")
    @Operation(summary = "设备获取token" ,description= "设备获取token")
    public R getToken(@RequestBody @Validated TokenDto tokenDto, HttpServletRequest request) {
        Device device =tokenService.getDevice(tokenDto.getDeviceNumber());
        if (ObjectUtils.isNull(device)){
            return null;
        }
        String token = tokenService.createToken(device);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.success(map);
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
