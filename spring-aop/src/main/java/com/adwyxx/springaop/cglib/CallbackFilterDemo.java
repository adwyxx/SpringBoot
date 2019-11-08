package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * {@link CallbackFilter} 过滤代理类的方法，可以指定逻辑：根据不同的方法执行不同的Callback拦截处理
 * 作用:
 *  在CGLib回调时可以设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
 *  在JDK动态代理中并没有类似的功能，对InvocationHandler接口方法的调用对代理类内的所以方法都有效。
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/8 11:09
 */
public class CallbackFilterDemo {

    public String getName(String name){
        System.out.println("getName() invoked:"+name);
        return name;
    }

    public int getAge(){
        System.out.println("getAge() invoked:"+18);
        return 18;
    }

    public static void main(String[] args) {
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //2.设置被代理的类
        enhancer.setSuperclass(CallbackFilterDemo.class);
        //3.创建CallbackFilter相应的 Callback 对象数组
        Callback[] callbacks = new Callback[]{
                NoOp.INSTANCE, //NoOp表示No Operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
                new DefaultFixedValue(), //表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
                new DefaultMethodInterceptor() //对被代理类的方法进行增强，before-after
        };
        //4.设置Callback处理
        enhancer.setCallbacks(callbacks);
        //5.设置CallbackFilter
        enhancer.setCallbackFilter(new DefaultCallbackFilter());
        //6.创建代理对象
        CallbackFilterDemo poxy = (CallbackFilterDemo)enhancer.create();
        //7.执行代理对象方法
        System.out.println(poxy.getName("张三")); //被FixcedValue拦截，返回Fixed Value
        poxy.getAge(); //被MethodInterceptor拦截
        System.out.println(poxy.toString()); //NoOp
    }
}
//自定义方法拦截器
class DefaultMethodInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before...");
        proxy.invokeSuper(obj,args);
        System.out.println("after...");
        return null;
    }
}
//FixedValue接口:表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
class DefaultFixedValue implements FixedValue{

    //锁定返回值为"Fixed Value"，注意被代理的方法返回值类型必须与该方法返回值类型一致
    @Override
    public Object loadObject() throws Exception {
        return "Fixed Value";
    }
}
//CallbackFilter接口:回调方法过滤
class DefaultCallbackFilter implements CallbackFilter{
    //过滤方法,返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
    @Override
    public int accept(Method method) {
        if(method.getName().equals("getName")){
            return 1;
        } else if(method.getName().equals("getAge")){
            return 2;
        }
        return 0;
    }
}



