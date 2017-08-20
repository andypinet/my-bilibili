package com.example.lbc15.testcordova;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lbc15 on 2017/8/13.
 */

public interface PageFragmenManagerListener {
    public void onCreateView(Boolean isFirstInit, int Position, View view, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
