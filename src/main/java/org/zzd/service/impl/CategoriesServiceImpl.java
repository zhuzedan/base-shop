package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.dto.categories.CreateCategoryDto;
import org.zzd.dto.categories.UpdateCategoryDto;
import org.zzd.entity.CategoriesEntity;
import org.zzd.exception.ResponseException;
import org.zzd.mapper.CategoriesMapper;
import org.zzd.param.BasePageParam;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.CategoriesService;
import org.zzd.utils.PageHelper;

/**
 * @apiNote 分类(Categories)服务实现类
 * @author zzd
 * @date 2023-05-30 16:48:49
 */
@Service("categoriesService")
@Slf4j
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, CategoriesEntity> implements CategoriesService {
    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public ResponseResult<PageHelper<CategoriesEntity>> queryPage(BasePageParam params) {
        LambdaQueryWrapper<CategoriesEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<CategoriesEntity> page = new Page<>(params.getPageNum(), params.getPageSize());
        IPage<CategoriesEntity> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    @Override
    public ResponseResult createCategory(CreateCategoryDto categoryDto) {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        // 将拷贝的源，拷贝到目标类中
        BeanUtils.copyProperties(categoryDto, categoriesEntity);
        CategoriesEntity category = categoriesMapper.selectByName(categoryDto.getName());
        if (category != null) {
            throw new ResponseException(ResultCodeEnum.NAME_EXISTED);
        }
        int flag = categoriesMapper.insert(categoriesEntity);
        //判断是否插入成功
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.CREATE_FAIL);
        }
        return ResponseResult.success();
    }

    @Override
    public void updateCategory(UpdateCategoryDto categoryDto) {
        if (categoryDto.getName() != null) {
            CategoriesEntity category = categoriesMapper.selectByName(categoryDto.getName());
            if (category != null && !category.getId().equals(categoryDto.getId())) {
                throw new ResponseException(ResultCodeEnum.NAME_EXISTED);
            }
        }
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        // 将拷贝的源，拷贝到目标类中
        BeanUtils.copyProperties(categoryDto, categoriesEntity);
        int flag = categoriesMapper.updateById(categoriesEntity);
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        CategoriesEntity category = categoriesMapper.selectById(id);
        if (category == null) {
            throw new ResponseException(ResultCodeEnum.DELETE_FAIL);
        }
        int flag = categoriesMapper.deleteById(id);
        if (flag == 0) {
            throw new ResponseException(ResultCodeEnum.DELETE_FAIL);
        }
    }
}

