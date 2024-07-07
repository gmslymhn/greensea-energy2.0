//package greensea.energy.framework.controller;
//
//import greensea.energy.framework.service.IFileService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName: FileController
// * @Description:
// * @Author: gmslymhn
// * @CreateTime: 2024-07-06 23:37
// * @Version: 1.0
// **/
//@Slf4j
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @Autowired
//    private IFileService iFileService;
//
//    /**
//     * 文件上传接口
//     * @param file
//     * @returna
//     */
//    @PostMapping("/upload")
//    public Map<String, Object> upload(@RequestPart("file") MultipartFile file){
//        String imgFileStr = iFileService.upload(file);
//        return buildResult(imgFileStr);
//    }
//
//    /**
//     * 测试返回拼装，根据公司自己封装的统一返回去写
//     * @param str
//     * @return
//     */
//    private Map<String,Object> buildResult(String str){
//        Map<String, Object> result = new HashMap<>();
//        //判断字符串用lang3下的StringUtils去判断，这块我就不引入新的依赖了
//        if(str== null || "".equals(str)){
//            result.put("code",10000);
//            result.put("msg","图片上传失败");
//            result.put("data",null);
//        }else{
//            result.put("code",200);
//            result.put("msg","图片上传成功");
//            result.put("data",str);
//        }
//        return result;
//    }
//
//}