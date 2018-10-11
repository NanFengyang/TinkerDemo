package com.example.yyt.qugutestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        initWebView();
    }

    private void initWebView() {
        final WebView webView = findViewById(R.id.wbview);
        // 获取WebSetting对象
        WebSettings webSettings = webView.getSettings();
       // 设置支持javascript
        webSettings.setJavaScriptEnabled(true);
       // 将Android里面定义的类对象AndroidJs暴露给javascript  其中：android，为js中调用的对象映射Android的AndroidJs类，
        webView.addJavascriptInterface(new AndroidJs(JSActivity.this,  webView), "android");
        webView.loadUrl("file:///android_asset/test.html");
        webView.setWebViewClient(new WebViewClient());
    }
}
