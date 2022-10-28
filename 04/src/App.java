import java.util.*;

import stu.*;

public class App {
    public static void main(String[] args) {
        StuGroup stu = new StuGroup();
        Scanner reader = new Scanner(System.in);
        int num;
        try {
            stu.load("demo", "demo", "demopw123456", "stu");
            System.out.print("How many students need to be input:");
            num = reader.nextInt();
            for (int i = 0; i < num; i++) {
                System.out.println("Information of the student " + (i + 1) + ":");
                stu.addStu(
                        reader.next(),
                        reader.next(),
                        reader.next(),
                        reader.nextInt(),
                        reader.nextInt(),
                        reader.nextInt());
            }
            System.out.print("How many students need to be deleted:");
            num = reader.nextInt();
            for (int i = 0; i < num; i++) {
                System.out.print("Name of the student to be deleted:");
                stu.deleteStu(reader.next());
            }
            System.out.println("The latest student information is:");
            stu.printitem();
        } finally {
            stu.close();
            reader.close();
        }
    }
}