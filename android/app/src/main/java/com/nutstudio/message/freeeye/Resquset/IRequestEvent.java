package com.nutstudio.message.freeeye.Resquset;

import org.json.JSONObject;

public interface IRequestEvent {
    void responseSuccess(JSONObject jsonObject, int whichCall);
    void responseError(int whichCall);
}