package com.tin.chigua.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tin.chigua.tcpsocket.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hasee on 6/22/2017.
 */

public class TestActivity extends Activity {

    private static final String TAG = "TestActivity";

    private MyService.TestBinder mBinder;
    MyService myService;
    @BindView(R.id.bind_service)
    Button mBindService;
    @BindView(R.id.unbind_service)
    Button mUnbindService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            Myservice = ((MyService.TestBinder)service).getService();
            myService = ((MyService.TestBinder) service).getService();
            mBinder = (MyService.TestBinder) service;
            mBinder.startDownLoad();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bind_service,R.id.unbind_service})
    public void OnClcik(View v){
        switch (v.getId()){
            case R.id.bind_service:
                Intent mIntent = new Intent(this, MyService.class);
                bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
                Log.w(TAG,"点击了启动并绑定服务按钮》》》》》》》》》》");
                break;
            case R.id.unbind_service:
                Log.w(TAG,"点击了解除绑定服务按钮》》》》》》》》》》》》》》》");
                unbindService(mServiceConnection);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"调用了Activity的onDestroy方法》》》》》》》》》》");
    }
}
