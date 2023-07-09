package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.dao.ShoppingCartMapper;
import com.example.demo.demos.web.blog.domain.ShoppingCart;
import com.example.demo.demos.web.blog.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
