package com.example.wanghui.game2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by wanghui on 2018/1/7.
 */

public class CardView extends FrameLayout {
    private Context mContext;
    private int num = 0;
    private TextView mTextView;

    public CardView(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            mTextView.setText("");
        } else {
            mTextView.setText(num + "");
        }

        switch (num) {
            case 0:
                mTextView.setBackgroundColor(0x183c3a32);
                break;
            case 2:
                mTextView.setBackgroundColor(0xffeee4da);
                break;
            case 4:
                mTextView.setBackgroundColor(0xffede0c8);
                break;
            case 8:
                mTextView.setBackgroundColor(0xfff2b179);
                break;
            case 16:
                mTextView.setBackgroundColor(0xfff59563);
                break;
            case 32:
                mTextView.setBackgroundColor(0xfff67c5f);
                break;
            case 64:
                mTextView.setBackgroundColor(0xfff65e3b);
                break;
            case 128:
                mTextView.setBackgroundColor(0xffedcf72);
                break;
            case 256:
                mTextView.setBackgroundColor(0xffedcc61);
                break;
            case 512:
                mTextView.setBackgroundColor(0xffedc850);
                break;
            case 1024:
                mTextView.setBackgroundColor(0xffedc53f);
                break;
            case 2048:
                mTextView.setBackgroundColor(0xffedc22e);
                break;
            default:
                mTextView.setBackgroundColor(0xff3c3a32);
                break;

        }
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.setMargins(10, 10, 10, 10);
        mTextView = new TextView(mContext);
        mTextView.setTextSize(40);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setLayoutParams(params);
        addView(mTextView);
    }

    public boolean equals(CardView cardView) {
        return cardView.getNum() == getNum();
    }
}
