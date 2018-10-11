package com.example.yyt.qugutestapplication;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AndroidJs {
    private Context mContext;
    private WebView mWebView;

    public AndroidJs(Context context, WebView webView) {
        this.mContext = context;
        this.mWebView = webView;
    }

    /**
     *
     * js调用的方法
     * JavascriptInterface 必须添加，否则在4.2以上的版本无效
     */
    @JavascriptInterface
    public void getToken() {
        Toast.makeText(mContext, "获取token", Toast.LENGTH_LONG).show();
        if (mWebView != null) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("token", System.currentTimeMillis()/1000);
                        //getAppToken为js中定义接受Android数据的方法
                        mWebView.loadUrl("javascript:getAppToken(" + json.toString() + ")");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
