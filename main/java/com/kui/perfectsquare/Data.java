package com.kui.perfectsquare;

/**
 * Created by Administrator on 2016/8/9.
 */

public class Data {
    public  final static int[][] RECT_NUMBER={{5,5,7},{9,9},{21,22}};
    public final static int[][] BOX_LENGTH={
            {//完美填充
            12,12,
            12,12,
            12,12},
            {//完美矩形
            33,32,
            69,61},
            {//完美正方
            112,112,
            110,110}
    };
    public final static int[][][] RECT_LENGTH={
            {//完美填充边长数据
                    {4,4,8,8,4,     8,8,4,4,4},
                    {4,4,8,4,4,     12,8,4,4,4},
                    {6,9,3,3,3,6,3,     6,3,6,6,6,3,3},
            },
            {//完美矩形边长数据
                    {1,4,7,8,9,10,14,15,18},
                    {2,5,7,9,16,25,28,33,36},

            },
            {//完美正方边长数据
                    {2,4,6,7,8,9,11,15,16,17,18,19,24,25,27,29,33,35,37,42,50},
                    {2,3,4,6,7,8,12,13,14,15,16,17,18,21,22,23,24,26,27,28,50,60},
            }
    };
    public final static int[][][] PROMPT={
            {//完美填充位置提示数据
                    {0,8,4,0,4,     0,4,0,8,4},
                    {0,4,4,8,8,     0,0,8,0,4},
                    {6,0,0,9,3,3,0,     6,0,3,0,6,3,9}
            },
            {//完美矩形位置提示数据
                    {24,14,18,25,24,14,0,18,0,      9,10,10,9,0,0,0,17,14},
                    {34,36,34,25,25,0,41,36,0,      36,33,38,36,45,36,33,0,0}
            },
            {//完美正方位置提示数据
                    {63,29,82,63,85,54,82,50,54,65,70,93,88,29,85,0,0,50,33,70,0,
                    50,75,46,52,27,50,35,35,59,35,52,27,46,50,0,50,79,0,75,70,0},
                    {24,92,67,54,60,46,83,54,46,95,67,54,92,71,24,60,0,0,83,26,60,0,
                    82,89,89,74,73,74,77,80,60,77,73,93,92,89,60,50,60,84,50,82,0,0}
            }
    };
    public final static int[] RECT_COLOR={
            R.color.red,
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
            R.color.slateblue
    };
}
