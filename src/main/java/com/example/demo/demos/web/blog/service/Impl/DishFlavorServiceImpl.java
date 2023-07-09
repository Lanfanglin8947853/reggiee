package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.dao.DishFlavorMapper;
import com.example.demo.demos.web.blog.domain.DishFlavor;
import com.example.demo.demos.web.blog.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
