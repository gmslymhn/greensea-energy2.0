package greensea.energy.framework.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: AddContactDto
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-19 20:27
 * @Version: 1.0
 **/
@Data
public class AddContactDto {
    @TableField("contact_type")
    @Size(min = 1, max = 30, message = "联系我们类型必须在1~30字符之间")
    @NotBlank(message = "联系我们类型不能为空")
    private String contactType;

    @TableField("contact_surname")
    @NotBlank(message = "联系人的姓不能为空")
    @Size(min = 1, max = 30, message = "联系人的姓必须在1~30字符之间")
    private String contactSurname;

    @TableField("contact_name")
    @NotBlank(message = "联系人的名不能为空")
    @Size(min = 1, max = 30, message = "联系人的名必须在1~30字符之间")
    private String contactName;

    @TableField("contact_mail")
    @NotBlank(message = "联系人的邮箱不能为空")
    @Size(min = 1, max = 250, message = "联系人的邮箱必须在1~30字符之间")
    private String contactMail;


    @TableField("contact_phone")
    @NotBlank(message = "联系人的电话不能为空")
    @Size(min = 1, max = 250, message = "联系人的电话必须在1~30字符之间")
    private String contactPhone;

    @TableField("contact_country")
    @NotBlank(message = "联系人的国家不能为空")
    @Size(min = 1, max = 16, message = "联系人的国家必须在1~30字符之间")
    private String contactCountry;

    @TableField("contact_how")
    @NotBlank(message = "联系我们的问题不能为空")
    @Size(min = 1, max = 500, message = "联系人问题必须在1~30字符之间")
    private String contactHow;

    @TableField("contact_msg")
    @NotBlank(message = "联系我们的内容不能为空")
    @Size(min = 1, max = 2000, message = "联系人内容必须在1~30字符之间")
    private String contactMsg;
}
