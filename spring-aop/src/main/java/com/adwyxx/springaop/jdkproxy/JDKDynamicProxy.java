package com.adwyxx.springaop.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 切面逻辑类
 * JKD 动态代理实现demo，要实现 {@InvocationHandler} 接口
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/31 14:47
 */
public class JDKDynamicProxy implements InvocationHandler {

    private  Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    //代理类通过反射Method.invoke() 方法调用被代理类的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before();
        Object obj = method.invoke(this.target,args);
        this.after();
        return obj;
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }

    public static void main(String[] args) {
        //testProxy();
        generateProxy();
    }

    public static void testProxy(){
        //1.初始化被代理类的实例
        IUserService service = new UserService();
        //2.初始化动态代理 切面 实例
        JDKDynamicProxy aop = new JDKDynamicProxy(service);
        Class<?> clazz = IUserService.class;

        //3.Proxy为JDKDynamicProxy动态创建一个符合某一接口的代理实例
        //第一个参数:获取被代理类的class loader
        //第二个参数:获取被代理类实现的接口, 由此可见被代理类需要实现统一的接口
        //第三个参数:InvocationHandler的实例, 也就是我们做切面逻辑的类
        IUserService proxy = (IUserService) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[] {clazz},
                aop);
        //动态代理实例条用被代理类的方法
        proxy.addUser();
    }


    /**
     * 生成代理类Class
     * @author: Leo.Wang, adwyxx@qq.com
     */
    public static void generateProxy(){
      byte[] bytes = ProxyGenerator.generateProxyClass("com.adwyxx.springaop.UserProxy",new Class<?>[]{IUserService.class},17);
      saveFile("UserProxy.class",bytes);
    }

    public static void saveFile(String filename,byte [] data){
        if(data != null){
            String filepath ="D:\\" + filename;
            File file  = new File(filepath);
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data,0,data.length);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
            }

        }
    }
}
