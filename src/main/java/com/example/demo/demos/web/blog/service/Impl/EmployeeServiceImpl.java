package com.example.demo.demos.web.blog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.demos.web.blog.dao.EmployeeMapper;
import com.example.demo.demos.web.blog.domain.Employee;
import com.example.demo.demos.web.blog.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}