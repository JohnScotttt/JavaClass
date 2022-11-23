package src;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class StuGroup implements Mygroup {
    private Student[] stu;
    private int length;

    public StuGroup(int len) {
        stu = new Student[len];
    }

    private boolean isOverflow() {
        if (this.length < stu.length)
            return false;
        else
            return true;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public boolean addStu(Student x) {
        if (isOverflow())
            return false;
        stu[length++] = x;
        return true;
    }

    public Student removeStu(int index) {
        if (isEmpty()) {
            System.out.println("Fail, the Group is enpty.");
            return null;
        }
        Student temp = stu[index - 1];
        for (int i = index - 1; i < this.length - 1; i++) {
            stu[i] = stu[i + 1];
        }
        this.length--;
        return temp;
    }

    public void printelem() {
        for (int i = 0; i < length; i++) {
            System.out.println("---------------");
            stu[i].printelem();
            System.out.println("---------------");
        }
    }

    public void load(String x) throws Exception {
        File fr = new File(x);
        BufferedReader reader = new BufferedReader(new FileReader(fr));
        String stdinf = new String();
        while ((stdinf = reader.readLine()) != null) {
            String[] info = stdinf.split(" ");
            this.addStu(new Student(
                    info[0],
                    info[1],
                    info[2].charAt(0),
                    Integer.parseInt(info[3]),
                    Integer.parseInt(info[4]),
                    Integer.parseInt(info[5])));
        }
        reader.close();
    }

    public void write(String x) throws Exception {
        File fw = new File(x);
        FileWriter writer = new FileWriter(fw);
        String str = new String();
        for (int i = 0; i < this.length; i++) {
            str = stu[i].name + " " + stu[i].addr + " " + stu[i].sex + " " + stu[i].age + " " + stu[i].math + " "
                    + stu[i].eng;
            writer.write(str);
            if (i != this.length - 1)
                writer.write("\r\n");
        }
        writer.close();
    }
}
