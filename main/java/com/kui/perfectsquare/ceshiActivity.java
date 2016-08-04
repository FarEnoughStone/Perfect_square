package com.kui.perfectsquare;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/8/3.
 */

public class ceshiActivity extends Activity {
    public static int Checkpoint=0;
    private int x=0,y=0;
    private int xdx=0,ydx=0;
    private int lastX = 0, lastY = 0;
    private int RectangleNumber,mindx,deviationX,deviationY;
    private int screenWidth, screenHeight;
    private ImageView box;
    private ImageView[] imageViews;
    private TextView tv1,tv2;
    private RelativeLayout Layout;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_view);

        tv1 = (TextView) findViewById(R.id.textview1);
        tv2 = (TextView) findViewById(R.id.textview2);
        Layout = (RelativeLayout) findViewById(R.id.relativelayout);

        Init();//数据初始化
        boxinit();//盒子初始化
        //RrctInit();//方块初始化

    }

    private void Init() {
//        x = Checkpoint / 100;
//        y = Checkpoint % 100;
        resources = new Resources();
        screenHeight = Layout.getHeight();
        screenWidth = Layout.getWidth();
        mindx = (screenHeight / 2) / resources.boxlength[x][y * 2];
        tv1.setText(mindx);
    }
    private void boxinit() {
        box = new ImageView(this);
        box.setImageResource(R.color.white);
        box.setMinimumHeight(resources.boxlength[x][y * 2]*mindx);
        box.setMinimumWidth(resources.boxlength[x][y * 2 + 1]*mindx);
        box.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (screenWidth - resources.boxlength[x][y * 2 + 1] * mindx) / 2;
        lp1.bottomMargin = 30;
        Layout.addView(box,lp1);
        deviationX = mindx - box.getLeft() % mindx;
        deviationY = mindx - box.getBottom() % mindx;
    }

    private void RrctInit() {
        RectangleNumber = resources.rnumber[x][y];
        imageViews = new ImageView[RectangleNumber];
        int a = resources.rnumber[x][y];
        int[] w = resources.length[x][y];
        int[] h;

        if (w.length==a) {
            h = resources.length[x][y];
        } else {
            h= Arrays.copyOfRange(w, a, w.length);
        }
        ydx = h[a - 1] / 2 + 20;
        for(;a-->0;) {
            ImageView view = new ImageView(this);
            view.setImageResource(resources.color[a]);
            view.setMinimumWidth(w[a]*mindx);
            view.setMinimumHeight(h[a]*mindx);
            //view.setOnTouchListener(this);
            view.setClickable(true);
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin= xdx;
            lp1.topMargin = ydx;
            xdx = xdx + 100 ;
            imageViews[a]=view;
            Layout.addView(imageViews[a],lp1);
        }
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // TODO Auto-generated method stub
//        int action=event.getAction();
//        switch(action){
//            case MotionEvent.ACTION_DOWN:
//                lastX = (int) event.getRawX();
//                lastY = (int) event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //p.destroy();
//                int dx =(int)event.getRawX() - lastX;
//                int dy =(int)event.getRawY() - lastY;
//
//                int left = v.getLeft() + dx;
//                int top = v.getTop() + dy;
//                int right = v.getRight() + dx;
//                int bottom = v.getBottom() + dy;
//                if(left < 0){
//                    left = 0;
//                    right = left + v.getWidth();
//                }
//                if(right > screenWidth){
//                    right = screenWidth;
//                    left = right - v.getWidth();
//                }
//                if(top < 0){
//                    top = 0;
//                    bottom = top + v.getHeight();
//                }
//                if(bottom > screenHeight){
//                    bottom = screenHeight;
//                    top = bottom - v.getHeight();
//                }
//                v.layout(left, top, right, bottom);
//                lastX = (int) event.getRawX();
//                lastY = (int) event.getRawY();
//                break;
//            case MotionEvent.ACTION_UP:
//                if (v.getLeft() % mindx > mindx/2) {
//                    left = v.getLeft() - (v.getLeft() % mindx) + mindx + deviationX;
//                    right = left + v.getWidth();
//                } else {
//                    left = v.getLeft() - (v.getLeft() % mindx)+ deviationX;
//                    right = left + v.getWidth();
//                }
//                if (v.getTop() % mindx > mindx/2) {
//                    bottom = v.getBottom() - (v.getBottom() % mindx) + mindx + deviationY;
//                    top = bottom - v.getHeight();
//                } else {
//                    bottom = v.getBottom() - (v.getBottom() % mindx)+ deviationY;
//                    top = bottom - v.getHeight();
//                }
//                v.layout(left, top, right, bottom);
//                //jg();
//                break;
//        }
//        return false;
//    }
}

class Resources {
    public int[][] rnumber={{4,6,9},{9,9},{21,22}};
    public int[][] boxlength={
            {4,4},
            {33,32},
            {112,120}};
    public int[][][] length={
            {
                    {1,1,1,1}
            },
            {//完美矩形边长数据
                    {1,4,7,8,9,10,14,15,18},
                    {2,5,7,9,16,25,28,33,36}

            },
            {//完美正方边长数据
                    {2,4,6,7,8,9,11,15,16,17,18,19,24,25,27,29,33,35,37,42,50},
                    {2,3,4,6,7,8,12,13,14,15,16,17,18,21,22,23,24,26,27,28,50,60}
            }};
    public int[] color={R.color.red,
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
}
