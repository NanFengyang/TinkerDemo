package com.example.yyt.qugutestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yyt.qugutestapplication.utils.Utils;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toJSactivity(View view) {
        startActivity(new Intent(this, JSActivity.class));
    }

    //更新APK
    public void onClickUpdaterAPK(View view) {
        checkPermission();
        String path = getFilePath();
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
    }

    public String getFilePath() {
        File photo = null;
        String path = "/mnt/sdcard/yytdownload";
        photo = new File(path);
        // 判断目录是否存在
        if (!photo.exists()) {
            photo.mkdir();// 创建照片的存储目录
        }
        if (!photo.exists()) {
            return null;
        }
        String fileName = "";

        fileName = "qugu.apk";
         notifySystemToScan(path + "/" + fileName);
        return path + "/" + fileName;
    }

    public void notifySystemToScan(String filePath) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(filePath);

        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);
//这里是context.sendBroadcast(intent);

    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("ewe", "checkPermission: 已经授权！");
        }
    }

}
