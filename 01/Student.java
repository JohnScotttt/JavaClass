public class Student extends Person
{
    protected int math,eng;

    public Student()
    {
        System.out.println("hhhhh");
    }

    public Student(String name, char sex)
    {
        this.name = name;
        this.sex = sex;
    }

    public Student(String name, String addr, char sex, int age, int math, int eng)
    {
        super(name, addr, sex, age);
        this.math = math;
        this.eng = eng;
    }

    public void printelem()
    {
        System.out.println("Name:" + name);
        System.out.println("Address:" + addr);
        System.out.println("Sex:" + sex);
        System.out.println("Age:" + age);
        System.out.println("math:" + math);
        System.out.println("eng:" + eng);
    }
}
