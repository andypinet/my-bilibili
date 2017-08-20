package com.example.lbc15.testcordova.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.lbc15.testcordova.utils.Logger;

/**
 * Created by lbc15 on 2017/8/20.
 */

public class BackTopBehavior extends FloatingActionButton.Behavior {
    public BackTopBehavior(Context context, AttributeSet attrs) {
        super();
    }
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;//垂直方向滑动
    }
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0 && dyUnconsumed == 0) {
            Logger.i("上滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed > 0) {
            Logger.i("到边界了还在上滑。。。");
        }
        if (dyConsumed < 0 && dyUnconsumed == 0) {
            Logger.i("下滑中。。。");
        }
        if (dyConsumed == 0 && dyUnconsumed < 0) {
            Logger.i("到边界了，还在下滑。。。");
        }
        if((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0) && child.getVisibility() != View.VISIBLE) {
            child.show();//上滑的时候显示按钮
        } else if((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed < 0) && child.getVisibility() != View.GONE) {
            child.hide();//下滑的时候因此按钮
        }
    }
}