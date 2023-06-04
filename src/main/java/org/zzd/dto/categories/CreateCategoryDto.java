package org.zzd.dto.categories;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @apiNote 新建分类
 * @author zzd
 * @date 2023/6/4 15:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDto {
    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @ApiModelProperty(value = "父级")
    @NotNull(message = "分类父级不能为空")
    private Long parentId;

    @ApiModelProperty(value = "分类级别1，2，3")
    @Max(3)
    @NotNull(message = "分类级别不能为空")
    private Integer level;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
