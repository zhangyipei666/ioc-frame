package com.bitshare.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: ZYP
 * date: 2019/3/4.
 */
@Target(ElementType.ANNOTATION_TYPE)//该注解作用在其他注解之上
@Retention(RetentionPolicy.RUNTIME)
public  @interface EventBase {
    //1.监听的方法名setOnClickListener
    String  listenerSetter();

    //2.监听的对象View.OnClickListener
    Class<?> listenerType();

    //3.回调方法名onClick
    String callBackListener();
}
