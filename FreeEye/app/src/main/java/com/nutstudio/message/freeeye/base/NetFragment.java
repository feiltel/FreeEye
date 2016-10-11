package com.nutstudio.message.freeeye.base;/*
*Created by admin on 2016/9/6.
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstudio.message.freeeye.Resquset.IRequest;
import com.nutstudio.message.freeeye.Resquset.IRequestEvent;
import com.nutstudio.message.freeeye.Resquset.RequestController;

import org.json.JSONObject;

public class NetFragment extends Fragment implements IRequestEvent {
    public IRequest iRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
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
