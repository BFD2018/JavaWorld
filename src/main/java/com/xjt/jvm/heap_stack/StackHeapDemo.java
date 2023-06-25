package com.xjt.jvm.heap_stack;

import lombok.Data;

public class StackHeapDemo {
    static String School = "希望";

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(20);
        p1.setName("xiaoming");

        new StackHeapDemo().func1(p1);
        System.out.println(p1);
        System.out.println(StackHeapDemo.School);

        while(true){}
    }

    public void func1(Person person){
        int a = 10;
        person.setAge(30);
        StackHeapDemo.School = "新希望";
        System.out.println(StackHeapDemo.School + "a== " + a);
    }
}
@Data
class Person{
    private String name;
    private Integer age;
}
