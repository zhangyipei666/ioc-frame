package com.bitshare.ioclib.annotations;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: ZYP
 * date: 2019/3/4.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class,callBackListener = "onClick")
public @interface OnClick {
    int[] value();
}
