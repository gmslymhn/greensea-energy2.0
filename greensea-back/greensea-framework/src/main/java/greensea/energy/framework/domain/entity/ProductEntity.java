package greensea.energy.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import greensea.energy.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProductEntity
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-10 14:45
 * @Version: 1.0
 **/
@TableName("gre_product")
@Data
@Schema(description = "产品实体类")
@NoArgsConstructor
public class ProductEntity extends BaseEntity {
    /**
     * 产品id
     */
    @Schema(description = "产品id")
    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;
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
    @TableField(value = "product_type")
    @Size(min = 1, max = 20, message = "产品类型必须在1~20字符之间")
    private String productType;
    /**
     * 产品简介
     */
    @Schema(description = "产品简介")
    @TableField(value = "product_intro")
    @Size(min = 1, max = 200, message = "产品简介必须在1~200字符之间")
    private String productIntro;
    /**
     * 产品图片id
     */
    @Schema(description = "产品图片id")
    @TableField(value = "product_image_id")
    private Integer productImageId;
    /**
     * 图片url
     */
    @Schema(description = "图片url")
    @TableField(exist = false)
    private String productImageUrl;
    /**
     * 产品Pdfid
     */
    @Schema(description = "产品pdf")
    @TableField(value = "product_pdf_id")
    private Integer productPdfId;
    /**
     * PDFurl
     */
    @Schema(description = "PDFFurl")
    @TableField(exist = false)
    private String productPdfUrl;
    /**
     * 产品图表id
     */
    @Schema(description = "产品图表")
    @TableField(value = "product_chart")
    @Size(min = 1, max = 200, message = "产品简介必须在1~200字符之间")
    private String productChart;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @TableField("state")
    private Boolean state;
    /**
     * 排序
     */
    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;
}
