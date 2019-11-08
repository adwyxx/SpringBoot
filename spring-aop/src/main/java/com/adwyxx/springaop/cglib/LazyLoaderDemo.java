package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

/**
 * 延迟加载 {@link LazyLoader}
 * 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化
 * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，就会自动触发代理类回调）
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/8 14:21
 */
public class LazyLoaderDemo {
    //被懒加载的属性
    private UserBean userLazyLoader;
    private UserBean userDispatcher;

    public LazyLoaderDemo(){
        this.userLazyLoader = createUserBean();
        this.userDispatcher = createUserBeanDispatcher();
    }
    /**
     * 使用{@link LazyLoader}实现延迟加载
     * 特点：只在第一次调用使用懒加载
     */
    private UserBean createUserBean(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserBean.class);
        //设置UserBean对象实例化的代理
        UserBean bean = (UserBean)enhancer.create(UserBean.class,new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("before LazyLoader...");
                UserBean bean = new UserBean(1,"LazyLoader");
                System.out.println("after LazyLoader...");
                return bean;
            }
        });
        return bean;
    }

    /**
     * 使用{@link Dispatcher}实现延迟加载
     * 特点：每次调用都懒加载
     */
    private UserBean createUserBeanDispatcher(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserBean.class);
        //设置UserBean对象实例化的代理
        UserBean bean = (UserBean)enhancer.create(UserBean.class,new Dispatcher() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("before Dispatcher...");
                UserBean bean = new UserBean(2,"Dispatcher");
                System.out.println("after Dispatcher...");
                return bean;
            }
        });
        return bean;
    }

    public static void main(String[] args) {
        LazyLoaderDemo demo = new LazyLoaderDemo();
        System.out.println(demo);

        LazyLoaderDemo demo1 = new LazyLoaderDemo();
        System.out.println(demo1);
    }

    @Override
    public String toString() {
        return "userLazyLoader = " + userLazyLoader.toString() +"\n"+
                "userDispatcher = " + userDispatcher.toString() +"\n"+
                this.getClass();
    }
}

//UserBean，延迟加载对象
class UserBean{
    private int id;
    private String name;

    public UserBean() {
    }

    public UserBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id:"+this.id+",name:"+this.name+"}" + this.getClass();
    }
}
