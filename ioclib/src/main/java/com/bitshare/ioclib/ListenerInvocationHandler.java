package com.bitshare.ioclib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * author: ZYP
 * date: 2019/3/4.
 */
//拦截本应该执行的回调方法,而去执行我们自定义的方法
public class ListenerInvocationHandler implements InvocationHandler {
    //拦截的目标 比如需要拦截的MainActivity中的方法
    private Object target;
    //需要拦截的对象 键值对
    private HashMap<String,Method> methodHashMap=new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(target!=null){
            String methodName = method.getName();
            method = methodHashMap.get(methodName);
            if(method!=null){
                return   method.invoke(target,args);
            }
        }
        return null;
    }

    /**
     *  将要添加拦截的方法
     * @param methodName 拦截的方法 如 onClick()
     * @param method 执行的方法 sssa();自定义的
     */
    public void addMethod(String methodName,Method method){
        methodHashMap.put(methodName,method);
    }
}
