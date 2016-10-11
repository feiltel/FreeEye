package com.nutstudio.message.freeeye.Resquset;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.nutstudio.message.freeeye.MyApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by fei on 2016/5/19.
 */
public class RequestController implements IRequest {
    private String TAG = "error";
    private IRequestEvent iRequestEvent;

    public RequestController(IRequestEvent iRequestEvent) {
        this.iRequestEvent = iRequestEvent;
    }

    /**
     * 处理最后一次网络请求（get），防止一直请求后台，减轻后台请求的压力
     * 同时快速点击节点不会出现两个都请求回来显示
     *
     *
     * MyApp获得队列清除所有的请求（whicall）
     * HttpsTrustManager.allowAllSSL();http转换为https
     * 实例化JsonObjectRequest（GET，url，null，实例化响应监听接口（iRequestEvent调用响应成功接口（JSONObject，whichCall），
     *                                         实例化响应错误监听接口（iRequestEvent调用响应错误接口（whichCall），设置错误类型（volleyError）））
     * request设置标签（which）
     * MyAPP获得队列添加请求
     * @param url
     * @param whichCall
     */
    @Override
    public void getJsonObjRequest(String url, final int whichCall) {
        MyApp.getHttpQueue().cancelAll(whichCall);
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {
                        iRequestEvent.responseSuccess(s, whichCall);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        iRequestEvent.responseError(whichCall);
                        setErrorType(volleyError);
                    }
                });
        request.setTag(whichCall);
        MyApp.getHttpQueue().add(request);
    }

    @Override
    public void getJsonObjRequestNot(String url, final int whichCall) {
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {
                        iRequestEvent.responseSuccess(s, whichCall);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        iRequestEvent.responseError(whichCall);
                        setErrorType(volleyError);
                    }
                });
        request.setTag(whichCall);
        MyApp.getHttpQueue().add(request);
    }
    @Override
    public void getJsonObjRequestPost(String url, final int whichCall, final Map<String, String> mParams) {
        MyApp.getHttpQueue().cancelAll(whichCall);
        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Log.d("aaa",s);
                            JSONObject jsonObject =new JSONObject(s);
                            iRequestEvent.responseSuccess(jsonObject, whichCall);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iRequestEvent.responseError(whichCall);
            }
        }) {
            @Override
            protected Map getParams() {
                return mParams;
            }
        };
        request.setTag(whichCall);
        MyApp.getHttpQueue().add(request);
    }

    @Override
    public void cancelRequest(int tag) {
        MyApp.getHttpQueue().cancelAll(tag);
    }

    private void setErrorType(VolleyError volleyError) {
        Class mClass = volleyError.getClass();
        if (mClass == com.android.volley.AuthFailureError.class) {
            Log.d(TAG, "AuthFailureError");
        } else if (mClass == com.android.volley.NetworkError.class) {
            Log.d(TAG, "NetworkError");
        } else if (mClass == com.android.volley.NoConnectionError.class) {
            Log.d(TAG, "NoConnectionError");
        } else if (mClass == com.android.volley.ServerError.class) {
            Log.d(TAG, "ServerError");
        } else if (mClass == com.android.volley.TimeoutError.class) {
            Log.d(TAG, "TimeoutError");
        } else if (mClass == com.android.volley.ParseError.class) {
            //在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
            Log.d(TAG, "ParseError");
        } else if (mClass == VolleyError.class) {
            Log.d(TAG, "General error");
        }
    }
}
