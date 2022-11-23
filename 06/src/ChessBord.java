package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessBord extends JPanel implements MouseListener {// 继承面板类和鼠标事件接口
    public JLabel side;// 声明落子方lable
    public static int MARGIN = 30;// 定义边距
    public static int ROWS = 15;// 定义行数
    public static int COLS = 15;// 定义列数
    public static int GRID_SPAN = 35;// 网格间距
    Chess[] chessList = new Chess[(ROWS + 1) * (COLS + 1)];// 定义一个棋子数组
    String[][] board = new String[MARGIN * 2 + GRID_SPAN * COLS][MARGIN * 2 + GRID_SPAN * COLS];// 声明一个字符串数组，用来判断输赢
    int chessCount;// 棋子数目
    int xindex, yindex;// 棋子的坐标索引
    boolean start = true;// 开始默认黑子先下
    boolean GameOver = false;// 定义是否游戏结束

    public ChessBord() {// 棋盘类构造函数
        side = new JLabel("<html><body><p align=\"center\">当前落子方<br/>黑棋</p></body></html>");
        side.setPreferredSize(new Dimension(200, 100));
        side.setHorizontalAlignment(JLabel.CENTER);
        side.setFont(new Font("宋体", Font.BOLD, 30));// 落子方lable
        setBackground(Color.LIGHT_GRAY);// 设置背景颜色
        addMouseListener(this);// 将棋盘类添加到鼠标事件监听器
        addMouseMotionListener(new MouseMotionListener() {// 匿名内部类
            @Override
            public void mouseMoved(MouseEvent e) {// 根据鼠标的移动所在的坐标来设置鼠标光标形状
                int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;// 对鼠标光标的x坐标进行转换
                int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;// 对鼠标光标的y坐标进行转换
                if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || GameOver || findchess(x1, y1)) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 设置鼠标光标为默认形状
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));// 设置鼠标光标为手型
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
        for (int i = 0; i < MARGIN * 2 + GRID_SPAN * COLS; i++) {// 对board[][]赋初值
            for (int j = 0; j < MARGIN * 2 + GRID_SPAN * COLS; j++) {
                board[i][j] = "0";
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (start) {
            side.setText("<html><body><p align=\"center\">当前落子方<br/>黑棋</p></body></html>");
        } else {
            side.setText("<html><body><p align=\"center\">当前落子方<br/>白棋</p></body></html>");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {// 鼠标点击事件
        if (GameOver)// 游戏结束，不能按
            return;
        String colorName = start ? "黑棋" : "白棋";// 判断是什么颜色的棋子
        xindex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;// 得到棋子x坐标
        yindex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;// 得到棋子y坐标
        board[xindex][yindex] = colorName;// 以棋子x坐标y坐标做索引将棋子的颜色添加到board中
        if (xindex < 0 || xindex > ROWS || yindex < 0 || yindex > COLS) {// 棋子在棋盘外不能下，
            return;
        } else if (findchess(xindex, yindex)) {// 所下位置已有棋子，不能下
            return;
        }
        Chess po = new Chess(xindex, yindex, start ? Color.black : Color.WHITE);// 对棋子对象进行初始化
        chessList[chessCount++] = po;// 将棋子对象添加到棋子数组中
        repaint();// 重画图型
        if (win(xindex, yindex, start)) {// 判断是否胜利
            GameOver = true;
            String msg = String.format("恭喜 %s赢了", colorName);
            JOptionPane.showMessageDialog(this, msg);
        } else if (chessCount == (COLS + 1) * (ROWS + 1)) {// 判断是否全部下满
            GameOver = true;
            String msg = String.format("恭喜 %s赢了", colorName);
            JOptionPane.showMessageDialog(this, msg);
        }
        start = !start;// 改变棋子先下棋状态

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {// 画棋盘和棋子
        super.paintComponent(g);
        for (int i = 0; i <= ROWS; i++) {// 画横线
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int j = 0; j <= COLS; j++) {// 画竖线
            g.drawLine(MARGIN + j * GRID_SPAN, MARGIN, MARGIN + j * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
        }
        for (int i = 0; i < chessCount; i++) {// 画棋子
            int xpos = chessList[i].getX() * GRID_SPAN + MARGIN;// 得到棋子x坐标
            int ypos = chessList[i].getY() * GRID_SPAN + MARGIN;// 得到棋子y坐标
            g.setColor(chessList[i].getColor());// 设置棋子颜色
            g.fillOval(xpos - Chess.DIAMETER / 2, ypos - Chess.DIAMETER / 2, Chess.DIAMETER, Chess.DIAMETER);// 画棋子
            if (i == chessCount - 1) {
                g.setColor(Color.red);// 标记最后一个棋子为红色
                g.drawRect(xpos - Chess.DIAMETER / 2, ypos - Chess.DIAMETER / 2, Chess.DIAMETER, Chess.DIAMETER);
            }
        }
    }

    private boolean findchess(int index, int yindex) {// 查找所在位置是否有棋子
        for (Chess c : chessList) {
            if (c != null && c.getX() == xindex && c.getY() == yindex)
                return true;
        }
        return false;
    }

    private boolean win(int x, int y, boolean start) {// 对棋子输赢的判断
        String str = start ? "黑棋" : "白棋";
        // 棋子所在行和列是否有五子相连的情况
        for (int i = 0; i < 16; i++) {
            if ((board[x][i].equals(str) && board[x][i + 1].equals(str) && board[x][i + 2].equals(str)
                    && board[x][i + 3].equals(str) && board[x][i + 4].equals(str))
                    || (board[i][y].equals(str) && board[i + 1][y].equals(str) && board[i + 2][y].equals(str)
                            && board[i + 3][y].equals(str) && board[i + 4][y].equals(str)))
                return true;
        }
        // 棋子所在撇行是否有五子相连的情况
        if (x + y >= 4 && x + y <= 30) {
            int i = (x + y <= 19) ? x + y : x + y - 20;
            if (x + y <= 19) {
                for (int k = 0; k <= i - 4; k++) {
                    if (board[k][i - k].equals(str) && board[k + 1][i - k - 1].equals(str)
                            && board[k + 2][i - k - 2].equals(str) && board[k + 3][i - k - 3].equals(str)
                            && board[k + 4][i - k - 4].equals(str))
                        return true;
                }
            } else {
                for (int k = i; k <= 15; k++) {
                    if (board[k][20 - k].equals(str) && board[k + 1][20 - k - 1].equals(str)
                            && board[k + 2][20 - k - 2].equals(str) && board[k + 3][20 - k - 3].equals(str)
                            && board[k + 4][20 - k - 4].equals(str))
                        return true;
                }
            }
        }
        // 棋子所在捺行是否有五子相连的情况
        if (y - x <= 15 && x - y <= 15) {
            int i = (x < y) ? y - x : x - y;
            if (x < y) {
                for (int k = 0; k <= 19 - 4 - i; k++) {
                    if (board[k][i + k].equals(str) && board[k + 1][i + k + 1].equals(str)
                            && board[k + 2][i + k + 2].equals(str) && board[k + 3][i + k + 3].equals(str)
                            && board[k + 4][i + k + 4].equals(str))
                        return true;
                }
            } else {
                for (int k = i; k <= 15; k++) {
                    if (board[k][i + k].equals(str) && board[k + 1][i + k + 1].equals(str)
                            && board[k + 2][i + k + 2].equals(str) && board[k + 3][i + k + 3].equals(str)
                            && board[k + 4][i + k + 4].equals(str))
                        return true;
                }
            }
        }
        return false;
    }

    public void goback() {// 悔棋函数
        if (chessCount == 0) {
            return;
        }
        chessList[chessCount - 1] = null;
        chessCount--;
        if (chessCount > 0) {
            xindex = chessList[chessCount - 1].getX();
            yindex = chessList[chessCount - 1].getY();
        }
        start = !start;
        GameOver = false;
        repaint();
    }

    public void restartGame() {// 重新开始函数
        for (int i = 0; i < chessList.length; i++)// 设置为初始状态
            chessList[i] = null;
        for (int i = 0; i < MARGIN * 2 + GRID_SPAN * COLS; i++) {
            for (int j = 0; j < MARGIN * 2 + GRID_SPAN * COLS; j++) {
                board[i][j] = "0";
            }
        }
        start = true;
        GameOver = false;
        chessCount = 0;
        repaint();
    }

    public Dimension getPreferredSize() {// 画矩形
        return new Dimension(MARGIN * 2 + GRID_SPAN * COLS, MARGIN * 2 + GRID_SPAN * ROWS);
    }

}