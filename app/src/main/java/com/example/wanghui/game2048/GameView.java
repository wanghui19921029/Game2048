package com.example.wanghui.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by wanghui on 2018/1/7.
 */

public class GameView extends LinearLayout {
    private String TAG = "whwhwhwwhhwhw";
    private int mWidth = 0;
    private Context mContext;
    private WindowManager mWindowManager;
    private float startX, startY, offsetX, offsetY;
    private CardView[][] mCardMap = new CardView[4][4];
    private ArrayList<Points> mList = new ArrayList<Points>();

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
        startGame();
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
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
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
                mCardMap[x][y] = cardView;
            }
        }
    }

    private void startGame() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                mCardMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum() {
        mList.clear();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (mCardMap[x][y].getNum() <= 0) {
                    mList.add(new Points(x, y));
                }
            }
        }
        if (mList.size() > 0) {
            int index = (int) (Math.random() * mList.size());
            Points points = mList.get(index);
            mCardMap[points.getX()][points.getY()].setNum(Math.random() > 0.1 ? 2 : 4);
        }
    }

    private void swipeLeft() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (mCardMap[x1][y].getNum() > 0) {
                        if (mCardMap[x][y].getNum() <= 0) {
                            mCardMap[x][y].setNum(mCardMap[x1][y].getNum());
                            mCardMap[x1][y].setNum(0);
                            x--;
                            merge = true;
                        } else if (mCardMap[x][y].equals(mCardMap[x1][y])) {
                            mCardMap[x][y].setNum(mCardMap[x][y].getNum() * 2);
                            mCardMap[x1][y].setNum(0);
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeRight() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 4 - 1; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (mCardMap[x1][y].getNum() > 0) {
                        if (mCardMap[x][y].getNum() <= 0) {
                            mCardMap[x][y].setNum(mCardMap[x1][y].getNum());
                            mCardMap[x1][y].setNum(0);
                            x++;
                            merge = true;
                        } else if (mCardMap[x][y].equals(mCardMap[x1][y])) {
                            mCardMap[x][y].setNum(mCardMap[x][y].getNum() * 2);
                            mCardMap[x1][y].setNum(0);
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeUp() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (mCardMap[x][y1].getNum() > 0) {
                        if (mCardMap[x][y].getNum() <= 0) {
                            mCardMap[x][y].setNum(mCardMap[x][y1].getNum());
                            mCardMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                        } else if (mCardMap[x][y].equals(mCardMap[x][y1])) {
                            mCardMap[x][y].setNum(mCardMap[x][y].getNum() * 2);
                            mCardMap[x][y1].setNum(0);
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeDown() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 4 - 1; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (mCardMap[x][y1].getNum() > 0) {
                        if (mCardMap[x][y].getNum() <= 0) {
                            mCardMap[x][y].setNum(mCardMap[x][y1].getNum());
                            mCardMap[x][y1].setNum(0);
                            y++;
                            merge = true;
                        } else if (mCardMap[x][y].equals(mCardMap[x][y1])) {
                            mCardMap[x][y].setNum(mCardMap[x][y].getNum() * 2);
                            mCardMap[x][y1].setNum(0);
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }

        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete() {
        boolean complete = true;
        forfor:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (mCardMap[x][y].getNum() == 0 ||
                        (x > 0 && mCardMap[x][y].equals(mCardMap[x - 1][y])) ||
                        (x < 4 - 1 && mCardMap[x][y].equals(mCardMap[x + 1][y])) ||
                        (y > 0 && mCardMap[x][y].equals(mCardMap[x][y - 1])) ||
                        (y < 4 - 1 && mCardMap[x][y].equals(mCardMap[x][y + 1]))) {
                    complete = false;
                    break forfor;
                }
            }
        }

        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }
    }
}
