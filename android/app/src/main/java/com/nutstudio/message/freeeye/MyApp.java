package com.nutstudio.message.freeeye;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by admin on 2016/1/14.
 * 转场动画，评论页分级，图片比例
 * TODO 转场动画
 * 书城页到详情页，
 * 精选页到详情页
 * TODO 评论页分级
 * TODO 全局页面比例调整>>>完成
 *
 */
public class MyApp extends Application {

    private static RequestQueue requestQueue;
    @Override
    public void onTerminate() {
        super.onTerminate();
        requestQueue.stop();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getHttpQueue() {
        return requestQueue;
    }
}
