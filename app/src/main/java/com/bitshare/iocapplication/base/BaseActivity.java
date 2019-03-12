package com.bitshare.iocapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bitshare.ioclib.InjectManager;

/**
 * author: ZYP
 * date: 2019/3/4.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //帮助子类进行:布局 控件 事件的注入
        InjectManager.inject(this);
    }
}
