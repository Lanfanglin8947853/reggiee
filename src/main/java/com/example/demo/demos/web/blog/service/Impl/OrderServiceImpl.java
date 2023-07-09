package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.common.BaseContext;
import com.example.demo.demos.web.blog.common.CustomException;
import com.example.demo.demos.web.blog.dao.OrderMapper;
import com.example.demo.demos.web.blog.domain.*;
import com.example.demo.demos.web.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public void submit(Orders orders) {
        //1.获取当前用户id
        Long userId = BaseContext.getCurrentId();
        //条件构造器
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //2.根据当前用户id查询其购物车数据
        shoppingCartLambdaQueryWrapper.eq(userId != null, ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(shoppingCartLambdaQueryWrapper);
        //判断一下购物车是否为空
        if (shoppingCarts == null) {
            throw new CustomException("购物车数据为空，不能下单");
        }
        //3.判断一下地址是否有误
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBookId == null) {
            throw new CustomException("地址信息有误，不能下单");
        }
        //4.获取用户信息，为了后面赋值
        User user = userService.getById(userId);
        long orderId = IdWorker.getId();//生成订单id
        AtomicInteger amount = new AtomicInteger(0);
        //5.向订单细节表设置属性
        List<OrderDetail> orderDetailList= shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);//订单id
            orderDetail.setName(item.getName());//菜品名称
            orderDetail.setImage(item.getImage());//菜品图片
            orderDetail.setDishId(item.getDishId());//菜品id
            orderDetail.setSetmealId(item.getSetmealId());//套餐id
            orderDetail.setDishFlavor(item.getDishFlavor());//菜品口味
            orderDetail.setNumber(item.getNumber());//数量
            orderDetail.setAmount(item.getAmount());//金额
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());//计算总金额

            return orderDetail;
        }).collect(Collectors.toList());

        //6.向订单表设置属性
        orders.setId(orderId);//订单id
        orders.setNumber(String.valueOf(orderId));//订单号
        orders.setStatus(2);//订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
        orders.setUserId(userId);//用户id
        orders.setAddressBookId(addressBookId);//地址id
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setPhone(addressBook.getPhone());//手机号
        orders.setUserName(user.getName());//用户名
        orders.setConsignee(addressBook.getConsignee());//收货人
        orders.setAddress(//地址
                (addressBook.getProvinceName() == null ? "":addressBook.getProvinceName())+
                        (addressBook.getCityName() == null ? "":addressBook.getCityName())+
                        (addressBook.getDistrictName() == null ? "":addressBook.getDistrictName())+
                        (addressBook.getDetail() == null ? "":addressBook.getDetail())
        );

        //7.根据查询到的购物车数据，对订单表插入数据（1条）
        super.save(orders);
        //根据查询到的购物车数据，对订单明细表插入数据（多条）
        orderDetailService.saveBatch(orderDetailList);
        //8.清空购物车数据
        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
    }

}
