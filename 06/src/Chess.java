package src;

import java.awt.Color;

public class Chess {
    private int x;// 棋子的x坐标索引
    private int y;// 棋子的y坐标索引
    private Color color;// 棋子颜色
    public static int DIAMETER = 30;// 直径

    public Chess(int x, int y, Color color) {// 棋子构造函数
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

}