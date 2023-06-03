package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.param.BasePageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.entity.Categories;
import org.zzd.service.CategoriesService;
import org.zzd.mapper.CategoriesMapper;

import org.springframework.stereotype.Service;

/**
 * @apiNote 分类(Categories)服务实现类
 * @author zzd
 * @date 2023-05-30 16:48:49
 */
@Service("categoriesService")
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {
    @Autowired
    private CategoriesMapper categoriesMapper;
    
    @Override
    public ResponseResult<PageHelper<Categories>> queryPage(BasePageParam params) {
        LambdaQueryWrapper<Categories> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Categories> page = new Page<>(params.getPageNum(), params.getPageSize());
        IPage<Categories> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

