package com.example.yyt.qugutestapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * 补丁重启服务进程
 */
public class PatchRestartService extends Service {
    private Handler handler;
    private static long stopDelayed = 2000;//两秒后重启APP

    public PatchRestartService() {
        handler = new Handler();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String PackageName = intent.getStringExtra("PackageName");//获取参数，需要重启的包名
        Toast.makeText(this, "补丁更新成功，重启中，请稍等...", Toast.LENGTH_LONG).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                startActivity(LaunchIntent);//重启
                PatchRestartService.this.stopSelf();//停止当前服务
            }
        }, stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }
}
