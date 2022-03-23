package com.lzk.dao.Imp;

import com.lzk.dao.UserDao;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description <类说明>
 * @Date 15:41 2022/3/19
 **/
public class UserDaoImp implements UserDao {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void sayHi() {
        System.out.println(username+" "+password);
        System.out.println("调用数据库层...");
    }
}
