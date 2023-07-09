package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.dao.UserMapper;
import com.example.demo.demos.web.blog.domain.User;
import com.example.demo.demos.web.blog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
