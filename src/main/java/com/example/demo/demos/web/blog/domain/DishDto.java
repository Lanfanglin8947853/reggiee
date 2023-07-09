package com.example.demo.demos.web.blog.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {//这里继承Dish是为了方便使用Dish的属性
    //菜品口味
    private List<DishFlavor> flavors = new ArrayList<>();//这里用来存储菜品口味

    //菜品分类名称
    private String categoryName;

    //菜品份量
    private Integer copies;
}
