package com.bitshare.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: ZYP
 * date: 2019/3/4.
 */

/**
 *  CONSTRUCTOR:构造方法之上
 *  FIELD:属性,枚举之上
 *  METHOD:方法之上
 *  LOCAL_VARIABLE:局部变量
 *  PACKAGE:包
 *  PARAMETER:方法参数之上
 *  TYPE:接口,类,枚举之上
 *  ANNOTATION_TYPE:元标签,作用在注解之上(注解的注解)
 */
@Target(ElementType.TYPE) //该注解作用在类上
//CLASS:编译期,编译时进行的预操作,存在于class文件,运行时就会丢失
//SOURCE:用于检查,存在于源码级别,运行时会丢失
//RUNTIME:在JVM加载的时候,通过反射获取注解的值
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    //int 类型的布局
    int value();
}
