package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.entity.CategoriesEntity;
import org.zzd.param.BasePageParam;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.vo.AddCategoryVo;

/**
 * @apiNote 分类(Categories)服务接口
 * @author zzd
 * @date 2023-05-30 16:48:49
 */
public interface CategoriesService extends IService<CategoriesEntity> {
    // 分页查询
    ResponseResult<PageHelper<CategoriesEntity>> queryPage(BasePageParam params);

    ResponseResult createCategory(AddCategoryVo categoryVo);
}

