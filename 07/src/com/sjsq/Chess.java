package com.sjsq;

import java.awt.Color;//设置棋子颜色和棋子的落子位置

public class Chess extends Object implements Gobang {
    int coloum, row; // 输入列数，行数
    Color color; // 棋盘颜色的设置

    public Chess(int coloum, int row, Color color) {
        this.coloum = coloum;
        this.row = row;
        this.color = color;
    }
}