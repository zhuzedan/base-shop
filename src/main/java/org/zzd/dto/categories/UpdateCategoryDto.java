package org.zzd.dto.categories;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @apiNote 更新分类
 * @author zzd
 * @date 2023/6/4 15:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCategoryDto extends CreateCategoryDto {
    @ApiModelProperty(value = "分类id")
    @NotNull(message = "分类目录id不能为空")
    private Long id;
}
