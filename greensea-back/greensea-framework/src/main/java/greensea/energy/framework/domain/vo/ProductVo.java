package greensea.energy.framework.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @ClassName: ProductVo
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 17:51
 * @Version: 1.0
 **/
@Data
@Schema(defaultValue = "产品信息")
public class ProductVo {
    /**
     * 产品名称
     */
    @Schema(description = "产品名称")
    @Size(min = 1, max = 20, message = "产品名称必须在1~20字符之间")
    private String productName;
    /**
     * 产品类型
     */
    @Schema(description = "产品类型")
    @Size(min = 1, max = 20, message = "产品类型必须在1~20字符之间")
    private String productType;
    /**
     * 产品简介
     */
    @Schema(description = "产品简介")
    @Size(min = 1, max = 200, message = "产品简介必须在1~200字符之间")
    private String productIntro;
    /**
     * 图片url
     */
    @Schema(description = "图片url")
    private String productImageUrl;
    /**
     * PDFurl
     */
    @Schema(description = "PDFFurl")
    private String productPdfUrl;
    /**
     * 产品图表id
     */
    @Schema(description = "产品图表")
    @Size(min = 1, max = 200, message = "产品图表必须在1~200字符之间")
    private String productChart;
}
