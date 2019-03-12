package com.bitshare.ioclib;

import android.app.Activity;
import android.view.View;

import com.bitshare.ioclib.annotations.ContentView;
import com.bitshare.ioclib.annotations.EventBase;
import com.bitshare.ioclib.annotations.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: ZYP
 * date: 2019/3/4.
 * desc: 注入管理类
 */

public class InjectManager {
    public static void inject(Activity activity) {
        //布局注入
        injectLayout(activity);

        //控件注入
        injectViews(activity);

        //事件注入
        injectEvents(activity);
    }

    private static void injectLayout(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //获取layoutId
            int layoutId = contentView.value();

            //方式一
//            activity.setContentView(layoutId);
            //方式二 反射的技术
            //获取指定的方法
            try {
                //第二个参数 该方法参数的类型(因为可能会有同名不同参数的多个方法)
                Method method = clazz.getMethod("setContentView", int.class);
                //执行方法
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //拿到所有属性
        Field[] fields = clazz.getDeclaredFields();
        //遍历拿到所有属性的注解
        for (Field field : fields) {
            //拿到每个注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                //拿到每个注解的值 R.id.xx
                int viewId = injectView.value();
                //方式一
//                View view = activity.findViewById(viewId);
//                try {
//                    field.set(activity,view);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                //方式二
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, viewId);
                    //设置访问权限 可以访问private
                    field.setAccessible(true);
                    //属性赋值
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void injectEvents(Activity activity) {
        //获取类
        Class<? extends Activity> clazz = activity.getClass();
        //获取该类所有的方法 包括私有
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取该方法的所有注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        String listenerSetter = eventBase.listenerSetter();
                        Class<?> listenerType = eventBase.listenerType();
                        String callBackListener = eventBase.callBackListener();
                        //通过代理的方式,操作这个对象,并且中间拦截OnClick方法,执行自定义的方法
                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethod(callBackListener,method);
                        //1.监听类型 类加载器,2,目前对象需要实现的一个接口类型
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
                        try {
                            //通过annotationType获取onClick注解的value值
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            //执行value的方法获取注解的值
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);
                            for (int viewId : viewIds) {
                                View view = activity.findViewById(viewId);
                                Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                setter.invoke(view,listener);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
