package greensea.energy.framework.domain.dto.param;

import com.baomidou.mybatisplus.annotation.TableField;
import greensea.energy.framework.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: ProductPageParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-12-24 20:39
 * @Version: 1.0
 **/
@Data
public class ProductPageParam extends PageParam {

    /**
     * 产品名称
     */
    @Schema(description = "产品名称")
    @TableField(value = "product_name")
    @Size(min = 1, max = 20, message = "产品名称必须在1~20字符之间")
    private String productName;
    /**
     * 产品类型
     */
    @Schema(description = "产品类型")
    @Size(min = 1, max = 20, message = "产品类型必须在1~20字符之间")
    private String productType;
}
