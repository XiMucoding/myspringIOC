package com.lzk.controller;

import com.lzk.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import ioc.lzk.context.ApplicationContext;
//import ioc.lzk.context.support.ClassPathXmlApplicationContext;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description <类说明>
 * @Date 15:46 2022/3/19
 **/
public class UserController {
    public static void main(String[] args) throws Exception {
        //创建spring容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //从IOC容器中获取UserService对象
        UserService userService = applicationContext.getBean("userService", UserService.class);
        //调用UserService对象的used方法
        userService.used();
    }
}
