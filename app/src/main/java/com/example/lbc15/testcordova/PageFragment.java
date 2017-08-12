package com.example.lbc15.testcordova;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lbc15.testcordova.components.AnuWebview;
import com.example.lbc15.testcordova.utils.Logger;

/**
 * Created by lbc15 on 2017/8/12.
 */

public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private AnuWebview webView;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
//        第一页 mPage = 1
        if (mPage == 1) {
            view = inflater.inflate(R.layout.fragment_page,container,false);
            webView = (AnuWebview) view.findViewById(R.id.mainwebview);
            webView.loadUrl("https://www.zhilizhili.com");
        } else {
            view = inflater.inflate(R.layout.fragment_native,container,false);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText("Native 第"+mPage+"页");
        }
        return view;
    }
}
