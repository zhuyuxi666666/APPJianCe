package com.demo.recognition.face.appjiance;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isApplicationAvilible(getApplicationContext(), "com.example.trafficmanager3");
    }

    public boolean isApplicationAvilible(Context context, String appPackageName) {
        PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (appPackageName.equals(pn)) {
                    Toast.makeText(MainActivity.this, "已安装路路通！", Toast.LENGTH_SHORT).show();
                    Intent intent = packageManager.getLaunchIntentForPackage(appPackageName);
                    startActivity(intent);
                    return true;
                }
            }
        }
        Toast.makeText(MainActivity.this, "未安装路路通", Toast.LENGTH_SHORT).show();
        // 直接从指定网址下载
        Uri uri = Uri.parse("http://sj.qq.com/myapp/detail.htm?apkName=com.example.trafficmanager3");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
        return false;
    }
}
