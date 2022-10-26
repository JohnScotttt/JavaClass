import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import scr.StuGroup;
import scr.Student;

public class App {
    public static void main(String[] args) throws Exception {
        StuGroup group = new StuGroup(20);
        File fl = new File("info.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fl));
        String stdinf = new String();
        while ((stdinf = reader.readLine()) != null){
            String[] info = stdinf.split(" ");
            group.addStu(new Student(info[0],
                                     info[1],
                                     info[2].charAt(0),
                                     Integer.parseInt(info[3]),
                                     Integer.parseInt(info[4]),
                                     Integer.parseInt(info[5])));
        }
        reader.close();
        group.printelem();
    }
}