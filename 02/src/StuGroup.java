package scr;
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
        for (int i = 0; i < length; i++)
            stu[i].printelem();
    }
}
