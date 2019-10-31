package com.adwyxx.springaop;

/**
 * 静态代理 示例
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/31 10:56
 * 什么是静态代理？
 * 简单来说，就是把被代理类作为参数传给代理类的构造方法，让代理类替被代理类实现更强大的功能。
 * 实现：
 * 1. 代理类和被代理类应该实现相同的接口
 * 2. 代理类的构造函数应该接收一个被代理类的实例对象
 * 3. 代理类在实现接口的方法时 可以在调用【被代理类实例对象对应的方法】 前后 实现自己的逻辑，增强业务逻辑
 */
public class StaticeProxyDemo {

    public static void main(String[] args) {
        IUserService userService = new UserService();
        LogProxy proxy = new LogProxy(userService);
        proxy.addUser();
    }

}

//静态代理实现
class LogProxy implements IUserService{
    //被代理类的实例对象
    private IUserService target;
    //将实际被代理的类作为参数传入
    public LogProxy(IUserService service){
        this.target = service;
    }

    //被代理类方法执行前执行
    private void before(){
        System.out.println("--------before--------");
    }

    //被代理类方法执行后执行
    private void after(){
        System.out.println("--------after--------");
    }

    //代理方法实现，增强被代理类
    @Override
    public void addUser() {
        this.before();
        this.target.addUser();
        this.after();
    }
}