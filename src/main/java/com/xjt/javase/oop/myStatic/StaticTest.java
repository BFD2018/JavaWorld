package com.xjt.javase.oop.myStatic;

public class StaticTest {
    public String name;
    public int age;
    public static String country = "china";

    {
        System.out.println("这里是普通初始化块");
    }

    static {
        System.out.println("这里是静态初始化块");
    }

    public StaticTest(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("这里是构造方法");
    }

    public static void MyStaticFunc() {
        System.out.println("我是静态方法MyStaticFunc,国家=" + StaticTest.country);
    }

    public void ShowNameFunc() {
        System.out.println("我是方法ShowNameFunc，name=" + this.name + " ,age=" + this.age);
    }

    public static void main(String[] args) {
        StaticTest obj1 = new StaticTest("zhangsan", 18);
        StaticTest.MyStaticFunc();      //静态方法通过类调用
//        this.ShowNameFunc();        //main主函数也是一个静态方法，不能使用this
        obj1.ShowNameFunc();

        Person p1 = new Person("李大猛", "北京珠市口八大胡同");
        Person p2 = new Person("花花", "北京朝阳门外");
        Person.country = "民国";
        System.out.println(p1.country);
        System.out.println(p2.country);
        Person.setCountry("中华人民共和国");
        System.out.println(p1.country);
        System.out.println(p2.country);
    }
}

class Person {
    static String country;
    String name;
    String address;

    public static void setCountry(String country) {
//        System.out.println(this.name);      //静态方法中不能使用对象，即不能用this
        System.out.println("修改之前的静态字段Person.country=" + Person.country);     //可以使用静态字段
        Person.country = country;
    }

    //构造方法
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
