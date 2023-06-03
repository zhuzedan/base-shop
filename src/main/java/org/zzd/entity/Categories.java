package org.zzd.entity;

import java.util.Date;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @apiNote 分类(Categories)实体类
 * @author zzd
 * @date 2023-05-30 17:01:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_categories")
public class Categories implements Serializable {
    @TableId
    @ApiModelProperty(value = "自增主键id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父级")
    private Long parentId;

    @ApiModelProperty(value = "状态：0禁用1启用")
    private Integer status;

    @ApiModelProperty(value = "分类级别1，2，3")
    private Integer level;

    @ApiModelProperty(value = "goods商品分组，menu菜单分组")
    private String group;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标志")
    private Integer isDeleted;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
