package com.adwyxx.springaop;
/**
 * 模拟业务逻辑接口实现类
 * @author: Leo.Wang, adwyxx@qq.com
 */
public class UserService implements IUserService{
    @Override
    public void addUser() {
        System.out.println("UserService do something...");
    }
}