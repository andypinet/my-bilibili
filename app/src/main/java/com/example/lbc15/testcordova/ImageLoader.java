package com.example.lbc15.testcordova;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lbc15.testcordova.utils.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lbc15 on 2017/8/19.
 */

public class ImageLoader {

    //加载图片
    public static Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            Logger.i("load image ready");
            is.close();
        } catch (Exception e) {
            Logger.i("load image failed " + e);
            e.printStackTrace();
        }
        return bmp;
    }
}
