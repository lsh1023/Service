package com.os.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by LSH on 2016/12/8.
 */

public class BackGroupService extends Service {
    /**
     * 綁定服务时调用
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service", "onBind");
        //返回代理对象
        return new MyBinder();
    }

    /**
     * 代理类
     */
    class MyBinder extends Binder {
        public void showToast() {
            Log.e("Service", "showToast");
        }

        public void showList() {
            Log.e("Service", "showList");
        }
    }

    /**
     * 解除绑定服务时调用
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service", "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 服务创建时调用
     */
    @Override
    public void onCreate() {
        Log.e("Service", "onCreate");
        super.onCreate();
    }

    /**
     * 执行startService时调用
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "onStartCommand");
        //执行耗时操作
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Log.e("Service", "doSomething");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务被销毁时调用
     */
    @Override
    public void onDestroy() {
        Log.e("Service", "onDestroy");
        super.onDestroy();
    }
}
