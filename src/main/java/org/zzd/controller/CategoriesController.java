package org.zzd.controller;

import org.springframework.validation.annotation.Validated;
import org.zzd.dto.categories.CreateCategoryDto;
import org.zzd.dto.categories.UpdateCategoryDto;
import org.zzd.entity.CategoriesEntity;
import org.zzd.service.CategoriesService;
import org.zzd.utils.PageHelper;
import org.zzd.param.BasePageParam;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;
import org.zzd.result.ResponseResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @apiNote 分类(Categories)控制器
 * @author zzd
 * @date 2023-05-30 16:52:19
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/queryCategories")
    public ResponseResult<PageHelper<CategoriesEntity>> queryPage(BasePageParam params) {
        return categoriesService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/readCategories")
    public ResponseResult selectOne(Integer id) {
        CategoriesEntity categoriesEntity = categoriesService.getById(id);
        if (!Objects.isNull(categoriesEntity)) {
            return ResponseResult.success(categoriesEntity);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID);
        }
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/insertCategories")
    public ResponseResult insert(@Validated @RequestBody CreateCategoryDto categoryDto) {
        return categoriesService.createCategory(categoryDto);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/updateCategories")
    public ResponseResult update(@Validated @RequestBody UpdateCategoryDto categoryDto) {
        categoriesService.updateCategory(categoryDto);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("deleteCategories")
    public ResponseResult delete(Long id) {
        categoriesService.deleteCategory(id);
        return ResponseResult.success();
    }

    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemoveCategories")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        categoriesService.removeByIds(idList);
        return ResponseResult.success();
    }
}

