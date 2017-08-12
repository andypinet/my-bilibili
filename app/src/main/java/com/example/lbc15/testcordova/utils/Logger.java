package com.example.lbc15.testcordova.utils;

import android.util.Log;

import com.example.lbc15.testcordova.Config;

/**
 * Created by lbc15 on 2017/8/12.
 */

public class Logger {
    
    private static String prefix = Config.LOGGER_PERFIX;
    
    public static void i(String str) {
        Log.i(Logger.prefix, str);
    }
}
