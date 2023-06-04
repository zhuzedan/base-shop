package org.zzd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @apiNote 分类(Categories)实体类
 * @author zzd
 * @date 2023-05-30 17:01:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_categories")
public class CategoriesEntity extends BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "自增主键id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "分类级别1，2，3")
    private Integer level;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
