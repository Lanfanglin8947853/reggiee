package com.example.demo.demos.web.blog.common;

public class BaseContext {//这个类的作用是为了在拦截器中获取线程id
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
