package com.nutstudio.message.freeeye.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/10/6.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> set;
    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> set) {
        super(fm);
        this.set=set;
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return set.get(position);
    }
    @Override
    public int getCount() {
        return set.size();
    }
}
