package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GobangMain extends JPanel implements Gobang {

    // 定义数组，存储组件上要显示的文字信息
    String[] tarray = { "开始新游戏", "悔棋", "认输", "暂停", "对战模式：", "人人对战", "人机对战", "选择棋子颜色:" };
    private JLabel time;// 声明时间lable
    public Timer clock;// 声明一个计时器
    private int second = 0;
    private int minute = 0;

    public void initUI() {
        JFrame frame = new JFrame("五子棋");
        frame.setSize(930, 635);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // 设置棋盘面板的背景颜色
        this.setBackground(new Color(180, 154, 102));
        // 将棋盘面板添加到窗体的中间部分
        frame.add(this, BorderLayout.CENTER);
        time = new JLabel("00:00",JLabel.CENTER);// 时间lable
        time.setPreferredSize(new Dimension(170, 300));
        frame.add(time, BorderLayout.WEST);
        time.setFont(new Font("宋体", Font.BOLD, 50));

        // 实例化JPanel面板对象，作为东边放置按钮的面板
        JPanel eastPanel = new JPanel();
        // 设置东边面板的布局方式为流式布局居中对齐
        eastPanel.setLayout(new FlowLayout());
        // 设置面板容器组件的宽度和高度
        eastPanel.setPreferredSize(new Dimension(150, 0));
        // 实例化单选按钮分组对象
        ButtonGroup bg = new ButtonGroup();
        ButtonGroup btg = new ButtonGroup();
        // 实例化事件处理类的对象，然后将棋盘面板作为参数传递到GobangListener类的对象中
        GobangListener gl = new GobangListener(this, array);

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
            }
        });

        for (int i = 0; i < tarray.length; i++) {
            if (i < 4) {
                JButton button = new JButton(tarray[i]);
                button.setFont(new Font("华文行楷", Font.PLAIN, 20)); // 字体情况设置
                button.setPreferredSize(new Dimension(140, 80));
                eastPanel.add(button);
                button.addActionListener(gl);
            } else if (i == 4) {
                JLabel label = new JLabel(tarray[i]);
                label.setFont(new Font("华文行楷", Font.PLAIN, 20));
                eastPanel.add(label);
            } else if (i == 7) {
                JLabel label = new JLabel(tarray[i]);
                label.setFont(new Font("华文行楷", Font.PLAIN, 20));
                eastPanel.add(label);
            } else {
                JRadioButton button1 = new JRadioButton(tarray[i]);
                button1.setFont(new Font("华文行楷", Font.PLAIN, 18));
                button1.setSelected(false);
                bg.add(button1);
                eastPanel.add(button1);
                button1.addActionListener(gl);
            }
        }

        String[] array1 = { "黑子(先手)", "白子(后手)" };
        for (int l = 0; l < array1.length; l++) {
            JRadioButton button2 = new JRadioButton(array1[l]);
            button2.setFont(new Font("华文行楷", Font.PLAIN, 18));
            button2.setSelected(false);
            btg.add(button2);
            eastPanel.add(button2);
            button2.addActionListener(gl);
        }

        // 将eastPanel添加到窗体上的东边
        frame.add(eastPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    private MyArrayList<Chess> array = new MyArrayList<Chess>();

    public void paint(Graphics g) { // 棋盘的绘制
        super.paint(g);
        for (int i = 0; i < coloum; i++) {
            g.drawLine(X, Y + size * i, X + size * (coloum - 1), Y + size * i);// 横线 //格子40
            g.drawLine(X + size * i, Y, X + size * i, Y + size * (coloum - 1));// 竖线 //格子40
        }

        for (int i = 0; i < array.getSize(); i++) {
            Chess e = array.get(i);
            g.setColor(e.color);
            g.fillOval(X + e.coloum * size - size / 2, Y + e.row * size - size / 2, size, size);
        }
    }

}
