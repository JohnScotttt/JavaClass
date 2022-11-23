package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChessJFrame extends JFrame {
    private int second = 0;
    private int minute = 0;
    private ChessBord chessbord;// 声明一个棋盘对象

    private Panel tool;// 声明一个功能面板对象
    private Panel state;// 声明一个状态面板对象
    private JLabel time;// 声明时间lable
    private Timer clock;// 声明一个计时器
    private Button StartButton;// 声明开始按钮
    private Button BackButton;// 声明悔棋按钮
    private Button exitButton;// 声明退出按钮
    private Button PauseButton;// 声明暂停按钮
    private Button GiveupButton;// 声明认输按钮

    public ChessJFrame() {// 构造函数
        setTitle("单机版五子棋");// 设置标题
        MyButtonLister mb = new MyButtonLister();// 按钮事件处理对象
        tool = new Panel();// 功能面板对象
        state = new Panel(new GridLayout(2, 1));// 状态面板对象
        time = new JLabel("00:00");// 时间lable
        time.setPreferredSize(new Dimension(200, 300));
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(new Font("宋体", Font.BOLD, 50));

        chessbord = new ChessBord();// 棋盘对象
        clock = new Timer(1000, null);// 计时器对象
        clock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (second == 59) {
                    second = 0;
                    minute += 1;
                } else {
                    second += 1;
                }
                time.setText(String.format("%02d", minute) + ":" + String.format("%02d", second));
                if (chessbord.GameOver) {
                    clock.stop();
                }
            }
        });
        clock.start();
        StartButton = new Button("重新开始");// 设置开始按钮
        BackButton = new Button("悔棋");// 设置悔棋按钮
        PauseButton = new Button("暂停");// 设置暂停按钮
        GiveupButton = new Button("认输");// 设置认输按钮
        tool.setLayout(new FlowLayout(FlowLayout.CENTER));// 流式布局
        tool.add(StartButton);
        tool.add(BackButton);
        tool.add(PauseButton);
        tool.add(GiveupButton);// 将按钮添加到面板对象
        state.add(time);
        state.add(chessbord.side);
        StartButton.addActionListener(mb);
        BackButton.addActionListener(mb);
        PauseButton.addActionListener(mb);
        GiveupButton.addActionListener(mb);// 将按钮添加到事件监听
        add(tool, BorderLayout.SOUTH);// 按钮所在的位置
        add(state, BorderLayout.EAST);// 状态所在的位置
        add(chessbord);// 添加棋盘对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭
        pack();// 自适应
    }

    private class MyButtonLister implements ActionListener {
        // 按钮处理事件类
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();// 获取事件源
            if (obj == StartButton) {// 事件源是重新开始按钮
                System.out.println("重新开始");
                time.setText("00:00");
                chessbord.side.setText("<html><body><p align=\"center\">当前落子方<br/>黑棋</p></body></html>");
                chessbord.restartGame();
                second = 0;
                minute = 0;
                clock.start();
            } else if (obj == BackButton) {// 事件源是悔棋按钮
                System.out.println("悔棋！");
                chessbord.goback();
                clock.start();
            } else if (obj == exitButton) {// 事件源是退出按钮
                System.exit(0);
            } else if (obj == GiveupButton) {// 事件源是弃权按钮
                chessbord.GameOver = true;
                String colorName = chessbord.start ? "白棋" : "黑棋";
                String msg = String.format("恭喜 %s赢了", colorName);
                JOptionPane.showMessageDialog(chessbord, msg);
            } else if (obj == PauseButton) {// 事件源是暂停按钮
                if (PauseButton.getLabel() == "暂停") {
                    chessbord.GameOver = true;
                    PauseButton.setLabel("恢复");
                } else if (PauseButton.getLabel() == "恢复") {
                    clock.start();
                    chessbord.GameOver = false;
                    PauseButton.setLabel("暂停");
                }
            }
        }
    }
}