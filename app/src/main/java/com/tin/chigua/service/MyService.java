package com.tin.chigua.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hasee on 7/7/2017.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    private TestBinder mBinder = new TestBinder();
    boolean threadDisable;
    int count;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w(TAG,"调用了Service的onBind方法获得一个Binder对象》》》》》》》》》》");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG,"调用了Service的onCreate方法》》》》》》》》》》");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!threadDisable){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }).start();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.w(TAG,"调用了Service的onStart方法》》》》》》》》》》");
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.w(TAG,"调用了Service的unbindService方法》》》》》》》》》》");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"调用了Service的onDestroy方法》》》》》》》》》》");
    }

    public int getCount(){
        return count;
    }

    class TestBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }

        public void startDownLoad(){
            Log.w(TAG,"调用了Service的startDownLoad方法》》》》》》》》》》");
        }
    }

}
