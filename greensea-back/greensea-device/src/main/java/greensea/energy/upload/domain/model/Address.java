package greensea.energy.upload.domain.model;

import greensea.energy.common.validator.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: Address
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-30 10:12
 * @Version: 1.0
 **/
@Data
@Schema(description = "地址信息类")
public class Address {
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String continent;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String country;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String areacode;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String lat;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String lng;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String radius;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String prov;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String city;
    @NotBlank(message = "不能为空")
    @Xss(message = "不能包含脚本字符")
    @Size(max=10,message="最长10字符")
    String district;
}
