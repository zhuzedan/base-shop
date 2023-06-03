package org.zzd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.zzd.entity.Categories;

/**
 * @apiNote 分类(Categories)数据库访问层
 * @author zzd
 * @date 2023-05-30 16:48:49
 */
@Repository
public interface CategoriesMapper extends BaseMapper<Categories> {

}

