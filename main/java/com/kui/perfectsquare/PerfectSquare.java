package com.kui.perfectsquare;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/14.
 */

public class PerfectSquare extends Activity implements View.OnTouchListener{

    RelativeLayout mainLayout;
    ImageView box;
    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    int a=21;
    int mindx=8,boxw=896,boxh=896;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.perfectsquare);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;
        mainLayout = (RelativeLayout)findViewById(R.id.activity_main);
//        TextView v = new TextView(this);
//        v.setText("聪明的玩家，你正在和上个世纪的著名数学家 杜伊维斯廷 想着同一个问题。");
//        v.setTextSize(20);
//        v.setTextColor(0x000000);
//        v.setMinimumWidth(500);
//        v.setMinimumHeight(100);
//        v.setId(View.generateViewId());
//        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lp1.leftMargin = (screenWidth-500)/2;
//        lp1.topMargin = 100;
//        mainLayout.addView(v,lp1);

        boxinit();
        ImageView imageViews[] = new ImageView[a];
        int[] w = {2,4,6,7,8,9,11,15,16,17,18,19,24,25,27,29,33,35,37,42,50};
        int[] color={R.color.red,
                R.color.mediumvioletred,
                R.color.darkviolet,
                R.color.powderblue,
                R.color.slategrey,
                R.color.mediumaquamarine,
                R.color.cornflowerblue,
                R.color.darkolivegreen,
                R.color.darkslategray,
                R.color.seagreen,
                R.color.midnightblue,
                R.color.darkturquoise,
                R.color.darkcyan,
                R.color.greenyellow,
                R.color.goldenrod,
                R.color.burlywood,
                R.color.blue,
                R.color.greenyellow,
                R.color.goldenrod,
                R.color.olivedrab,
                R.color.slateblue};
        int x=30,y=350;
        for(;a-->0;) {
            ImageView view = new ImageView(this);
            view.setImageResource(color[a]);
            view.setMinimumWidth(w[a]*8);
            view.setMinimumHeight(w[a]*8);
            view.setOnTouchListener(this);
            view.setClickable(true);
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (a > 13) {
                lp2.leftMargin = x + 100 * (a - 14);
                lp2.topMargin = y;
            } else if (a < 14 && a > 6) {
                lp2.leftMargin = x + 100 * (a - 7);
                lp2.topMargin = y + 200;
            } else {
                lp2.leftMargin = x + 100 * a;
                lp2.topMargin = y + 400;
            }
            imageViews[a]=view;
            mainLayout.addView(imageViews[a],lp2);
        }


    }



    private void boxinit() {
        box = new ImageView(this);
        box.setImageResource(R.color.white);
        box.setMinimumHeight(boxh);
        box.setMinimumWidth(boxw);
        box.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (screenWidth-boxw)/2-(screenWidth-boxw)/2%mindx;
        lp1.topMargin = screenHeight/2-screenHeight/2%mindx;
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
                if (v.getLeft() % mindx > mindx/2) {
                    left = v.getLeft() - (v.getLeft() % mindx) + mindx;
                    right = left + v.getWidth();
                } else {
                    left = v.getLeft() - (v.getLeft() % mindx);
                    right = left + v.getWidth();
                }
                if (v.getTop() % mindx > mindx/2) {
                    top = v.getTop() - (v.getTop() % mindx) + mindx;
                    bottom = top + v.getHeight();
                } else {
                    top = v.getTop() - (v.getTop() % mindx);
                    bottom = top + v.getHeight();
                }
                v.layout(left, top, right, bottom);
                break;
        }
        return false;
    }
}
