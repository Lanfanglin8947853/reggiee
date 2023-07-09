package com.example.demo.demos.web.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.demos.web.blog.domain.Category;
import com.example.demo.demos.web.blog.domain.Dish;
import com.example.demo.demos.web.blog.service.CategoryService;
import com.example.demo.demos.web.blog.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //分类管理，分类信息分页查询
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize) {
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件查询器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByDesc(Category::getSort);//根据sort字段降序排列
        //分页查询
        categoryService.page(pageInfo, queryWrapper);//这里的pageInfo就是返回的分页数据
        return Result.success(pageInfo);
    }

    //添加分类
    @PostMapping
    public Result<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return Result.success(category.getType() == 1 ? "添加菜品分类成功！" : "添加套餐分类成功！");
    }

    //删除分类
    @DeleteMapping
    private Result<String> delete(Long ids) {
        log.info("将被删除的id：{}", ids);
        categoryService.remove(ids);
        return Result.success("删除成功！");
    }

    //修改分类
    @PutMapping
    public Result<String> update(@RequestBody Category category) {
        log.info("修改分类信息为：{}", category);
        categoryService.updateById(category);
        return Result.success("修改分类信息成功");
    }


    /**
     * 根据条件查询分类数据,返回到菜品管理和套餐管理的下拉框里去
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Category category) {//category的type属性值为1或2
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件，这里只需要判断是否为菜品（type为1是菜品，type为2是套餐）
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        //查询数据
        List<Category> list = categoryService.list(queryWrapper);
        //返回数据
        return Result.success(list);
    }




}
