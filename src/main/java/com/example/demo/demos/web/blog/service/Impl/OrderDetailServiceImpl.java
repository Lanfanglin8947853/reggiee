package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.dao.OrderDetailMapper;
import com.example.demo.demos.web.blog.domain.OrderDetail;
import com.example.demo.demos.web.blog.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
