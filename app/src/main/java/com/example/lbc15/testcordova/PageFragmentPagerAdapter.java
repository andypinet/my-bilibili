package com.example.lbc15.testcordova;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.lbc15.testcordova.utils.Logger;

/**
 * Created by lbc15 on 2017/8/12.
 */


public class PageFragmentPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 3;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3"};
    private Context context;
    private Boolean isFirstInit = false;
    private PageFragmentPagerAdapterListener listener;
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public PageFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        if (listener != null) {
            listener.onInstantiateItem(container, position, isFirstInit);
        }
        if (!isFirstInit) {
            isFirstInit = true;
        }
        return fragment;
    }

    public void setPageFragmentPagerAdapterListener(PageFragmentPagerAdapterListener listener) {
        this.listener = listener;
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
