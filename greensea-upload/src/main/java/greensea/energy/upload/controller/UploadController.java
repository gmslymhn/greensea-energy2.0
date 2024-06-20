package greensea.energy.upload.controller;

import greensea.energy.upload.domain.entity.UploadEntity;
import greensea.energy.upload.domain.entity.UploadEntity2;
import greensea.energy.upload.service.IUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: UploadController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-06-07 16:51
 * @Version: 1.0
 **/
@RestController
@Tag(name = "管理员")
@RequestMapping("upload")
@Slf4j
public class UploadController {
    @Autowired
    private IUploadService iUploadService;
    @PostMapping("/abc")
    @Operation(summary = "csv" ,description= "用于管理员登陆")
    public String submitOriginalFile(MultipartFile file) {
        // 拿取前端标识的文件
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String imagePath = null;
        if(suffixName.equals(".csv")){
            imagePath ="Markdown\\";
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT);
            CSVRecord csvRecord = csvParser.getRecords().get(6);
            System.out.println(csvRecord);
            UploadEntity uploadEntity = new UploadEntity(Integer.valueOf(csvRecord.get(2)),
                    Integer.valueOf(csvRecord.get(3)),Integer.valueOf(csvRecord.get(4)),Float.valueOf(csvRecord.get(5))
                    ,Float.valueOf(csvRecord.get(6)),Float.valueOf(csvRecord.get(7)),Float.valueOf(csvRecord.get(8)),Float.valueOf(csvRecord.get(9)),
                    Float.valueOf(csvRecord.get(10)),Float.valueOf(csvRecord.get(11))
            ,Integer.valueOf(csvRecord.get(12)),
                    Float.valueOf(csvRecord.get(13)),Float.valueOf(csvRecord.get(14)),Float.valueOf(csvRecord.get(15)),Float.valueOf(csvRecord.get(16)),
                    Float.valueOf(csvRecord.get(17)),Float.valueOf(csvRecord.get(18)),Float.valueOf(csvRecord.get(19)),Float.valueOf(csvRecord.get(20)),
                    Float.valueOf(csvRecord.get(21)));
            UploadEntity2 uploadEntity2 = new UploadEntity2(Float.valueOf(csvRecord.get(21)),Float.valueOf(csvRecord.get(22)),Float.valueOf(csvRecord.get(23)),
                    Float.valueOf(csvRecord.get(24)),Float.valueOf(csvRecord.get(25)),Float.valueOf(csvRecord.get(26)),Float.valueOf(csvRecord.get(27)),
                    Float.valueOf(csvRecord.get(28)),Float.valueOf(csvRecord.get(29)),Float.valueOf(csvRecord.get(30)),Float.valueOf(csvRecord.get(31)),
                    Float.valueOf(csvRecord.get(32)),Float.valueOf(csvRecord.get(33)),Float.valueOf(csvRecord.get(34)),Float.valueOf(csvRecord.get(35)),
                    Float.valueOf(csvRecord.get(36)),Float.valueOf(csvRecord.get(37)),Float.valueOf(csvRecord.get(38)),Float.valueOf(csvRecord.get(39)),
                    Float.valueOf(csvRecord.get(40)),Float.valueOf(csvRecord.get(41)),Float.valueOf(csvRecord.get(42)),Float.valueOf(csvRecord.get(43)),
                    Float.valueOf(csvRecord.get(44)),Float.valueOf(csvRecord.get(45)),Float.valueOf(csvRecord.get(46)),Float.valueOf(csvRecord.get(47)));
            iUploadService.uploadAsync("dev_1",uploadEntity);
            iUploadService.uploadAsync2("inv_1",uploadEntity2);
            csvParser.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "读取成功!";
    }
}
