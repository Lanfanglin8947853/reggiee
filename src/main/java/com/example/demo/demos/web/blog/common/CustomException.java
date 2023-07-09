package com.example.demo.demos.web.blog.common;

public class CustomException extends RuntimeException{//自定义异常类
    public CustomException(String msg){
        super(msg);
    }
}
