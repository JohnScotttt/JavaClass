package com.sjsq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class GobangListener extends MouseAdapter implements ActionListener, Gobang {

    private GobangMain gm; // 棋盘面板对象
    private Graphics g; // 画笔对象
    boolean cco = true; // 记录玩家下棋还是电脑下棋
    boolean fff = true; // 记录是否能悔棋
    boolean ggg = true; // 记录是否能认输
    boolean stop = false; // 记录是否暂停
    private MyArrayList<Chess> array;
    int coloum1, row1;
    int xx, yy, max;

    public GobangListener(GobangMain gm, MyArrayList<Chess> array) { // 从GobangMain传窗体对象和记录棋子的数组
        this.gm = gm;
        this.array = array;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("悔棋")) {
            if (flag[0]) { // 人人对战悔棋
                if (fff) {
                    if (array.getSize() > 1) {
                        array1[coloum1][row1] = 0;
                        Chess aaa = array.get(array.getSize() - 2);
                        coloum1 = aaa.coloum;
                        row1 = aaa.row;
                        array.Delete();
                        cco = !cco;
                        gm.repaint();
                    }
                }
            }

            if (flag[1]) { // 人机对战悔棋
                if (fff) {
                    if (cco) {
                        if (array.getSize() > 2) {
                            array1[xx][yy] = 0;
                            Chess aaa = array.get(array.getSize() - 2);
                            coloum1 = aaa.coloum;
                            row1 = aaa.row;
                            array.Delete();
                            array1[coloum1][row1] = 0;
                            Chess bbb = array.get(array.getSize() - 2);
                            xx = bbb.coloum;
                            yy = bbb.row;
                            array.Delete();
                            gm.repaint();
                        }
                    }
                }
            }
        }
        if (e.getActionCommand().equals("认输")) {
            if (ggg) {
                if (flag[0]) {
                    if (cco) {
                        JOptionPane.showMessageDialog(gm, "白棋获胜");
                    } else {
                        JOptionPane.showMessageDialog(gm, "黑棋获胜");
                    }
                    gm.removeMouseListener(this);
                    fff = false;
                    ggg = false;
                }
                if (flag[1]) {
                    if (co[0]) {
                        if (cco) {
                            JOptionPane.showMessageDialog(gm, "黑棋获胜");
                        } else {
                            JOptionPane.showMessageDialog(gm, "白棋获胜");
                        }
                        gm.removeMouseListener(this);
                        fff = false;
                        ggg = false;
                    }
                    if (co[1]) {
                        if (cco) {
                            JOptionPane.showMessageDialog(gm, "白棋获胜");
                        } else {
                            JOptionPane.showMessageDialog(gm, "黑棋获胜");
                        }
                        gm.removeMouseListener(this);
                        fff = false;
                        ggg = false;
                        array.Reset();
                    }
                }
            }
        }
        if (e.getActionCommand().equals("暂停")) { // 暂停游戏
            if (stop){
                gm.tarray[3] = "恢复";
                gm.addMouseListener(this);
                stop = !stop;
            } else {
                gm.tarray[3] = "暂停";
                gm.removeMouseListener(this);
                stop = !stop;
            }
        }
        if (e.getActionCommand().equals("人人对战")) { // 选择人人对战模式 flag[0]为true,flag[1]为false
            flag[0] = true;
            flag[1] = false;
            for (int i = 0; i < array1.length; i++) {
                Arrays.fill(array1[i], 0);
            }
            cco = true;
            fff = true;
            ggg = true;
            array.Reset();
            gm.repaint();
        }
        if (e.getActionCommand().equals("人机对战")) { // 选择人机对战模式 flag[0]为false,flag[1]为true
            flag[0] = false;
            flag[1] = true;
            for (int i = 0; i < array1.length; i++) {
                Arrays.fill(array1[i], 0);
            }
            cco = true;
            fff = true;
            ggg = true;
            array.Reset();
            gm.repaint();
        }
        if (e.getActionCommand().equals("黑子(先手)")) {
            co[0] = false;
            co[1] = true;
            for (int i = 0; i < array1.length; i++) {
                Arrays.fill(array1[i], 0);
            }
            cco = true;
            fff = true;
            ggg = true;
            array.Reset();
            gm.repaint();
        }
        if (e.getActionCommand().equals("白子(后手)")) {
            co[0] = true;
            co[1] = false;
            for (int i = 0; i < array1.length; i++) {
                Arrays.fill(array1[i], 0);
            }
            cco = false;
            fff = true;
            ggg = true;
            array.Reset();
            gm.repaint();
        }
        if (e.getActionCommand().equals("开始新游戏")) {
            if (flag[1]) {
                if (co[1]) {
                    for (int i = 0; i < array1.length; i++) {
                        Arrays.fill(array1[i], 0);
                    }
                    gm.addMouseListener(this);
                    array.Reset();
                    gm.repaint();
                }
                if (co[0]) {

                    for (int i = 0; i < array1.length; i++) {
                        Arrays.fill(array1[i], 0);
                    }
                    // cco=false;
                    gm.addMouseListener(this);
                    array.Reset();
                    gm.repaint();

                    // g.setColor(Color.BLACK);
                    // g.fillOval(X+8*size-size/2, Y+8*size-size/2, size, size);
                    array1[7][7] = -1; // AI先手第一个子落点
                    Chess sh = new Chess(7, 7, Color.BLACK);
                    array.add(sh);
                    cco = true;
                    fff = true;
                    ggg = true;
                }
            }
            if (flag[0]) {
                for (int i = 0; i < array1.length; i++) {
                    Arrays.fill(array1[i], 0);
                }
                cco = true;
                fff = true;
                ggg = true;
                gm.addMouseListener(this);
                array.Reset();
                gm.repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent e) { // 鼠标松开的时候进行的操作
        if (flag[0]) { // 选择人人对战模式进行的操作
            if (g == null)
                g = gm.getGraphics();
            int x = e.getX();
            int y = e.getY();
            coloum1 = (x - X + size / 2) / size;
            row1 = (y - Y + size / 2) / size;
            if (coloum1 < coloum && row1 < row) {
                if (array1[coloum1][row1] == 0) {
                    if (cco) {
                        g.setColor(Color.BLACK);
                        g.fillOval(X + coloum1 * size - size / 2, Y + row1 * size - size / 2, size, size);
                        array1[coloum1][row1] = 1;
                        Chess sh = new Chess(coloum1, row1, Color.BLACK);
                        array.add(sh);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillOval(X + coloum1 * size - size / 2, Y + row1 * size - size / 2, size, size);
                        array1[coloum1][row1] = -1;
                        Chess sh = new Chess(coloum1, row1, Color.WHITE);
                        array.add(sh);
                    }

                    Judge jd = new Judge(coloum1, row1);
                    if (jd.judge()) {
                        if (cco) {
                            JOptionPane.showMessageDialog(gm, "黑棋获胜");
                        } else {
                            JOptionPane.showMessageDialog(gm, "白棋获胜");
                        }
                        gm.removeMouseListener(this);
                        fff = false;
                        ggg = false;
                        array.Reset();
                    }
                    cco = !cco;

                }

            }
        }

        if (flag[1]) { // 选择人机对战进行的操作
            if (g == null)
                g = gm.getGraphics();
            if (co[1]) {
                if (cco) { // 若cco为true，则人下棋
                    int x = e.getX();
                    int y = e.getY();
                    coloum1 = (x - X + size / 2) / size;
                    row1 = (y - Y + size / 2) / size;
                    if (coloum1 < coloum && row1 < row) {
                        if (array1[coloum1][row1] == 0) {
                            g.setColor(Color.BLACK);
                            g.fillOval(X + coloum1 * size - size / 2, Y + row1 * size - size / 2, size, size);
                            array1[coloum1][row1] = 1;
                            Chess sh = new Chess(coloum1, row1, Color.BLACK);
                            array.add(sh);

                            Judge jd = new Judge(coloum1, row1);
                            if (jd.judge()) {
                                if (cco) {
                                    JOptionPane.showMessageDialog(gm, "黑棋获胜");
                                } else {
                                    JOptionPane.showMessageDialog(gm, "白棋获胜");
                                }
                                gm.removeMouseListener(this);
                                fff = false;
                                ggg = false;
                                array.Reset();
                                cco = !cco;
                            }
                            cco = !cco;

                        }

                    }
                }
                if (!cco) { // 若cco为false，则机器下棋
                    AIX();
                }
            }
            if (co[0]) {
                if (cco) { // 若cco为true，则人下棋
                    int x = e.getX();
                    int y = e.getY();
                    coloum1 = (x - X + size / 2) / size;
                    row1 = (y - Y + size / 2) / size;
                    if (coloum1 < coloum && row1 < row) {
                        if (array1[coloum1][row1] == 0) {
                            g.setColor(Color.WHITE);
                            g.fillOval(X + coloum1 * size - size / 2, Y + row1 * size - size / 2, size, size);
                            array1[coloum1][row1] = 1;
                            Chess sh = new Chess(coloum1, row1, Color.WHITE);
                            array.add(sh);

                            Judge jd = new Judge(coloum1, row1);
                            if (jd.judge()) {
                                if (cco) {
                                    JOptionPane.showMessageDialog(gm, "白棋获胜");
                                } else {
                                    JOptionPane.showMessageDialog(gm, "黑棋获胜");
                                }
                                gm.removeMouseListener(this);
                                fff = false;
                                ggg = false;
                                array.Reset();
                                cco = !cco;
                            }
                            cco = !cco;

                        }

                    }
                }
                if (!cco) { // 若cco为false，则机器下棋
                    AIX();
                }
            }
        }

    }

    // 调用AI进行下棋
    public void AIX() {
        if (co[1]) {
            for (int i = 0; i < weightArray.length; i++) {
                for (int j = 0; j < weightArray[i].length; j++) {
                    weightArray[i][j] = 0;
                }
            }
            max = -1;
            AI.Quan();
            for (int i = 0; i < weightArray.length; i++) {
                for (int j = 0; j < weightArray[i].length; j++) {
                    if (i < 5 && j < 5) {
                        if (max <= weightArray[i][j] && array1[i][j] == 0) {
                            max = weightArray[i][j];
                            xx = i;
                            yy = j;
                        }
                    } else {
                        if (max < weightArray[i][j] && array1[i][j] == 0) {
                            max = weightArray[i][j];
                            xx = i;
                            yy = j;
                        }
                    }
                }
            }
            if (array1[xx][yy] == 0) {
                g.setColor(Color.WHITE);
                g.fillOval(X + xx * size - size / 2, Y + yy * size - size / 2, size, size);
                array1[xx][yy] = -1;
                Chess sh = new Chess(xx, yy, Color.WHITE);
                array.add(sh);

                Judge jd = new Judge(xx, yy);
                if (jd.judge()) {
                    if (cco) {
                        JOptionPane.showMessageDialog(gm, "黑棋获胜");
                    } else {
                        JOptionPane.showMessageDialog(gm, "白棋获胜");
                    }
                    gm.removeMouseListener(this); // 移除监听，这时将不能对棋盘进行操作
                    fff = false; // 设置不能进行悔棋
                    ggg = false; // 设置不能进行认输
                    array.Reset();
                }
                cco = !cco;
            }
        }
        if (co[0]) {
            for (int i = 0; i < weightArray.length; i++) {
                for (int j = 0; j < weightArray[i].length; j++) {
                    weightArray[i][j] = 0;
                }
            }
            max = -1;
            AI.Quan();
            for (int i = 0; i < weightArray.length; i++) {
                for (int j = 0; j < weightArray[i].length; j++) {
                    if (i < 5 && j < 5) {
                        if (max <= weightArray[i][j] && array1[i][j] == 0) {
                            max = weightArray[i][j];
                            xx = i;
                            yy = j;
                        }
                    } else {
                        if (max < weightArray[i][j] && array1[i][j] == 0) {
                            max = weightArray[i][j];
                            xx = i;
                            yy = j;
                        }
                    }
                }
            }

            if (array1[xx][yy] == 0) {
                g.setColor(Color.BLACK);
                g.fillOval(X + xx * size - size / 2, Y + yy * size - size / 2, size, size);
                array1[xx][yy] = -1;
                Chess sh = new Chess(xx, yy, Color.BLACK);
                array.add(sh);
                Judge jd = new Judge(xx, yy);
                if (jd.judge()) {
                    if (cco) {
                        JOptionPane.showMessageDialog(gm, "白棋获胜");
                    } else {
                        JOptionPane.showMessageDialog(gm, "黑棋获胜");
                    }
                    gm.removeMouseListener(this); // 移除监听，这时将不能对棋盘进行操作
                    fff = false; // 设置不能进行悔棋
                    ggg = false; // 设置不能进行认输
                    array.Reset();
                }
                cco = !cco;
            }
        }
    }

}