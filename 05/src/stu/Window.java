package stu;
import javax.swing.*;
import java.awt.*;

public class Window {
    public void window() {
        JFrame jf = new JFrame("学生信息管理系统");
        // PopupMenu popupMenu1 = new PopupMenu();
        // MenuItem menuItem1 = new MenuItem();
        jf.setBounds(600, 350, 500, 300);
        JScrollPane jp = new JScrollPane();
        jf.setContentPane(jp);
        Object[] columnTitle={"姓名","年龄","性别"};
        Object[][] tableDate={
                new Object[] {"去病",20,"男"},
                new Object[] {"波斯客",25,"男"},
                new Object[] {"李小白",20,"男"},
                new Object[] {"张小骞",20,"男"},
                new Object[] {"唐小妃",20,"男"},
                new Object[] {"卫小青",20,"男"},
                new Object[] {"韩小信",20,"男"}
        };
        JTable table = new JTable(tableDate,columnTitle);
        table.setBounds(0, 0, 500, 100);
        JButton addstu = new JButton("add");
        JButton delestu = new JButton("delete");
        addstu.setBounds(0, 150, 100, 50);
        delestu.setBounds(150, 150, 100, 50);
        jp.add(table);
        jp.add(addstu);
        jp.add(delestu);
        // popupMenu1.add(menuItem1);
        // jf.add(popupMenu1);
        jf.setVisible(true);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}