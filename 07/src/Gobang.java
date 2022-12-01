package src;

public interface Gobang {
    public static final int size = 40;
    public static final int X = 20, Y = 20;
    public static final int coloum = 15;
    public static final int row = 15;
    public static final int[][] array1 = new int[coloum][row];
    public static final int[][] weightArray = new int[coloum][row];
    public static final boolean flag[] = new boolean[2];
    public static final boolean co[] = new boolean[2];
}
