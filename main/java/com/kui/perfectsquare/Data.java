package com.kui.perfectsquare;

/**
 * Created by Administrator on 2016/8/9.
 */

class Data {
    final static int[][] RECT_NUMBER={{5,5,7,7,9,9},{9,9,10,10,11},{21,22,22}};
    final static int[][] BOX_LENGTH={
            {//完美填充
            12,12,
            12,12,
            12,12,
            12,12,
            10,10,
            12,12},
            {//完美矩形
            33,32,
            69,61,
            105,104,
            57,55,
            97,96},
            {//完美正方
            112,112,
            110,110,
            110,110,}
    };
    final static int[][][] RECT_LENGTH={
            {//完美填充边长数据
                    {4,4,8,8,4,     8,8,4,4,4},
                    {4,4,8,4,4,     12,8,4,4,4},
                    {6,9,3,3,3,6,3,     6,3,6,6,6,3,3},
                    {3,3,6,3,9,3,12,    3,3,3,6,3,9,3},
                    {2,2,4,2,6,6,2,8,6,     2,2,2,4,2,2,6,2,4},
                    {2,2,2,4,4,6,4,4,6,     2,2,4,4,4,2,6,6,6}
            },
            {//完美矩形边长数据
                    {1,4,7,8,9,10,14,15,18},
                    {2,5,7,9,16,25,28,33,36},
                    {7,12,16,19,26,28,33,44,45,60},
                    {2,3,8,11,13,15,17,25,27,30},
                    {2,7,12,14,17,24,26,31,40,41,56}

            },
            {//完美正方边长数据
                    {2,4,6,7,8,9,11,15,16,17,18,19,24,25,27,29,33,35,37,42,50},
                    {2,3,4,6,7,8,12,13,14,15,16,17,18,21,22,23,24,26,27,28,50,60},
                    {1,2,3,4,6,8,9,12,14,16,17,18,19,21,22,23,24,26,27,28,50,60},
            }
    };
    final static int[][][] PROMPT={
            {//完美填充位置提示数据
                    {0,8,4,0,4,     0,4,0,8,4},
                    {0,4,4,8,8,     0,0,8,0,4},
                    {6,0,0,9,3,3,0,     6,0,3,0,6,3,9},
                    {9,6,6,3,3,0,0,     9,9,6,6,3,3,0},
                    {8,6,6,6,0,0,8,0,0,        8,8,6,2,2,4,0,0,6},
                    {0,4,4,0,4,2,0,8,6,     10,4,0,0,6,10,4,6,0}
            },
            {//完美矩形位置提示数据
                    {24,14,18,25,24,14,0,18,0,      9,10,10,9,0,0,0,17,14},
                    {34,36,34,25,25,0,41,36,0,      36,33,38,36,45,36,33,0,0},
                    {72,60,44,60,79,44,72,0,60,0,       64,64,60,45,45,76,71,60,0,0},
                    {42,30,25,33,44,42,25,0,30,0,       38,27,30,27,27,40,38,30,0,0},
                    {54,66,54,40,56,73,40,66,0,56,0,       56,58,58,56,41,41,70,65,56,0,0}
            },
            {//完美正方位置提示数据
                    {63,29,82,63,85,54,82,50,54,65,70,93,88,29,85,0,0,50,33,70,0,
                    50,75,46,52,27,50,35,35,59,35,52,27,46,50,0,50,79,0,75,70,0},
                    {24,92,67,54,60,46,83,54,46,95,67,54,92,71,24,60,0,0,83,26,60,0,
                    82,89,89,74,73,74,77,80,60,77,73,93,92,89,60,50,60,84,50,82,0,0},
                    {91,24,60,87,54,46,54,63,46,75,75,92,91,54,24,87,0,0,60,26,60,0,
                    92,82,77,73,74,74,80,77,60,77,93,92,73,89,60,50,60,84,50,82,0,0}
            }
    };
    final static int[] RECT_COLOR={
            R.color.mediumvioletred,
            R.color.darkviolet,
            R.color.powderblue,
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
            R.color.slateblue,
            R.color.palegreen,
            R.color.slategrey,
            R.color.red,
    };
}
