package com.example.demo.demos.web.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.demos.web.blog.domain.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
