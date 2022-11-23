import src.*;

public class App {
    public static void main(String[] args) {
        ChessJFrame jf = new ChessJFrame();// 声明框架对象
        jf.setLocationRelativeTo(null);// 居中显示
        jf.setVisible(true);// 设置为可见
        jf.setResizable(false);
    }
}
