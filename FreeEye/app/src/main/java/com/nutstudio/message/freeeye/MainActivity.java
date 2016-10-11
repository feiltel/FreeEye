package com.nutstudio.message.freeeye;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.nutstudio.message.freeeye.base.NetActivity;
import com.nutstudio.message.freeeye.component.Api;
import com.nutstudio.message.freeeye.component.MainBean;
import com.nutstudio.message.freeeye.component.MyViewPagerAdapter;
import com.nutstudio.message.freeeye.util.DateFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends NetActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @Bind(R.id.main_pager)
    ViewPager mainPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.root)
    LinearLayout root;
    private List<Fragment> fragmentSet;
    private List<MainBean> listSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iRequest = getRequest();
        listSet = new ArrayList<>();
        initListSet();
        fragmentSet = new ArrayList<>();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSystemUi();
    }

    private void initListSet() {
        for (int i = 0; i < 55; i++) {
            MainBean bean = new MainBean();
            bean.setTitle("标题" + i);
            bean.setMainUrl("url" + i);
            listSet.add(bean);
        }
    }

    private void initSystemUi() {
        for (int i = 0; i < listSet.size(); i++) {
            MainBean listBean = listSet.get(i);
            fragmentSet.add(BaseShowFragment.newInstance(listBean.getMainUrl()));
            mainTabLayout.addTab(mainTabLayout.newTab().setText(listBean.getTitle()));
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mainPager.setCurrentItem(0);
        mainPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentSet));
        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mainTabLayout);
        mainPager.addOnPageChangeListener(listener);
    }
}
