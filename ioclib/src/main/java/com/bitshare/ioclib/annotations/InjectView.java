package com.bitshare.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: ZYP
 * date: 2019/3/4.
 */
@Target(ElementType.FIELD)//属性之上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    //int类型的控件(R.id.xx)
    int value();
}
