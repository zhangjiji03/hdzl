package com.zz.service.impl;

/**
 * 项目名称:     hdzl
 * 类名称:       Tests
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2020/12/29 10:37
 * 版本:         1.0
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.createUser();
        System.out.println("11");
    }

    public void createUser(){
        int i = 0;
        while(true){
            User user = new User();
            user.setId(i);
            user.setName("张三");
            user.setAge(18);
            i++;
            System.out.println(user.toString());
        }
    }
}


