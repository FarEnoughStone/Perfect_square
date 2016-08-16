package com.kui.perfectsquare;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


/**
 * Created by Administrator on 2016/8/9.
 */

public class GameActivity extends Activity implements View.OnTouchListener{

    static int checkpoint=0;

    private byte rectNumber,aPOINT,bPOINT,order,orderKey=0;
    private int boxLeft, boxTop,boxWidth,boxHeight;
    private int lastX, lastY;
    private int mindx,deviationX,deviationY;
    private int screenWidth, screenHeight;
    private byte[][] testMatrix;
    private int[] h, w;
    private ImageView[] imageViews;
    private TextView tv1,tv2,tv3;
    private Button key1,key2;
    private RelativeLayout layout,keyBoard,ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_view);

        tv1 = (TextView) findViewById(R.id.textview1);
        tv2 = (TextView) findViewById(R.id.textview2);
        tv3 = (TextView) findViewById(R.id.textview3);
        key1 = (Button) findViewById(R.id.key1);
        key2 = (Button) findViewById(R.id.key2);
        layout = (RelativeLayout) findViewById(R.id.relativelayout);
        keyBoard = (RelativeLayout) findViewById(R.id.keyboard);
        ll = (RelativeLayout) findViewById(R.id.ll);

        key2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keyBoard.getVisibility() == View.INVISIBLE) {
                    keyBoard.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.INVISIBLE);
                    key2.setText("隐藏键盘");
                } else {
                    keyBoard.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    key2.setText("显示键盘");
                }
                key1.setText("方块" + orderKey);
                key1.setTextColor(Data.RECT_COLOR[orderKey]);
            }
        });
        key1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderKey > order) {
                    orderKey --;
                } else {
                    orderKey = (byte) (rectNumber - 1);
                }
                key1.setText("方块"+orderKey);
                key1.setTextColor(Data.RECT_COLOR[orderKey]);
            }
        });
        (findViewById(R.id.keyUp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyOnClick((byte)0);
            }
        });
        (findViewById(R.id.keyDown)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyOnClick((byte)1);
            }
        });
        (findViewById(R.id.keyLeft)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyOnClick((byte)2);
            }
        });
        (findViewById(R.id.keyRight)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyOnClick((byte)3);
            }
        });
        (findViewById(R.id.tv_prompt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prompt(ll);
            }
        });
        (findViewById(R.id.tv_choose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose(ll);
            }
        });
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.removeAllViews();
                ll.setVisibility(View.INVISIBLE);
            }
        });

        init();//数据初始化
        boxInit();//盒子初始化
        rectInit();//方块初始化

        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            int currentVersion = info.versionCode;
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            int lastVersion = prefs.getInt("first", 0);
            final LinearLayout jiaocheng = (LinearLayout) findViewById(R.id.ll_jiaocheng);
            jiaocheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jiaocheng.setVisibility(View.INVISIBLE);
                }
            });
            if (currentVersion > lastVersion) {
                jiaocheng.setVisibility(View.VISIBLE);
                prefs.edit().putInt("first", currentVersion).commit();
            } else {
                jiaocheng.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            (Toast.makeText(this, "获取版本出错！", Toast.LENGTH_SHORT)).show();
        }
    }

    private void init() {
        aPOINT = (byte) (checkpoint / 100);
        bPOINT = (byte) (checkpoint % 100);
        rectNumber = (byte)Data.RECT_NUMBER[aPOINT][bPOINT];
        orderKey = order = (byte) (rectNumber - 1);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 60;
        testMatrix = new byte[Data.BOX_LENGTH[aPOINT][2 * bPOINT+1]][Data.BOX_LENGTH[aPOINT][2 * bPOINT]];
        for (int i = 0; i < Data.BOX_LENGTH[aPOINT][2 * bPOINT]; i++) {
            for (int j = 0; j < Data.BOX_LENGTH[aPOINT][2 * bPOINT + 1]; j++) {
                testMatrix[j][i] = 0;
            }
        }
        mindx = Math.min(screenHeight/2/Data.BOX_LENGTH[aPOINT][2*bPOINT],screenWidth*4/5/Data.BOX_LENGTH[aPOINT][1+2*bPOINT]);
        if (mindx <= 0) {
            (Toast.makeText(this, "数据出错！", Toast.LENGTH_SHORT)).show();
            finish();
        }
        String s="";
        switch (aPOINT) {
            case 0:
                s = getResources().getString(R.string.perfectFill);
                break;
            case 1:
                s = getResources().getString(R.string.perfectRectangle);
                break;
            case 2:
                s = getResources().getString(R.string.app_name);
                break;
            default:
                (Toast.makeText(this, "数据出错！", Toast.LENGTH_SHORT)).show();
                finish();
        }
        drawRect(layout);
        tv1.setText(s);
        tv2.setText("第" + (bPOINT + 1) + "关");
        key2.setText("显示键盘");
        String[][] strings={
                {
                        getResources().getString(R.string.stringA0),
                        getResources().getString(R.string.stringA1),
                        getResources().getString(R.string.stringA2),
                        getResources().getString(R.string.stringA3)
                },
                {
                        getResources().getString(R.string.stringB0),
                        getResources().getString(R.string.stringB1),
                },
                {
                        getResources().getString(R.string.stringC0),
                        getResources().getString(R.string.stringC1),
                }
        };
        if (bPOINT < strings[aPOINT].length) {
            tv3.setText(strings[aPOINT][bPOINT]);
        } else {
            tv3.setText("当前关数为"+rectNumber+"阶!");
        }

    }

    private void boxInit() {
        ImageView box = new ImageView(this);
        box.setImageResource(R.color.white);
        box.setMinimumWidth(Data.BOX_LENGTH[aPOINT][bPOINT * 2] * mindx);
        box.setMinimumHeight(Data.BOX_LENGTH[aPOINT][bPOINT * 2 + 1] * mindx);
        box.setId(View.generateViewId());
        box.setAlpha(0.9f);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        boxHeight = Data.BOX_LENGTH[aPOINT][bPOINT * 2 + 1] * mindx;
        boxWidth = Data.BOX_LENGTH[aPOINT][bPOINT * 2] * mindx;
        boxLeft = lp1.leftMargin = (screenWidth - boxWidth) / 2;
        boxTop = lp1.topMargin = screenHeight / 3 - 100;
        layout.addView(box,lp1);
        deviationX = lp1.leftMargin % mindx;
        deviationY = lp1.topMargin % mindx;
    }

    private void rectInit() {
        imageViews = new ImageView[rectNumber];
        int a = rectNumber;
        w = Data.RECT_LENGTH[aPOINT][bPOINT];
        if (w.length==a) {
            h = Data.RECT_LENGTH[aPOINT][bPOINT];
        } else {
            h= Arrays.copyOfRange(w, a, w.length);
        }
        for(;a-->0;) {
            ImageView view = new ImageView(this);
            view.setImageResource(Data.RECT_COLOR[a]);
            view.setMinimumWidth(w[a]*mindx);
            view.setMinimumHeight(h[a]*mindx);
            view.setOnTouchListener(this);
            view.setClickable(true);
            view.setAlpha(0.9f);
            view.setVisibility(View.INVISIBLE);
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin = ((screenWidth - w[a] * mindx) / 2/mindx)*mindx+deviationX;
            lp1.topMargin = (20/mindx)*mindx+deviationY;
            imageViews[a]=view;
            layout.addView(imageViews[a],lp1);
        }
        imageViews[order].setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        int action=event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                if (v.getLeft() >= boxLeft && v.getTop() >= boxTop &&
                        v.getHeight() + v.getTop() <= boxTop + boxHeight &&
                        v.getLeft() + v.getWidth() <= boxLeft + boxWidth) {
                    int a = (v.getLeft() - boxLeft) / mindx;
                    int b = (v.getTop() - boxTop) / mindx;
                    for (int i = v.getWidth() / mindx; i-- > 0; ) {
                        for (int j = v.getHeight() / mindx; j-- > 0; ) {
                            if (testMatrix[b+j][a+i] > 0) {
                                testMatrix[b+j][a+i] -= 1;
                            } else {
                                (Toast.makeText(this, "检测矩阵出错！", Toast.LENGTH_SHORT)).show();
                            }
                        }
                    }
                }
                for(byte i=rectNumber;i-->0;) {
                    if (v.getId() == imageViews[i].getId()) {
                        orderKey = i;
                        key1.setText("方块" + orderKey);
                        key1.setTextColor(Data.RECT_COLOR[orderKey]);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
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
                if ((v.getLeft() - deviationX) % mindx > mindx / 2) {
                    left = v.getLeft() - ((v.getLeft() - deviationX) % mindx) + mindx;
                    right = left + v.getWidth();
                } else {
                    left = v.getLeft() - ((v.getLeft() - deviationX) % mindx);
                    right = left + v.getWidth();
                }
                if ((v.getTop() - deviationY)% mindx > mindx/2) {
                    top = v.getTop() - ((v.getTop() - deviationY) % mindx) + mindx;
                    bottom = top + v.getHeight();
                } else {
                    top = v.getTop() - ((v.getTop() - deviationY) % mindx);
                    bottom = top + v.getHeight();
                }
                v.layout(left, top, right, bottom);
                if (order > 0 && imageViews[order].getTop() >= boxTop) {
                    imageViews[--order].setVisibility(View.VISIBLE);
                }
                passInspect(v);
                break;
        }
        return false;
    }

    private void keyOnClick(byte mbyte) {
        ImageView v = imageViews[orderKey];
        if (v.getLeft() >= boxLeft && v.getTop() >= boxTop &&
                v.getHeight() + v.getTop() <= boxTop + boxHeight &&
                v.getLeft() + v.getWidth() <= boxLeft + boxWidth) {
            int a = (v.getLeft() - boxLeft) / mindx;
            int b = (v.getTop() - boxTop) / mindx;
            for (int i = v.getWidth() / mindx; i-- > 0; ) {
                for (int j = v.getHeight() / mindx; j-- > 0; ) {
                    if (testMatrix[b+j][a+i] > 0) {
                        testMatrix[b+j][a+i] -= 1;
                    } else {
                        (Toast.makeText(this, "检测矩阵出错！", Toast.LENGTH_SHORT)).show();
                    }
                }
            }
        }
        int left = (v.getLeft() / mindx) * mindx + deviationX;
        int top = (v.getTop() / mindx) * mindx + deviationY;
        int right = (v.getRight() / mindx) * mindx + deviationX;
        int bottom = (v.getBottom() / mindx) * mindx + deviationY;
        switch (mbyte) {
            case 0:top -= mindx;bottom = top + v.getHeight();break;
            case 1:top += mindx;bottom = top + v.getHeight();break;
            case 2:left -= mindx;right = left + v.getWidth();break;
            case 3:left += mindx;right = left + v.getWidth();break;
        }
        imageViews[orderKey].layout(left, top, right, bottom);
        if (order > 0 && imageViews[order].getTop() >= boxTop) {
            imageViews[--order].setVisibility(View.VISIBLE);
        }
        passInspect(imageViews[orderKey]);
    }

    private void passInspect(View v) {
        boolean r = true;
        if (v.getLeft() >= boxLeft && v.getTop() >= boxTop &&
                v.getHeight() + v.getTop() <= boxTop + boxHeight &&
                v.getLeft() + v.getWidth() <= boxLeft + boxWidth) {
            int a = (v.getLeft() - boxLeft) / mindx;
            int b = (v.getTop() - boxTop) / mindx;
            for (int i = v.getWidth() / mindx; i-- > 0; ) {
                for (int j = v.getHeight() / mindx; j-- > 0; ) {
                    if (testMatrix[b+j][a+i] >= 0) {
                        testMatrix[b+j][a+i] += 1;
                    } else {
                        (Toast.makeText(this, "检测矩阵出错！", Toast.LENGTH_SHORT)).show();
                    }
                }
            }
        }

        aLoop:for (int i = 0; i < Data.BOX_LENGTH[aPOINT][2 * bPOINT + 1]; i++) {
            for (int j = 0; j < Data.BOX_LENGTH[aPOINT][2 * bPOINT]; j++) {
                if (testMatrix[i][j] == 0) {
                    r = false;
                    break aLoop;
                }
            }
        }
        if (r) {
            if ((bPOINT + 1) < Data.RECT_NUMBER[aPOINT].length) {
                new AlertDialog.Builder(GameActivity.this)
                        .setMessage("恭喜过关了！^_^")//设置显示的内容
                        .setNegativeButton("重新挑战",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                layout.removeAllViews();//移除所有子控件
                                init();//数据初始化
                                boxInit();//盒子初始化
                                rectInit();//方块初始化
                                key1.setText("方块" + orderKey);
                                key1.setTextColor(Data.RECT_COLOR[orderKey]);
                            }})
                        .setPositiveButton("下一关",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkpoint += 1;
                                layout.removeAllViews();//移除所有子控件
                                init();//数据初始化
                                boxInit();//盒子初始化
                                rectInit();//方块初始化
                                key1.setText("方块" + orderKey);
                                key1.setTextColor(Data.RECT_COLOR[orderKey]);
                            }})
                        .show();//在按键响应事件中显示此对话框
            } else {
                new AlertDialog.Builder(GameActivity.this)
                        .setMessage("聪明的玩家，你已经通关\""+tv1.getText()+"\"了！")
                        .setNegativeButton("返回主菜单", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).show();
            }
        }
    }

    private void prompt(RelativeLayout ll) {
        ll.removeAllViews();
        RelativeLayout rl = new RelativeLayout(this);
        rl.setMinimumWidth(Data.BOX_LENGTH[aPOINT][bPOINT*2]*mindx+2);
        rl.setMinimumHeight(Data.BOX_LENGTH[aPOINT][bPOINT*2+1]*mindx+2);
        rl.setBackground(getResources().getDrawable(R.drawable.bg_rect));
        for(int a=rectNumber;a-->0;) {
            ImageView view = new ImageView(this);
            view.setMinimumWidth(w[a]*mindx);
            view.setMinimumHeight(h[a]*mindx);
            view.setBackground(getResources().getDrawable(R.drawable.bg_rect));
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin = Data.PROMPT[aPOINT][bPOINT][a]*mindx+1;
            lp1.topMargin = Data.PROMPT[aPOINT][bPOINT][a+rectNumber]*mindx+1;
            rl.addView(view,lp1);
        }
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (screenWidth - Data.BOX_LENGTH[aPOINT][bPOINT * 2]*mindx) / 2;
        lp1.topMargin = boxTop - 1;//+ 176
        ll.addView(rl,lp1);
        ll.setVisibility(View.VISIBLE);
    }

    private void drawRect(RelativeLayout rl) {
        for(int a=10;a-->0;) {
            ImageView view = new ImageView(this);
            view.setMinimumWidth((int)(Math.random()*1000));
            view.setMinimumHeight((int)(Math.random()*1000));
            view.setBackground(getResources().getDrawable(R.drawable.bg_random_rect_w));
            view.setId(View.generateViewId());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin = (int)(Math.random()*1000);
            lp1.topMargin = (int)(Math.random()*1000);
            rl.addView(view,lp1);
        }

    }

    private void choose(RelativeLayout rl) {
        rl.removeAllViews();
        final Button[] buttons = new Button[Data.RECT_NUMBER[aPOINT].length];
        for(int i=Data.RECT_NUMBER[aPOINT].length;i-->0;) {
            Button button = new Button(GameActivity.this);
            button.setText((" " + (i + 1) + " "));
            button.setTextColor(getResources().getColor(R.color.skyblue));
            button.setTextSize(20);
            button.setId(View.generateViewId());
            button.setBackground(getResources().getDrawable(R.drawable.bg_button_changecolor));
            button.setMinimumHeight(screenWidth * 2 / 15);
            button.setMinimumWidth(screenWidth * 2 / 15);
            button.setClickable(true);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for(int i=Data.RECT_NUMBER[aPOINT].length;i-->0;) {
                                if (view.getId() == buttons[i].getId()) {
                                    checkpoint = aPOINT * 100 + i;
                                    ll.setVisibility(View.INVISIBLE);
                                    layout.removeAllViews();//移除所有子控件
                                    init();//数据初始化
                                    boxInit();//盒子初始化
                                    rectInit();//方块初始化
                                    key1.setText("方块" + orderKey);
                                    key1.setTextColor(Data.RECT_COLOR[orderKey]);
                                }
                            }
                        }
                    });
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.leftMargin = screenWidth / 6 + (i % 3) * screenWidth * 4 / 15;
            lp1.topMargin = screenHeight / 6 + (i / 3) * screenWidth * 4 / 15 ;
            buttons[i] = button;
            rl.addView(button, lp1);
        }
        rl.setVisibility(View.VISIBLE);
    }
}
