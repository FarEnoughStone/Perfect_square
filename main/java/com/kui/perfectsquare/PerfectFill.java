package com.kui.perfectsquare;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/7/14.
 */

public class PerfectFill extends Activity implements View.OnTouchListener{

    RelativeLayout mainLayout;
    ImageView box;
    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    int a=5;
    ImageView imageViews[] = new ImageView[a];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.perfectfill);

        mainLayout = (RelativeLayout)findViewById(R.id.activity_main);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;
        boxinit();
        int[] w = {200, 200, 200,400,400};
        int[] h = {200, 400, 400,200,200};
        int[] color={R.color.peru,
                R.color.mediumvioletred,
                R.color.darkviolet,
                R.color.powderblue,
                R.color.greenyellow};
        int x=30,y=180;
        for(;a-->0;) {
            ImageView view = new ImageView(this);
            view.setImageResource(color[a]);
            view.setMinimumWidth(w[a]);
            view.setMinimumHeight(h[a]);
            view.setOnTouchListener(this);
            view.setClickable(true);
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin= x;
            lp1.topMargin = y;
            x=x+100;
            imageViews[a]=view;
            mainLayout.addView(imageViews[a],lp1);
        }


    }



    private void boxinit() {
        box = new ImageView(this);
        box.setImageResource(R.color.white);
        box.setMinimumHeight(600);
        box.setMinimumWidth(600);
        box.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (screenWidth-600)/2-(screenWidth-600)/2%50;
        lp1.topMargin = screenHeight/2-screenHeight/2%50;
        mainLayout.addView(box,lp1);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        int action=event.getAction();
        //Toast.makeText(DraftTest.this, "λ�ã�"+x+","+y, Toast.LENGTH_SHORT).show();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                //p.destroy();
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + v.getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (v.getLeft() % 50 > 24) {
                    left = v.getLeft() - (v.getLeft() % 50) + 50;
                    right = left + v.getWidth();
                } else {
                    left = v.getLeft() - (v.getLeft() % 50);
                    right = left + v.getWidth();
                }
                if (v.getTop() % 50 > 24) {
                    top = v.getTop() - (v.getTop() % 50) + 50;
                    bottom = top + v.getHeight();
                } else {
                    top = v.getTop() - (v.getTop() % 50);
                    bottom = top + v.getHeight();
                }
                v.layout(left, top, right, bottom);
                //jg();
                break;
        }
        return false;
    }
}
