package com.example.wanghui.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtn;
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mGameView = (GameView) findViewById(R.id.gameview);
    }

    @Override
    public void onClick(View v) {
        mGameView.startGame();
    }
}
