package com.nutstudio.message.freeeye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstudio.message.freeeye.base.NetFragment;
import com.nutstudio.message.freeeye.component.Api;
import com.nutstudio.message.freeeye.component.MyListAdapter;
import com.nutstudio.message.freeeye.component.ListItemBean;
import com.nutstudio.message.freeeye.content.ContentActivity;
import com.nutstudio.message.freeeye.util.DateFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;


public class BaseShowFragment extends NetFragment {
    private RecyclerView rootView;
    private MyListAdapter adapter;
    private List<ListItemBean> dataList;

    public static BaseShowFragment newInstance(String url) {
        BaseShowFragment fragment = new BaseShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        iRequest = getRequest();
        rootView = new RecyclerView(getActivity());
        dataList = new ArrayList<>();
        adapter = new MyListAdapter(getActivity(), dataList);
        rootView.setHasFixedSize(true);
        rootView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rootView.setItemAnimator(new DefaultItemAnimator());
        rootView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String shareImgUrl ) {
                Intent intent=new Intent();
                intent.putExtra("img_url",shareImgUrl);
                intent.setClass(getActivity(), ContentActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, String groupId) {

            }
        });
        getData();
        return rootView;
    }

    private void getData() {
        iRequest.getJsonObjRequest(Api.DOUBAN_MOMENT+new DateFormatter().DoubanDateFormat(Calendar.getInstance().getTimeInMillis()), 121);
    }
    @Override
    public void responseSuccess(JSONObject jsonObject, int whichCall) {
        if (whichCall == 121) {
            try {
                JSONArray array = jsonObject.getJSONArray("posts");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item=array.getJSONObject(i);
                    ListItemBean bean=new ListItemBean();
                    bean.setTitle(item.getString("title"));
                    bean.setIconUrl("https://img3.doubanio.com/view/presto/medium/public/t119620.jpg");
                    bean.setShareImgUrl(item.getString("share_pic_url"));
                    dataList.add(bean);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
