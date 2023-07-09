package com.example.demo.demos.web.blog.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {
    private List<OrderDetail> orderDetails;

}
