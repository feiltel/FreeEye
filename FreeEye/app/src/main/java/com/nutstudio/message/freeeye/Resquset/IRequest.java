package com.nutstudio.message.freeeye.Resquset;

import java.util.Map;

/**
 * Created by fei on 2016/5/20.
 */
public interface IRequest {
    //处理最后一次网络请求（get）
    void getJsonObjRequest(String url, int whichCall);
    //处理所有的发出的请求
    void getJsonObjRequestNot(String url, int whichCall);
    void getJsonObjRequestPost(String url, int whichCall, Map<String, String> params);
    void cancelRequest(int tag);
}
