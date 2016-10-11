package com.nutstudio.message.freeeye.base;/*
*Created by admin on 2016/9/6.
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.nutstudio.message.freeeye.Resquset.IRequest;
import com.nutstudio.message.freeeye.Resquset.IRequestEvent;
import com.nutstudio.message.freeeye.Resquset.RequestController;

import org.json.JSONObject;

public class NetActivity extends AppCompatActivity implements IRequestEvent {
    public IRequest iRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        iRequest=new RequestController(this);
        super.onCreate(savedInstanceState);
    }
    public IRequest getRequest(){
        iRequest=new RequestController(this);
        return iRequest;
    }
    @Override
    public void responseSuccess(JSONObject jsonObject, int whichCall) {

    }

    @Override
    public void responseError(int whichCall) {

    }
}
