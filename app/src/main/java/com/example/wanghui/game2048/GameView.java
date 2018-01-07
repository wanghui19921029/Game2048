package com.example.wanghui.game2048;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by wanghui on 2018/1/7.
 */

public class GameView extends LinearLayout {
    private int mWidth = 0;
    private Context mContext;
    private WindowManager mWindowManager;
    private float startX, startY, offsetX, offsetY;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(VERTICAL);
        init();
    }

    private void init() {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        addCards(mWidth / 4);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        break;
                }
                return true;
            }
        });
    }

    private void addCards(int width) {
        CardView cardView;
        LinearLayout line;
        LayoutParams layoutParams;
        for (int y = 0; y < 4; y++) {
            line = new LinearLayout(getContext());
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width);
            addView(line, layoutParams);

            for (int x = 0; x < 4; x++) {
                cardView = new CardView(getContext());
                line.addView(cardView, width, width);
            }
        }
    }
}
