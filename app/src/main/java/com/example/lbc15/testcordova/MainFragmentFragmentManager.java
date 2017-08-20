package com.example.lbc15.testcordova;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.lbc15.testcordova.components.AnuWebview;

/**
 * Created by lbc15 on 2017/8/13.
 */

public class MainFragmentFragmentManager {
    public static int page = -1;

    public static void initWebviewPage(int position, View view, Context context) {
        AnuWebview webView = (AnuWebview) view.findViewById(R.id.mainwebview);
        webView.initPageSelected();
    }

    public static void initNativePage(int position, View view, Context context) {
        page = position + 1;
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("Native 第"+ page +"页");
    }

    public static void initMainPage(int position, View view, Context context) {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        final RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));//这里用线性宫格显示 类似于grid view
        mRecyclerView.setLayoutManager(layoutManager);//瀑布流 这里用线性宫格显示
        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(context));

        FloatingActionButton mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.smoothScrollToPosition(mRecyclerView, null , 0);
            }
        });
    }

    public static void initView(int position, View view, Context context) {
        if (position == 0) {
            initWebviewPage(position, view, context);
        } else if (position == 1) {
            initMainPage(position, view, context);
        } else {
            initNativePage(position, view, context);
        }
    }


}
