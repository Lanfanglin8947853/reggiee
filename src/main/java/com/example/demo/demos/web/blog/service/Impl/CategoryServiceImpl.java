package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.common.CustomException;
import com.example.demo.demos.web.blog.dao.CategoryMapper;
import com.example.demo.demos.web.blog.domain.Category;
import com.example.demo.demos.web.blog.domain.Dish;
import com.example.demo.demos.web.blog.domain.Setmeal;
import com.example.demo.demos.web.blog.service.CategoryService;
import com.example.demo.demos.web.blog.service.DishService;
import com.example.demo.demos.web.blog.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;


    /**
     * 根据id删除分类，删除之前需要进行判断，如果分类下有菜品或者套餐，则不能删除
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0) {
//            System.out.println("该分类下有菜品，不能删除");
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
//            System.out.println("该分类下有套餐，不能删除");
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除
        super.removeById(id);//调用父类的删除方法
    }
}
