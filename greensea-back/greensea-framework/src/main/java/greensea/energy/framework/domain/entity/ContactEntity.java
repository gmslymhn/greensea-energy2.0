package greensea.energy.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import greensea.energy.framework.domain.dto.AddContactDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ContactEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-09-19 20:16
 * @Version: 1.0
 **/
@TableName("gre_contact")
@Data
@Schema(description = "联系我们实体类")
@NoArgsConstructor
public class ContactEntity {


    @Schema(description = "联系人id")
    @TableId(value = "contact_id",type = IdType.AUTO)
    private Integer contactId;

    @TableField("contact_ip")
    @Size(min = 1, max = 30, message = "联系人ip必须在1~30字符之间")
    private String contactIp;


    @TableField("contact_type")
    @Size(min = 1, max = 30, message = "联系我们类型必须在1~30字符之间")
    private String contactType;

    @TableField("contact_surname")
    @Size(min = 1, max = 30, message = "联系人的姓必须在1~30字符之间")
    private String contactSurname;

    @TableField("contact_name")
    @Size(min = 1, max = 30, message = "联系人的名必须在1~30字符之间")
    private String contactName;

    @TableField("contact_mail")
    @Size(min = 1, max = 250, message = "联系人的邮箱必须在1~30字符之间")
    private String contactMail;


    @TableField("contact_phone")
    @Size(min = 1, max = 250, message = "联系人的电话必须在1~30字符之间")
    private String contactPhone;

    @TableField("contact_country")
    @Size(min = 1, max = 16, message = "联系人的国家必须在1~30字符之间")
    private String contactCountry;

    @TableField("contact_how")
    @Size(min = 1, max = 500, message = "联系人问题必须在1~30字符之间")
    private String contactHow;

    @TableField("contact_msg")
    @Size(min = 1, max = 2000, message = "联系人内容必须在1~30字符之间")
    private String contactMsg;

    @TableField("create_time")
    private String createTime;

    public ContactEntity(AddContactDto addContactDto){
        this.contactCountry = addContactDto.getContactCountry();
        this.contactHow = addContactDto.getContactHow();
        this.contactMsg = addContactDto.getContactMsg();
        this.contactMail = addContactDto.getContactMail();
        this.contactName  = addContactDto.getContactName();
        this.contactSurname = addContactDto.getContactSurname();
        this.contactType = addContactDto.getContactType();
        this.contactPhone = addContactDto.getContactPhone();
    }
}
