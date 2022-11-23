package src;
public abstract class Person {
    protected String name, addr;
    protected char sex;
    protected int age;

    public Person() {
    }

    public Person(String name, char sex) {
        this.name = name;
        this.sex = sex;
    }

    public Person(String name, String addr, char sex, int age) {
        this.name = name;
        this.addr = addr;
        this.sex = sex;
        this.age = age;
    }

    abstract void printelem();
}
