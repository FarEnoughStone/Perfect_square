package com.kui.perfectsquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        RelativeLayout bg = (RelativeLayout) findViewById(R.id.bg);
        (findViewById(R.id.bt1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.checkpoint=0;
                start();
            }
        });
        (findViewById(R.id.bt2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.checkpoint=100;
                start();
            }
        });
        (findViewById(R.id.bt3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.checkpoint=200;
                start();
            }
        });

        drawRect(bg);
    }
    private void start() {
        startActivity(new Intent(MainActivity.this,GameActivity.class));
    }

    private void drawRect(RelativeLayout rl) {
        for(int a=10;a-->0;) {
            ImageView view = new ImageView(this);
            view.setMinimumWidth((int)(Math.random()*1000));
            view.setMinimumHeight((int)(Math.random()*1000));
            view.setBackground(getResources().getDrawable(R.drawable.bg_random_rect));
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin = (int)(Math.random()*1000);
            lp1.topMargin = (int)(Math.random()*1000);
            rl.addView(view,lp1);
        }

    }
}
