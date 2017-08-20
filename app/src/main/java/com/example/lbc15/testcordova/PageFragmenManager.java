package com.example.lbc15.testcordova;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lbc15.testcordova.R;
import com.example.lbc15.testcordova.utils.Logger;

/**
 * Created by lbc15 on 2017/8/13.
 */

public class PageFragmenManager {
    public static PageFragmenManagerListener listerner = null;
    public static Boolean isFirstInit = false;
    public static void onCreateView(int Position, View view, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listerner.onCreateView(isFirstInit, Position, view, inflater, container, savedInstanceState);
        if (!isFirstInit) {
            isFirstInit = true;
        }
    }
}
