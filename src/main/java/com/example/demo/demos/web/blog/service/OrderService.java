package com.example.demo.demos.web.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.demos.web.blog.domain.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
