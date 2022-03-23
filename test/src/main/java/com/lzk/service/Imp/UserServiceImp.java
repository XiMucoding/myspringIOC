package com.lzk.service.Imp;

import com.lzk.dao.Imp.UserDaoImp;
import com.lzk.dao.UserDao;
import com.lzk.service.UserService;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description <类说明>
 * @Date 15:43 2022/3/19
 **/
public class UserServiceImp implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void used() {
        System.out.println("调用逻辑层");
        userDao.sayHi();
    }
}
