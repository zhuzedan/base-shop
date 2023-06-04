package org.zzd.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @apiNote 基础实体类
 * @author zzd
 * @date 2023/6/4 9:59
 */
@Data
public class BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态1启用0禁用")
    private Integer status;

    @ApiModelProperty(value = "删除标记（0:可用 1:已删除）")
    @TableLogic
    private Integer isDeleted;
}
