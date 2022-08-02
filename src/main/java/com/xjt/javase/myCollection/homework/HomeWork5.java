package com.xjt.javase.myCollection.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

public class HomeWork5 {
    public static void main(String[] args) {
        TreeSet<Object> set = new TreeSet<>();
        Person p1 = new Person(1000, "mary", 18, "shenzhen");
        set.add(p1);
        Person p2 = new Person(1001, "lucy", 18, "beijing");
        set.add(p2);
        Person p3 = new Person(1002, "jack", 18, "shanghai");
        set.add(p3);
        Person p4 = new Person(1000, "tom", 26, "guangzhou");
        set.add(p4);

        System.out.println(set);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Comparable<Person> {
    private Integer id;
    private String name;
    private Integer age;
    private String addr;

    @Override
    public int compareTo(Person o) {
        if (this == o) {
            return 0;
        } else {
            return this.id.compareTo(o.id);

            //return this.id == o.id ? 0 : 1;
        }

        /*比较器return的说明：一定不能在else中return -1，1等确定的值。会当成return 0处理。表示相等，不再循环找*/
    }
}