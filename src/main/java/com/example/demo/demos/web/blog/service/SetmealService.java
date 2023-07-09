package com.example.demo.demos.web.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.demos.web.blog.domain.Setmeal;
import com.example.demo.demos.web.blog.domain.SetmealDto;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);
}