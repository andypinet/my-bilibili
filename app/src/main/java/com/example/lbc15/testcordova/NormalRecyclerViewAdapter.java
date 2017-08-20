package com.example.lbc15.testcordova;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lbc15.testcordova.utils.Logger;

import java.util.HashMap;

/**
 * Created by lbc15 on 2017/8/15.
 */

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    HashMap h = (HashMap) msg.obj;
                    NormalTextViewHolder holder = (NormalTextViewHolder) h.get("holder");
                    Bitmap bmp = (Bitmap) h.get("bmp");
                    holder.mImageView.setImageBitmap(bmp);
                    break;
            }
        };
    };

    @Override
    public void onBindViewHolder(final NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position] + "1");

        // 获取图片
        new Thread(new Runnable() {

            @Override
            public void run() {
            HashMap<String, Object> h = new HashMap();
            Bitmap bmp = ImageLoader.getURLimage("http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a");
            h.put("bmp", bmp);
            h.put("holder", holder);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = h;
            handle.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        NormalTextViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.main_card_title);
            mImageView = (ImageView) view.findViewById(R.id.main_card_avatar);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Logger.i("NormalTextViewHolder onClick--> position = " + getPosition());
                }
            });
        }
    }
}
