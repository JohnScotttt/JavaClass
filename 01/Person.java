public class Person 
{
    protected String name,addr;
    protected char sex;
    protected int age;

    public Person()
    {
        System.out.println("hhhhh");
    }

    public Person(String name, char sex)
    {
        this.name = name;
        this.sex = sex;
    }

    public Person(String name, String addr, char sex, int age)
    {
        this.name = name;
        this.addr = addr;
        this.sex = sex;
        this.age = age;
    }

    public void printelem()
    {
        System.out.println("Name:" + name);
        System.out.println("Address:" + addr);
        System.out.println("Sex:" + sex);
        System.out.println("Age:" + age);
    }
}
