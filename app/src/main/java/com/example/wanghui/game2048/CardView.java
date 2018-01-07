package com.example.wanghui.game2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by wanghui on 2018/1/7.
 */

public class CardView extends FrameLayout {
    private Context mContext;
    private int num = 0;

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
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.setMargins(10, 10, 10, 10);
        View view = new View(mContext);
        view.setBackgroundColor(0x883c3a32);
        view.setLayoutParams(params);
        addView(view);
    }
}
