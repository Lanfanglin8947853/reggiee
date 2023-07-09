package com.example.demo.demos.web.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.demos.web.blog.domain.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
