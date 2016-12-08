package com.os.service;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mClose,mOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClose= (Button) findViewById(R.id.close);
        mOpen= (Button) findViewById(R.id.open);

        final Intent intent = new Intent(this, BackGroupService.class);

        //启动后台服务
        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });
        //启动后台服务
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
        //启动前台服务
        startService(new Intent(this, ForegroundService.class));
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到后台服务代理对象
            BackGroupService.MyBinder myBinder = (BackGroupService.MyBinder) service;
            //调用后台服务的方法
            myBinder.showToast();
            myBinder.showList();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public void isWifi() {
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        boolean enabled = wm.isWifiEnabled();
    }

    public void getMaxAudio() {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);

        int current = am.getStreamMaxVolume(AudioManager.STREAM_RING);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void isConnect() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        boolean isAvailable = info.isAvailable();
    }


}
