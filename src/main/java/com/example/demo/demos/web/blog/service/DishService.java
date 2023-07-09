package com.example.demo.demos.web.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.demos.web.blog.domain.Dish;
import com.example.demo.demos.web.blog.domain.DishDto;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
