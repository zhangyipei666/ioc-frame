package com.bitshare.iocapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bitshare.iocapplication.base.BaseActivity;
import com.bitshare.ioclib.annotations.ContentView;
import com.bitshare.ioclib.annotations.InjectView;
import com.bitshare.ioclib.annotations.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.btn1)
    private Button btn1;
    @InjectView(R.id.btn2)
    private Button btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void xxxx(View view){
        switch (view.getId()){
            case R.id.btn1:
                Toast.makeText(this,"点击了btn1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this,"点击了btn2",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
