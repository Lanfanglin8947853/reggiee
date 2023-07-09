package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.common.CustomException;
import com.example.demo.demos.web.blog.dao.SetmealMapper;
import com.example.demo.demos.web.blog.domain.Setmeal;
import com.example.demo.demos.web.blog.domain.SetmealDish;
import com.example.demo.demos.web.blog.domain.SetmealDto;
import com.example.demo.demos.web.blog.service.SetmealDishService;
import com.example.demo.demos.web.blog.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional//开启事务
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    protected SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时保存对应的菜品数据
     */
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);//保存套餐信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();//保存套餐和菜品的关系
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);//批量保存
    }


    /**
     * 删除套餐
     */
    @Override
    public void removeWithDish(List<Long> ids) {
        //先判断一下能不能删，如果status为1，则套餐在售，不能删
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId, ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, 1);
        List<Setmeal> setmeals = this.list(setmealLambdaQueryWrapper);
        if (setmeals.size() > 0) {
            throw new CustomException("套餐正在售卖中，请先停售再进行删除");
        }
        //删除套餐和菜品的关系
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }
}
